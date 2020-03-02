package com.bridgelabz.usermanagement.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;

import com.bridgelabz.usermanagement.iservices.IServices;
import com.bridgelabz.usermanagement.model.Registration;

@Path("admin")
@Stateless
public class Controller {
	
	@EJB
	private IServices services;
	
    public Controller() {
    }
    
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    public Response addUser(Registration registration) {
    	String output = services.addUser(registration) ; 
    	return Response.status(200).entity(output).build();
	}
    
    @GET
    @Path("/getOneUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Registration getUser(@QueryParam("email")String email) {
    	return services.getOneUser(email);
    	
    }
    
    @GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getAllUsers() {
		JSONArray jsonArray = new JSONArray();
		List<?> userList = services.getAllUser();
		for (Object user : userList) {
			jsonArray.add(user);
		}
		return jsonArray;
	}
    
    @DELETE
    @Path("/deleteUser")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteUser(@QueryParam("id") int id)
    {
    	int row = services.deleteUser(id);
    	String output = row +" row deleted"; 
    	return Response.status(200).entity(output).build();
    }
    
    @PUT
    @Path("/update")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateUser(@QueryParam("id") int id,@FormParam("firstname") String firstname,@FormParam("middlename") String middlename,
    		@FormParam("lastname") String lastname,@FormParam("email") String email,@FormParam("password") String password,
    		@FormParam("gender") String gender,@FormParam("dateOfBirth") String dateOfBirth,@FormParam("age") String age,
    		@FormParam("state") String state,@FormParam("country") String country,@FormParam("zip") String zip,@FormParam("contact") String contact)
    {
    	Registration user = new Registration();
    	user.setFirstName(firstname);
    	user.setMiddleName(middlename);
    	user.setLastName(lastname);
    	user.setEmail(email);
    	user.setPassword(password);
    	user.setGender(gender);
    	user.setDateOfBirth(dateOfBirth);
    	user.setAge(Integer.parseInt(age));
    	user.setState(state);
    	user.setCountry(country);
    	user.setZip(Integer.parseInt(zip));
    	user.setContact(Long.parseLong(contact));
		String output = services.updateUser(id, user);
		return Response.status(200).entity(output).build();	
    }
   
    @GET
    @Path("/gender")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray genderWise(@QueryParam("gender")String gender) {
    	JSONArray jsonArray = new JSONArray();
    	List<?> userList = services.genderWise(gender);
    	for(Object user : userList)
    		jsonArray.add(user);
    	return jsonArray;
    }
    
    @GET
    @Path("/locations")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray topLocations(){
    	JSONArray jsonArray = new JSONArray();
    	List<?> userList = services.topLocations();
		for (Object user : userList) {
			jsonArray.add(user);
		}
		return jsonArray;
    }
    
    @GET
    @Path("/latestregistered")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray latestRegistered(){
    	JSONArray jsonArray = new JSONArray();
    	List<?>userList = services.latestRegistered();
    	for(Object user : userList)
    		jsonArray.add(user);
		return jsonArray;
    }
}
