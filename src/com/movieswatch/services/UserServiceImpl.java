package com.movieswatch.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.User;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


public class UserServiceImpl implements UserService {

	private EntityManager manager;
	
	private EntityFinder<User> finder;
	
	private PostalCodeService cpService;
	
	public UserServiceImpl() {
		this.finder= new EntityFinderImpl<User>();
		this.cpService= new PostalCodeServiceImpl();
	}
	
	@Override
	public void insertUser(User user) {
		this.manager= EMF.getEM();
		Postalcode cp= cpService.getByNumber(user.getPostalcode().getNumber());
		user.setPostalcode(cp);
		
		EntityTransaction transac= manager.getTransaction();
		 try {
			 transac.begin();
			 manager.persist(user);
			 transac.commit();
			 
		 }catch (ConstraintViolationException e) {
			    Set<ConstraintViolation<?>> embeddedConstraintViolations = e.getConstraintViolations();
			    for (ConstraintViolation details : embeddedConstraintViolations) {
			        String message = details.getMessage();
			        System.err.println(message);
			    }
		 }
		 finally {
			 if(transac.isActive())
				 transac.rollback();
			 manager.clear();
			 manager.close();
		 }
	}

	@Override
	public User findByEmail(String email) {
		Map<String, String> param= new HashMap<String, String>();
		param.put("email", email);
		return finder.findOneByNamedQuery("User.findByEmail", new User(), param);
	}

	@Override
	public User getByEmailAndPassword(User user) {
		Map<String, String> param= new HashMap<String, String>();
		param.put("email", user.getEmail());
		param.put("password", user.getPassword());
		return finder.findOneByNamedQuery("User.connexion", new User(), param);
		
	}

	@Override
	public boolean updateUser(User updatedUser) {
		boolean isUpdated=false;
		manager = EMF.getEM();
		EntityTransaction transac= manager.getTransaction();

		try {
			transac.begin();
			manager.merge(updatedUser);			
			transac.commit();
			isUpdated= true;
		}
		finally {
			if(transac.isActive())
				transac.rollback();
			manager.clear();
			manager.close();
		}
		return isUpdated;
	}

	@Override
	public List<User> getAllUsers() {
		return finder.findByNamedQuery("User.findAll", new User(), null);
	}

	@Override
	public boolean deleteUser(int id) {
		boolean isDeleted= false;
		manager= EMF.getEM();
		EntityTransaction transac= manager.getTransaction();
		try {
			 transac.begin();
			 User userToDel= manager.find(User.class, id);
			 manager.remove(userToDel);
			 transac.commit();
			 isDeleted= true;
		 }finally{
			 if(transac.isActive())
				 transac.rollback();
			 manager.clear();
			 manager.close();
		 }
		return isDeleted;
	}

	@Override
	public User getById(int id) {
		return finder.findOne(new User(), id);
	}

	@Override
	public List<User> getLinkedAccounts(User mainAccount) {
		Map<String, Integer> param= new HashMap<String, Integer>();
		param.put("refere", mainAccount.getId());
		return finder.findByNamedQuery("User.findLinkedAccounts", new User(), param);
	}

}
