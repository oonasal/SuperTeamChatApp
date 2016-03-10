package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import models.Alert;
import models.Message;
import models.Profile;
import models.Task;
import models.User;

public class FileLoader {

    //private FileOutputStream fos;
    private ObjectOutputStream oos;
    //private FileInputStream fis;
    private ObjectInputStream ois;

    public FileLoader() {

    }

    //for saving alerts
    public void saveAlertsToFile(HashMap<Integer, Alert> alerts, File file) {

        HashMap<Integer, Alert> contents = new HashMap<>();

        try {

            contents = readAlertsFromFile(file);  //reads an existing hashmap from the given file
            if (contents != null) {
                contents.putAll(alerts); //adds the keys and values from the given hashmap to the one read from file
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(contents); //writes the new hashmap object to the file
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(alerts);
            }
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for saving alerts to file after removing an alert
    public void saveAlertsAfterRemoval(HashMap<Integer, Alert> alerts, File file) {

        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(alerts);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for reading alerts
    @SuppressWarnings("unchecked")
    public HashMap<Integer, Alert> readAlertsFromFile(File file) {

        HashMap<Integer, Alert> contents = new HashMap<>();

        try {
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                contents = (HashMap<Integer, Alert>) ois.readObject(); //reads the contents of the given file and saves them into a hashmap
                ois.close();
            } else {
                contents = null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contents; //returns the contents as a hashmap
    }

    //for saving messages
    public void saveMessagesToFile(HashMap<Integer, Message> messages, File file) {
        HashMap<Integer, Message> contents = new HashMap<>();

        try {

            contents = readMessagesFromFile(file);  //reads an existing hashmap from the given file
            if (contents != null) {
                contents.putAll(messages); //adds the keys and values from the given hashmap to the one read from file
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(contents); //writes the new hashmap object to the file
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(messages);
            }
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //for reading messages
    @SuppressWarnings("unchecked")
    public HashMap<Integer, Message> readMessagesFromFile(File file) {
        HashMap<Integer, Message> contents = new HashMap<>();

        try {
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                contents = (HashMap<Integer, Message>) ois.readObject(); //reads the contents of the given file and saves them into a hashmap
                ois.close();
            } else {
                contents = null;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contents; //returns the contents as a hashmap
    }

    //for saving users
    public void saveUsersToFile(HashMap<Integer, User> staff, File file) {

        HashMap<Integer, User> contents = new HashMap<>();

        try {

            contents = readUsersFromFile(file);  //reads an existing hashmap from the given file
            if (contents != null) {
                contents.putAll(staff); //adds the keys and values from the given hashmap to the one read from file
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(contents); //writes the new hashmap object to the file
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(staff);
            }
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //for saving users to file after removing a user
    public void saveUsersAfterRemoval(HashMap<Integer, User> staff, File file) {

        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(staff);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for reading users
    @SuppressWarnings("unchecked")
    public HashMap<Integer, User> readUsersFromFile(File file) {
        HashMap<Integer, User> contents = new HashMap<>();

        try {
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                contents = (HashMap<Integer, User>) ois.readObject(); //reads the contents of the given file and saves them into a hashmap
                ois.close();
            } else {
                contents = null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contents; //returns the contents as a hashmap
    }

    //for saving tasks to file
    public void saveTasksToFile(HashMap<Integer, Task> tasks, File file) {

        HashMap<Integer, Task> contents = new HashMap<>();

        try {

            contents = readTasksFromFile(file);  //reads an existing hashmap from the given file
            if (contents != null) {
                contents.putAll(tasks); //adds the keys and values from the given hashmap to the one read from file
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(contents); //writes the new hashmap object to the file
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(tasks);
            }
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //for saving tasks to file after removing a task
    public void saveTasksAfterRemoval(HashMap<Integer, Task> tasks, File file) {

        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for reading tasks
    public HashMap<Integer, Task> readTasksFromFile(File file) {
        HashMap<Integer, Task> contents = new HashMap<>();

        try {
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                contents = (HashMap<Integer, Task>) ois.readObject(); //reads the contents of the given file and saves them into a hashmap
                ois.close();
            } else {
                contents = null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contents; //returns the contents as a hashmap
    }

    //for saving profiles to file
    public void saveProfilesToFile(HashMap<Integer, Profile> profiles, File file) {

        HashMap<Integer, Profile> contents = new HashMap<>();

        try {

            contents = readProfilesFromFile(file);  //reads an existing hashmap from the given file
            if (contents != null) {
                contents.putAll(profiles); //adds the keys and values from the given hashmap to the one read from file
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(contents); //writes the new hashmap object to the file
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(profiles);
            }
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //save after removal
    public void saveProfilesAfterRemoval(HashMap<Integer, Profile> profiles, File file) {
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for reading profiles
    public HashMap<Integer, Profile> readProfilesFromFile(File file) {
        HashMap<Integer, Profile> contents = new HashMap<>();

        try {
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                contents = (HashMap<Integer, Profile>) ois.readObject(); //reads the contents of the given file and saves them into a hashmap
                ois.close();
            } else {
                contents = null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contents; //returns the contents as a hashmap
    }

}
