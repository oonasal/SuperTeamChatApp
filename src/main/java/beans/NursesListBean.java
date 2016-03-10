/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import models.User;
import services.UserService;

/**
 *
 * @author Oona
 */
public class NursesListBean {
    
    public ArrayList<String> getNurses() {
        UserService us = new UserService();
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        
        userList = us.getAllNurses();
        
        for(User u : userList) {
            String userName = u.getFirstName() + " " + u.getLastName();
            list.add(userName);
            System.out.println(userName);
        }
       
        return list;
    }
    
}
