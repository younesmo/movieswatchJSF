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
import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.Role;
import com.movieswatch.entities.User;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;

@Named("RegistrationBean")
@ViewScoped
public class RegistrationBean implements Serializable {

	private User user;
	private Postalcode cp;
	
	//@Inject
	transient private UserService userService;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RegistrationBean() {
		user= new User();
		this.cp= new Postalcode();
		this.userService= new UserServiceImpl();
	}
	
	public String registrateUser() {
		Role role= new Role();
		role.setId(1);
		user.setRole(role);
		user.setPostalcode(cp);
		userService.insertUser(user);
		 FacesMessage message= new FacesMessage("Succés de l'inscription");
		 FacesContext.getCurrentInstance().addMessage(null, message);
		 return "login";
	}
	
	public Postalcode getCp() {
		return cp;
	}

	public void setCp(Postalcode cp) {
		this.cp = cp;
	}

}
