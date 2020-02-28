package com.bridgelabz.usermanagement.servicesimpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bridgelabz.usermanagement.iservices.IServices;
import com.bridgelabz.usermanagement.model.Registration;

@Stateless
public class ServicesImpl implements IServices {

	@PersistenceContext(unitName = "JPADB")
	private EntityManager entityManager;

	@Override
	public String addUser(Registration registration) {
		entityManager.persist(registration);
		return "Successfully Added";
	}
}
