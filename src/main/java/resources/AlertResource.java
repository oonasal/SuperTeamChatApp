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

import models.Alert;
import services.AlertService;

@Path("/alerts")
public class AlertResource {
	
	AlertService alertService = new AlertService();
	
	//get all alerts
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Alert> getAlerts() {
		return alertService.getAllAlerts();
	}
	
	//get alert
	@GET
	@Path("/{alertId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Alert getAlert(@PathParam("alertId") int id) {
		return alertService.getAlert(id);
	}
	
	//update an existing alert
	@PUT
	@Path("/{alertId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Alert updateAlert(@PathParam("alertId") int id, Alert alert) {
		alert.setAlertId(id);
		return alertService.updateAlert(alert);
	}
	
	//add a new alert
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Alert addAlert(Alert alert) {
		return alertService.addAlert(alert);
	}
}
