package com.bridgelabz.usermanagement.iservices;

import javax.ejb.Remote;

import com.bridgelabz.usermanagement.model.Registration;

@Remote
public interface IServices {
	String addUser(Registration registration);

}
