package main;

import models.User;
import org.hibernate.Session;
import services.AlertService;
import services.MessageService;
import services.ProfileService;
import services.TaskService;
import services.UserService;


public class Main {
    
    public static void main(String[] args) {
        initialDatabaseSetup();
        
    }
    
    public static void initialSerializedSetup() {
        AlertService as = new AlertService();
        //as.addAlertsToTestDatabase();
        System.out.println("alert: " + as.getAlert(1).getAlertContent());
        
        MessageService ms = new MessageService();
        //ms.addMessagesToTestDatabase();
        System.out.println("message: " + ms.getMessage(1).getMessageContent());
        
        TaskService ts = new TaskService();
        //ts.addTasksToTestDatabase();
        System.out.println("task: " + ts.getTask(1).getTaskContent());
        System.out.println("task receiver: " + ts.getTask(1).getReceiverName());
        
        //Task task1 = new Task("Markku", 78, "Leena", 1, "Hey Leena, remember to check the updated schedule!");
        TaskService ts2 = new TaskService();
        //ts2.addTask(task1);
        System.out.println("task: " + ts.getTask(2).getTaskContent());
        System.out.println("task receiver: " + ts.getTask(2).getReceiverName());
        
        UserService us = new UserService();
        //us.addUsersToTestDatabase();
        System.out.println("user: " + us.getUser(3).getFullName());
        
        ProfileService ps = new ProfileService();
        //Profile p = new Profile("Ash", "Ketchum", "Pokemon trainer", "ash@gmail.com", 8);
        //ps.addProfile(p);
        System.out.println("profile added");
    }
    
    public static void initialDatabaseSetup() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = new User();
        user.setFirstName("Aku");
        user.setLastName("Ankka");
        user.setEmail("aku13@gmail.com");
        
        User user2 = new User();
        user2.setFirstName("Mikki");
        user2.setLastName("Hiiri");
        user2.setEmail("hiiri566@gmail.com");
        
        session.saveOrUpdate(user);
        session.saveOrUpdate(user2);
        
        session.getTransaction().commit();
        session.close();
    }
    
}
