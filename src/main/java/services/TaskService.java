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

import models.Task;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TaskService implements Serializable {

    //private HashMap<Integer, Task> tasks = TestDatabase.getTasks();
    private transient HashMap<Integer, Task> tasks = new HashMap<>();
    public transient FileLoader fileLoader;
    private transient File file;

    public TaskService() {
        /*file = new File("C:/Users/Oona/Documents/NetBeansProjects/SuperTeamApp/SuperTeamAppWithLogin2/src/main/java/files/tasks.data");
        fileLoader = new FileLoader();
        
        if(!(getReadTasks() == null)) {
            tasks = getReadTasks();
        }*/
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.tasks);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.tasks = (HashMap<Integer, Task>) in.readObject();
    }

    //for testing purposes
    public void addTasksToTestDatabase() {
        Task task1 = new Task("13.3.2016", "Maija Mehiläinen", 78, "May Mellow", 65, "Clean the toilets on floors 3-9.");
        tasks.put(task1.getTaskId(), task1);
        fileLoader.saveTasksToFile(tasks, file);
    }

    public List<Task> getAllTasks() {
        //eturn new ArrayList<>(tasks.values());
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();

        Criteria criteria = session.createCriteria(Task.class);
        List<Task> taskList = (List<Task>) criteria.list();
        tr.commit();
        session.close();
        return taskList;
    }

    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public Task addTask(Task task) {
        /*task.setTaskId(tasks.size() + 1);
        tasks.put(task.getTaskId(), task);
        saveTasks();
        return task;*/
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(task);

        session.getTransaction().commit();
        session.close();
        return task;
    }

    public Task updateTask(Task task) {
        if (task.getTaskId() <= 0) {
            return null;
        }
        tasks.put(task.getTaskId(), task);
        saveTasks();
        return task;
    }

    public Task removeTask(int taskId) {
        tasks = getReadTasks();
        Task removedTask = tasks.remove(taskId);
        saveAfterRemoval();
        return removedTask;
    }
    
    public void saveTasks() {
        fileLoader.saveTasksToFile(tasks, file);
    }
    
    public void saveAfterRemoval() {
        fileLoader.saveTasksAfterRemoval(tasks, file);
    }
    
    private HashMap<Integer, Task> getReadTasks() {
        HashMap<Integer, Task> readTasks = new HashMap<>();
        readTasks = fileLoader.readTasksFromFile(file);
        return readTasks;
    }
}
