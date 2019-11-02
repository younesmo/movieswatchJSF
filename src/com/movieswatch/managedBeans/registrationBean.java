package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.movieswatch.dao.EMF;
import com.movieswatch.entities.Codepostaux;
import com.movieswatch.entities.Role;
import com.movieswatch.entities.Utilisateur;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;

@Named
@ViewScoped
public class registrationBean implements Serializable {

	private Utilisateur user;
	private Codepostaux cp;
	
	//@Inject
	transient private UserService userService;
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public registrationBean() {
		user= new Utilisateur();
		this.cp= new Codepostaux();
		this.userService= new UserServiceImpl();
	}
	
	public String registrateUser() {
		Role role= new Role();
		role.setIdRole(1);
		user.setRole(role);
		user.setCodepostaux(cp);
		userService.insertUser(user);
		 FacesMessage message= new FacesMessage("Succés de l'inscription");
		 FacesContext.getCurrentInstance().addMessage(null, message);
		 return "login";
	}
	
	public Codepostaux getCp() {
		return cp;
	}

	public void setCp(Codepostaux cp) {
		this.cp = cp;
	}

}
