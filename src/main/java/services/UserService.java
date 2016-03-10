package services;

import database.FileLoader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import models.Profile;

import models.User;

public class UserService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -797406039349190411L;

    //private HashMap<Integer, User> users = TestDatabase.getUsers();
    //private HashMap<Integer, User> doctors = TestDatabase.getDoctors();
    //private HashMap<Integer, User> nurses = TestDatabase.getNurses();
    //private HashMap<Integer, User> specialists = TestDatabase.getSpecialists();
    private transient HashMap<Integer, User> users = new HashMap<>();
    private transient HashMap<Integer, User> doctors = new HashMap<>();
    private transient HashMap<Integer, User> nurses = new HashMap<>();
    private transient HashMap<Integer, User> specialists = new HashMap<>();

    private transient File file1, file2, file3, file4;
    private transient FileLoader fileLoader;

    private transient ProfileService profileService = new ProfileService();
    private transient Profile profile;

    public UserService() {
        file1 = new File("src/main/java/files/users.data");
        file2 = new File("src/main/java/files/doctors.data");
        file3 = new File("src/main/java/files/nurses.data");
        file4 = new File("src/main/java/files/specialists.data");
        fileLoader = new FileLoader();

        if (!(getReadUsers(file1) == null)) {
            users = getReadUsers(file1);
        }
        if (!(getReadUsers(file2) == null)) {
            doctors = getReadUsers(file2);
        }
        if (!(getReadUsers(file3) == null)) {
            nurses = getReadUsers(file3);
        }
        if (!(getReadUsers(file4) == null)) {
            specialists = getReadUsers(file4);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.users);
        out.writeObject(this.doctors);
        out.writeObject(this.nurses);
        out.writeObject(this.specialists);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.users = (HashMap<Integer, User>) in.readObject();
        this.doctors = (HashMap<Integer, User>) in.readObject();
        this.nurses = (HashMap<Integer, User>) in.readObject();
        this.specialists = (HashMap<Integer, User>) in.readObject();
    }

    //for testing purposes
    public void addUsersToTestDatabase() {
        User user1 = new User("Maria", "Heikkinen", "Nurse", "maria.heikkinen@gmail.com", 1);
        User user2 = new User("Matti", "Kaupponen", "Nurse", "matti.kaupponen@gmail.com", 2);
        User user3 = new User("James", "Smith", "Dentist", "james_3423@hotmail.com", 3);
        User user4 = new User("Helen", "Lee", "Doctor", "hello.helen@gmail.com", 4);
        User user5 = new User("Jack", "Jackson", "Doctor", "jj34289y42@gmail.com", 5);

        doctors.put(user4.getUserId(), user4);
        doctors.put(user5.getUserId(), user5);

        nurses.put(user1.getUserId(), user1);
        nurses.put(user2.getUserId(), user2);

        specialists.put(user3.getUserId(), user3);

        users.putAll(doctors);
        users.putAll(nurses);
        users.putAll(specialists);

        fileLoader.saveUsersToFile(users, file1);
        fileLoader.saveUsersToFile(doctors, file2);
        fileLoader.saveUsersToFile(nurses, file3);
        fileLoader.saveUsersToFile(specialists, file4);
    }

    //returns users as an arraylist
    public ArrayList<User> getAllUsers() {
        //System.out.println("y: " + users.size());
        return new ArrayList<>(users.values());
    }

    //returns a particular user by id
    public User getUser(int id) {
        return users.get(id);
    }

    //adds a user to a hashmap/hashmaps and saves it/them to a file
    public User addUser(User user) {
        user.setUserId(users.size() + 1);

        //users.put(user.getUserId(), user);
        if (user.getRole().equals("Doctor")) {
            doctors.put(user.getUserId(), user);
            saveUsers(doctors, file2);
        } else if (user.getRole().equals("Nurse")) {
            nurses.put(user.getUserId(), user);
            saveUsers(nurses, file3);
        } else {
            specialists.put(user.getUserId(), user);
            saveUsers(specialists, file4);
        }

        if (doctors != null) {
            users.putAll(doctors);
        }
        if (nurses != null) {
            users.putAll(nurses);
        }
        if (specialists != null) {
            users.putAll(specialists);
        }

        //System.out.println("Trying to create a profile");
        this.profile = new Profile(user.getFirstName(), user.getLastName(), user.getRole(), user.getEmail(), user.getUserId());
        //System.out.println("Trying to add a profile");
        this.profileService.addProfile(profile);

        saveUsers(users, file1);

        return user;
    }

    //updates an existing user and saves it to the file
    public User updateUser(User user) {
        //if user doesn't exist, return null
        if (user.getUserId() <= 0) {
            return null;
        }

        //users.put(user.getUserId(), user);
        if (user.getRole().equals("Doctor")) {
            doctors.put(user.getUserId(), user);
            if (nurses.containsValue(user)) {
                nurses.remove(user.getUserId());
            }
            users.putAll(doctors);
            saveUsers(doctors, file2);
        } else if (user.getRole().equals("Nurse")) {
            nurses.put(user.getUserId(), user);
            //System.out.println("nurse");
            if (doctors.containsKey(user.getUserId())) {
                //System.out.println("nurse2");
                doctors.remove(user.getUserId());
                //System.out.println("removed " + user.getUserId());
                //System.out.println(doctors.keySet());
            }
            users.putAll(nurses);
            saveUsers(nurses, file3);
            //System.out.println(doctors.keySet());
            //System.out.println(nurses.keySet());
        } else {
            specialists.put(user.getUserId(), user);
            users.putAll(specialists);
            saveUsers(specialists, file4);
        }

        saveUsers(users, file1);

        return user;
    }

    //removes a user from the file and returns the user that was removed
    public User removeUser(int id) {
        if (doctors.containsKey(id)) {
            doctors.remove(id);
            saveAfterUserRemoval(doctors, file2);
        } else if (nurses.containsKey(id)) {
            nurses.remove(id);
            saveAfterUserRemoval(nurses, file3);
        } else if (specialists.containsKey(id)) {
            specialists.remove(id);
            saveAfterUserRemoval(specialists, file4);
        }
        User removedUser = users.remove(id);
        saveAfterUserRemoval(users, file1);
        return removedUser;
    }

    public ArrayList<User> getAllDoctors() {
        return new ArrayList<User>(doctors.values());
    }

    public User getDoctor(int id) {
        return doctors.get(id);
    }

    public ArrayList<User> getAllNurses() {

        /*ArrayList<String> nurseList = new ArrayList<String>();
		for(User n : nurses.values()) {
			nurseList.add(n.getFullName());
		}
		return nurseList;*/
        return new ArrayList<User>(nurses.values());
    }

    public User getNurse(int id) {
        return nurses.get(id);
    }

    public ArrayList<User> getAllSpecialists() {
        return new ArrayList<User>(specialists.values());
    }

    public User getSpecialist(int id) {
        return specialists.get(id);
    }

    //saves users to file
    public void saveUsers(HashMap<Integer, User> staff, File file) {
        //save users, doctors, nurses and specialists
        fileLoader.saveUsersToFile(staff, file);
    }

    //saves users to file after removing a user
    public void saveAfterUserRemoval(HashMap<Integer, User> staff, File file) {
        fileLoader.saveUsersAfterRemoval(staff, file);
    }

    //reads the users from file and saves them into a temporary hashmap for use
    private HashMap<Integer, User> getReadUsers(File file) {
        HashMap<Integer, User> readUsers = new HashMap<>();
        readUsers = fileLoader.readUsersFromFile(file);
        return readUsers;
    }

}
