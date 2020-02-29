package com.bridgelabz.usermanagement.servicesimplementation;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

	@Override
	public Registration getOneUser(String email) {
		TypedQuery<Registration> query = (TypedQuery<Registration>) entityManager.createQuery("SELECT c FROM user_management_api c WHERE c.email = :email");
		return query.setParameter("email", email).getSingleResult();
	}

	@Override
	public List<?> getAllUser() {
		Query query = entityManager.createQuery("SELECT c FROM user_management_api c");
		return query.getResultList();
		
	}

	@Override
	public int deleteUser(int id) {
		Query query = entityManager.createQuery("DELETE FROM user_management_api c WHERE c.id= "+id);
		return query.executeUpdate();
	}

	@Override
	public String updateUser(int id, Registration users) {
		Registration user = entityManager.find(Registration.class,id);
		user.setFirstName(users.getFirstName());
		user.setMiddleName(users.getMiddleName());
		user.setLastName(users.getLastName());
		user.setEmail(users.getEmail());
		user.setPassword(users.getPassword());
		user.setGender(users.getGender());
		user.setDateOfBirth(users.getDateOfBirth());
		user.setAge(users.getAge());
		user.setState(users.getState());
		user.setCountry(users.getCountry());
		user.setZip(users.getZip());
		user.setContact(users.getContact());
		entityManager.persist(user);
		return "Successfully Updated";
	}

	@Override
	public List<?> genderWise(String gender) {
		Query query = entityManager.createQuery("SELECT c FROM user_management_api c WHERE c.gender =:gender");
		return query.setParameter("gender", gender).getResultList();
//		TypedQuery<Registration> query = (TypedQuery<Registration>) entityManager
//				.createQuery("SELECT c FROM user_management_api c WHERE c.gender =:gender");
//		return query.setParameter("gender", gender).getResultList();
	}

	@Override
	public List<?> topLocations() {
		TypedQuery<Registration> query = (TypedQuery<Registration>) entityManager.createQuery("SELECT c.state,count(*) as Counts FROM user_management_api c GROUP BY c.state ORDER BY Counts DESC");
		return query.getResultList();
	}
}
