package com.bridgelabz.usermanagement.repository;

import javax.persistence.EntityManager;
import org.json.simple.JSONObject;

public class AdminDAO {
	
	private EntityManager entityManager;
	
	public String addUser(JSONObject jsonObject) {
		entityManager.persist(jsonObject);
		return "Successfully Added";
	}

}
