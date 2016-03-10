package resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Task;
import services.TaskService;

@Path("/tasks")
public class TaskResource {

	TaskService taskService = new TaskService();
	
	//get all tasks
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Task> getTasks() {
		return taskService.getAllTasks();
	}
	
	//get task
	@GET
	@Path("/{taskId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Task getTask(@PathParam("taskId") int id) {
		return taskService.getTask(id);
	}
	
	//update an existing task
	@PUT
	@Path("/{taskId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Task updateTask(@PathParam("taskId") int id, Task task) {
		task.setTaskId(id);
		return taskService.updateTask(task);
	}
	
	//add a new task
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Task addTask(Task task) {
		return taskService.addTask(task);
	}
	
        @DELETE
        @Path("/{taskId}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Task deleteTask(@PathParam("taskId") int id, Task task) {
            return taskService.removeTask(id);
            
        }
}
