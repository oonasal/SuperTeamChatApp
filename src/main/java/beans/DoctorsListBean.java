package beans;

import java.util.ArrayList;
import java.util.List;
import models.User;
import services.UserService;

public class DoctorsListBean {
    

    public ArrayList<String> getDoctors() {
        UserService us = new UserService();
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        
        userList = us.getAllDoctors();
        
        for(User u : userList) {
            String userName = u.getFirstName() + " " + u.getLastName();
            list.add(userName);
            System.out.println(userName);
        }
       
        /*
        list.add("Liyue");
        list.add("Milda");
        list.add("Leena");
        list.add("Maria");
        list.add("Oona");*/
        
        return list;
    }

}
