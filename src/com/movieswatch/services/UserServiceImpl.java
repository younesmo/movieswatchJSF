package com.movieswatch.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Codepostaux;
import com.movieswatch.entities.Utilisateur;

public class UserServiceImpl implements UserService {

	private EntityManager manager;
	
	//@Inject
	private EntityFinder<Utilisateur> finder;
	
	//@Inject
	private CodePostauxService cpService;
	
	public UserServiceImpl() {
		this.manager= EMF.getEM();
		this.finder= new EntityFinderImpl<Utilisateur>();
		this.cpService= new CodePostauxServiceImpl();
	}
	
	@Override
	public void insertUser(Utilisateur user) {
		Codepostaux cp= cpService.getByNumber(user.getCodepostaux().getNumero());
		user.setCodepostaux(cp);
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
	public Utilisateur findByEmail(String email) {
		Map<String, String> param= new HashMap<String, String>();
		param.put("email", email);
		return finder.findOneByNamedQuery("Utilisateur.findByEmail", new Utilisateur(), param);
	}

	@Override
	public Utilisateur getByEmailAndPassword(Utilisateur user) {
		Map<String, String> param= new HashMap<String, String>();
		param.put("email", user.getEmail());
		param.put("password", user.getPasswd());
		return finder.findOneByNamedQuery("Utilisateur.connexion", new Utilisateur(), param);
		
	}

}
