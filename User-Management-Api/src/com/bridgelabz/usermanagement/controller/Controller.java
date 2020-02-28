package com.bridgelabz.usermanagement.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public String addUser(Registration registration) {
    	System.out.println("Hitting Controller");
    	return services.addUser(registration) ; 
	}
}
