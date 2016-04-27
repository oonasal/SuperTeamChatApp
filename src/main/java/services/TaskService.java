package services;

import database.FileLoader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import models.Task;

public class TaskService implements Serializable {

    //private HashMap<Integer, Task> tasks = TestDatabase.getTasks();
    private transient HashMap<Integer, Task> tasks = new HashMap<>();
    public transient FileLoader fileLoader;
    private transient File file;

    public TaskService() {
        file = new File("C:/Users/Oona/Documents/NetBeansProjects/SuperTeamApp/SuperTeamAppWithLogin2/src/main/java/files/tasks.data");
        fileLoader = new FileLoader();
        
        if(!(getReadTasks() == null)) {
            tasks = getReadTasks();
        }
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

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public Task addTask(Task task) {
        task.setTaskId(tasks.size() + 1);
        tasks.put(task.getTaskId(), task);
        saveTasks();
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
