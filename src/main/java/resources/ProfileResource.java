package resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Profile;
import services.ProfileService;

@Path("/profiles")
public class ProfileResource {

    ProfileService profileService = new ProfileService();

    //get all profiles
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    //get profile
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfile(@PathParam("id") int id) {
        return profileService.getProfile(id);
    }

    //add a new user
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Profile addProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    //update an existing profile
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Profile updateProfile(@PathParam("id") int id, Profile profile) {
        profile.setId(id);
        return profileService.updateProfile(profile);
    }
}
