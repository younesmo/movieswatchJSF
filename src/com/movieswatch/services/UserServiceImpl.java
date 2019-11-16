package com.movieswatch.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.User;
import com.movieswatch.utils.SessionUtils;

public class UserServiceImpl implements UserService {

	private EntityManager manager;
	
	//@Inject
	private EntityFinder<User> finder;
	
	//@Inject
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
			 
		 }catch(Exception e) {
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
		boolean isUpdateDone=false;
		manager = EMF.getEM();
		EntityTransaction transac= manager.getTransaction();

		try {
			transac.begin();
			manager.merge(updatedUser);			
			transac.commit();
			isUpdateDone= true;
		}
		finally {
			if(transac.isActive())
				transac.rollback();
			manager.clear();
			manager.close();
		}
		return isUpdateDone;
	}

}
