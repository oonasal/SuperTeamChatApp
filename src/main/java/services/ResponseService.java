package services;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Response;
import servlets.Validate;

/**
 *
 * @author Oona
 */
public class ResponseService {
    
    Response response;
    
    public ResponseService() {
        response = new Response();
    }
    
    public Response getResponse(String email, String password) {
        try {
            if (Validate.checkUser(email, password)) {
                response.setFirstName(Validate.fetchFirstName(email));
                response.setLastName(Validate.fetchLastName(email));
                response.setEmail(email);
                response.setLoginSuccessful(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
