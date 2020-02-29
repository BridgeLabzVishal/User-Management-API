package com.bridgelabz.usermanagement.iservices;

import java.util.List;

import javax.ejb.Remote;
import com.bridgelabz.usermanagement.model.Registration;

@Remote
public interface IServices {
	String addUser(Registration registration);
	Registration getOneUser(String email);
	List<?> getAllUser();
	int deleteUser(int id);
	String updateUser(int id,Registration user);
	List<?> genderWise(String gender);
	List<?> topLocations();
}
