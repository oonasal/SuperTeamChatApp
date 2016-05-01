package models;

/**
 *
 * @author Oona
 */
public class Response {

    private boolean loginSuccessful;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    
    public Response() {
        loginSuccessful = false;
        email = "";
        firstName = "";
        lastName = "";
        password = "";
    }

    public Response(boolean loginSuccessful, String email, String firstName, String lastName, String password) {
        this.loginSuccessful = loginSuccessful;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
    
    

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
