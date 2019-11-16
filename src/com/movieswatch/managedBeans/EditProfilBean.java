package com.movieswatch.managedBeans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.User;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;
import com.movieswatch.utils.SessionUtils;

@Named
@ViewScoped
public class EditProfilBean implements Serializable{
	
	private User user;
	private Postalcode cp;
	transient private UserService userService;
	
	public EditProfilBean() {
		this.userService= new UserServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		user= SessionUtils.getCurrentUser();
		cp= user.getPostalcode();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Postalcode getCp() {
		return cp;
	}

	public void setCp(Postalcode cp) {
		this.cp = cp;
	}
	
	public String saveUser() throws IOException {
		if(userService.updateUser(user)) {
			SessionUtils.setCurrentUser(user);
			FacesMessage message= new FacesMessage("Mise à jour effectué avec succés");
			 FacesContext.getCurrentInstance().addMessage(null, message);
			return "profil";
		}
		return "";
	}
	
	public String goToEdit() {
		return "editProfil";
	}
	
	public String goToEditPassword() {
		return "editPassword";
	}


	
	
	
}
