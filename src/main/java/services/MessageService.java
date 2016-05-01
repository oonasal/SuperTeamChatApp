package services;

import database.FileLoader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import main.HibernateUtil;

import models.Message;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MessageService implements Serializable {

    private static final long serialVersionUID = 2294266773197932480L;
    private transient HashMap<Integer, Message> messages = new HashMap<>();
    private transient FileLoader fileLoader;
    private transient File file;

    public MessageService() {
        /*file = new File("C:/Users/Oona/Documents/NetBeansProjects/SuperTeamApp/SuperTeamAppWithLogin2/src/main/java/files/messages.data");
        fileLoader = new FileLoader();

        if (!(getReadMessages() == null)) {
            messages = getReadMessages();
        }*/
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.messages);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.messages = (HashMap<Integer, Message>) in.readObject();
    }

    //for testing purposes
    public void addMessagesToTestDatabase() {
        Message message1 = new Message("Mark M.", "Milja Mammutti", "How do you send messages on this app?!");
        messages.put(message1.getMessageId(), message1);
        fileLoader.saveMessagesToFile(messages, file);
    }

    //returns messages as an arraylist
    public List<Message> getAllMessages() {
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();

        Criteria criteria = session.createCriteria(Message.class);
        List<Message> messageList = (List<Message>) criteria.list();
        tr.commit();
        session.close();
        return messageList;
        
        //return new ArrayList<>(messages.values());
    }

    //returns a particular message
    public Message getMessage(int messageId) {
        return messages.get(messageId);
    }

    //adds a message to the messages hashmap and saves it to the file
    public Message addMessage(Message message) {
        /*message.setMessageId(messages.size() + 1);
        messages.put(message.getMessageId(), message);
        saveMessages();
        return message;*/
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(message);
        session.getTransaction().commit();
        session.close();
        return message;
    }

    //saves messages to a file
    public void saveMessages() {
        fileLoader.saveMessagesToFile(messages, file);
    }

    //reads the messages from file and saves them into a temporary hashmap for use
    private HashMap<Integer, Message> getReadMessages() {
        HashMap<Integer, Message> readMessages = new HashMap<>();
        readMessages = fileLoader.readMessagesFromFile(file);
        return readMessages;
    }
}
