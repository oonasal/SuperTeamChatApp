package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import models.Response;
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
    public Response getResponse(Response response) {
        Response r = new Response();
        String email = r.getEmail();
        String password = r.getPassword();
        return responseService.getResponse(email, password);
    }
}
