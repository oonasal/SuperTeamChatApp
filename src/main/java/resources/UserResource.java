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

import models.User;
import services.UserService;

@Path("/users")
public class UserResource {
	
	UserService userService = new UserService();
	
	//get all users
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getUsers() {
		return userService.getAllUsers();
	}

	//get user
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userId") int id) {
		return userService.getUser(id);
	}
	
	//get all doctors
	@GET
	@Path("/doctors")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getDoctors() {
		return userService.getAllDoctors();
	}
	
	//get doctor
	@GET
	@Path("/doctors/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getDoctor(@PathParam("userId") int id) {
		return userService.getDoctor(id);
	}
	
	//get all nurses
	@GET
	@Path("/nurses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getNurses() {
		return userService.getAllNurses();
	}
	
	//get nurse
	@GET
	@Path("/nurses/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getNurse(@PathParam("userId") int id) {
		return userService.getNurse(id);
	}
	
	//get all specialists
	@GET
	@Path("/specialists")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getSpecialists() {
		return userService.getAllSpecialists();
	}
	
	//get specialist
	@GET
	@Path("/specialists/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getSpecialist(@PathParam("userId") int id) {
		return userService.getSpecialist(id);
	}
	
	//update an existing user
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(@PathParam("userId") int id, User user) {
		user.setUserId(id);
		return userService.updateUser(user);
	}

	//add a new user
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User addUser(User user) {
		return userService.addUser(user);
	}

}
