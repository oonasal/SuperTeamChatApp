package websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import models.Message;
import services.MessageService;

@ServerEndpoint("/chat")
public class ServerEndPoint {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    //map for storing session ids and usernames
    private static Map<String, String> users = Collections.synchronizedMap(new HashMap<String, String>());
    private String username;
    private String delims = "[:]+";
    private String receiverName;
    private String receiverSessionId;
    private Session userSession;
    private String senderName;
    private String senderSessionId;
    private String messageToSend = "";
    //String messageToSendWithSender = "";
    
    private Message m;
    private MessageService ms;

    public ServerEndPoint() {
        username = null;
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {
        username = (String) session.getUserProperties().get("username");

        if (username == null) { //if username hasn't been set yet

            //make the message the user's username (the first thing typed into the text input field)
            session.getUserProperties().put("username", message);
            username = (String) session.getUserProperties().get("username");

            addIntoUsersMap(session.getId(), username);
            System.out.println("Users after adding: " + users.keySet() + "--->" + users.values());

            //call buildJsonMessageData() method; send "System" as username and the rest as message
            session.getBasicRemote().sendText(buildJsonMessageData("System", "you are now connected as " + message));

            for (Session s : sessions) {
                s.getBasicRemote().sendText(buildJsonUsersData());
            }
        } else {
            parseMessage(message);
            userSession = session;
            //if receiver is marked as everyone, send message to all connected users
            //otherwise, send to the user marked as the receiver
            if (receiverName.equals("Everyone")) {
                for (Session s : sessions) {
                    s.getBasicRemote().sendText(buildJsonMessageData(senderName, messageToSend));
                    
                }
                
            } else {
                sendMessageToUser(receiverName, senderName, messageToSend);
                
                
            }
            System.out.println("sendername: " + senderName + " message: " + messageToSend + " receivername: " + receiverName);
            
            //save the message to message log file
            m = new Message(senderName, receiverName, messageToSend);
            ms = new MessageService();
            ms.addMessage(m);
            System.out.println("added!" + m.getMessageId());
            
            messageToSend = "";
        }
    }

    public void sendMessageToUser(String receiverName, String senderName, String messageToSend) throws IOException {

        //get receiver's session id
        if (users.containsValue(receiverName)) {
            for (String t : users.keySet()) {
                if (receiverName.equals(users.get(t))) {
                    receiverSessionId = t;
                }
            }
        }

        //get sender's session id
        senderSessionId = userSession.getId();

        //send message to the receiver and back to the sender
        for (Session s : sessions) {
            if (s.getId().equals(receiverSessionId)) {
                s.getBasicRemote().sendText(buildJsonMessageData(senderName + " (Private)", messageToSend));
            }
            if (s.getId().equals(senderSessionId)) {
                if (!(messageToSend.equals(""))) {
                    s.getBasicRemote().sendText(buildJsonMessageData(senderName + " (Private)", messageToSend));
                }
            }
            if (messageToSend.equals("")) {
                s.getBasicRemote().sendText(buildJsonMessageData(senderName, receiverName));
            }
        }
    }

    //take information from the message
    public void parseMessage(String message) {

        //find sender's name
        senderName = username;

        //find receiver's name
        String tokens[] = message.split(delims, 2);
        receiverName = tokens[0];

        //find the actual message to be sent
        for (int i = 1; i < tokens.length; i++) {
            messageToSend += tokens[i] + " ";
        }

    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        sessions.add(session);

        for (Session s : sessions) {
            s.getBasicRemote().sendText(buildJsonUsersData());
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        sessions.remove(session);
        removeFromUsersMap(session.getId());

        for (Session s : sessions) {
            s.getBasicRemote().sendText(buildJsonUsersData());
        }
    }

    private String buildJsonMessageData(String username, String message) {
        JsonObject jsonObject = Json.createObjectBuilder().add("message", username + ": " + message).build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }

    private String buildJsonUsersData() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (String t : getUserNames()) {
            jsonArrayBuilder.add((String) t);
        }
        return Json.createObjectBuilder().add("users", jsonArrayBuilder).build().toString();
    }

    private Set<String> getUserNames() {
        HashSet<String> usernameSet = new HashSet<>();

        for (Session s : sessions) {
            usernameSet.add(s.getUserProperties().get("username").toString());
        }
        return usernameSet;
    }

    private void addIntoUsersMap(String sessionId, String username) {
        users.put(sessionId, username);
    }

    private void removeFromUsersMap(String sessionId) {
        users.remove(sessionId);
    }
}
