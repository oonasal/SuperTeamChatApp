package beans;

import java.util.ArrayList;
import models.User;
import services.UserService;

public class SpecialistsListBean {
    public ArrayList<String> getSpecialists() {
        UserService us = new UserService();
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        
        userList = us.getAllSpecialists();
        
        for(User u : userList) {
            String userName = u.getFirstName() + " " + u.getLastName();
            list.add(userName);
            System.out.println(userName);
        }
       
        return list;
    }
}
