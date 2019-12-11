package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.Role;
import com.movieswatch.entities.User;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;

@Named("registrationBean")
@ViewScoped
public class RegistrationBean implements Serializable {
//edit name
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8314158609120964813L;
	
	private User user;
	private Postalcode cp;
	private int idRole;
	
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
	
	public String insertUser() {
		Role role= new Role();
		role.setId(idRole);
		user.setRole(role);
		user.setPostalcode(cp);
		userService.insertUser(user);
		 FacesMessage message= new FacesMessage("Membres ajoutés");
		 FacesContext.getCurrentInstance().addMessage(null, message);
		 return "members";
	}
	
	public Postalcode getCp() {
		return cp;
	}

	public void setCp(Postalcode cp) {
		this.cp = cp;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int role) {
		this.idRole = role;
	}
	
}