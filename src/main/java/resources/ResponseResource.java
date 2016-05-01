package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import models.Request;
import models.Response;
import org.json.JSONObject;
import services.ResponseService;

/**
 *
 * @author Oona
 */
@Path("/mylogin")
public class ResponseResource {
    
    ResponseService responseService = new ResponseService();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(String request) {
//        Response r = new Response();
//        String email = r.getEmail();
//        String password = r.getPassword();
//        return responseService.getResponse(email, password);
        JSONObject js = new JSONObject(request);
        String email = js.getString("email");
        String password = js.getString("password");
        
        // TODO : User service....move to Hibernate 
        return responseService.getResponse(email, password);
//        return new Response(true,email,"Test","Testnimi",password);
    }
}
