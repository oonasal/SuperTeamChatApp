package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity (name = "profile_details")
@XmlRootElement
public class Profile implements Serializable {

    /**
     *
     * @author Oona
     */
    private String firstName;
    private String lastName;
    private String fullName;
    private String jobTitle;
    private String phoneNumber;
    private String email;

    @Id
    @GeneratedValue
    private int id;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String email, int id) {
        this(firstName, lastName, "", "", email, id);
    }

    public Profile(String firstName, String lastName, String jobTitle, String email, int id) {
        this(firstName, lastName, jobTitle, "", email, id);
    }

    public Profile(String firstName, String lastName, String jobTitle, String phoneNumber, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.email = email;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
