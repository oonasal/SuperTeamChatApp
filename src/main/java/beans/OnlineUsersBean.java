package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OnlineUsersBean {
    
    private static List<String> onlineUsersList = new ArrayList<>();
    
    public OnlineUsersBean() {
        /*if(!(onlineUsersList.contains("me"))) {
            onlineUsersList.add("me");
        }*/
        
    }
    
    public List<String> getOnlineUsers() {
        
        if(onlineUsersList.isEmpty()) {
            onlineUsersList.add("Empty");
        } else if(onlineUsersList.size() >= 2) {
            onlineUsersList.remove("Empty");
        }
        
        return onlineUsersList;
    }
    
    public void setOnlineUsers(Map<String, String> onlineUsers) {
        
        Map<String, String> users = onlineUsers;
        
        //System.out.println("ONLINE USERS: " + onlineUsers.values());
        
        for(String s : users.values()) {
            if(!(onlineUsersList.contains(s))) {
                onlineUsersList.add(s);
            //System.out.println("added: " + s);
            }
            
        }
        
        /*for(String name : onlineUsersList) {
            System.out.println("NAME: " + name);
        }*/
        
    }
}
