package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;
import services.ProfileService;

@Entity
@XmlRootElement
public class User implements Serializable {

    /**
     *
     * @author Oona
     */
    private static final long serialVersionUID = -3550144232479495572L;
    
    @Id
    @GeneratedValue
    private int userId;
    
    private String firstName;
    private String lastName;
    private String role; //"doctor", "nurse" or something else
    //private static int userIDCounter;
    private boolean loggedIn;
    private String email;
    private String fullName;

    private transient Profile profile;
    private transient ProfileService profileService;

    //private transient HashMap<Integer, Profile> profiles = new HashMap<>();
    public User() {
    }

    public User(String firstName, String lastName, String role, String email, int userId) {
        //userIDCounter++;
        //this.userId = userIDCounter;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.loggedIn = false;
        this.email = email;
        this.fullName = firstName + " " + lastName;
        this.profile = new Profile(firstName, lastName, email, userId);

        //this.profileService.addProfile(profile);
        //this.profiles.put(userId, profile);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
