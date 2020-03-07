package com.movieswatch.managedBeans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.Role;
import com.movieswatch.entities.User;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;

@SessionScoped
@Named
public class UpdateMemberBean implements Serializable {
	private User user;
	private Postalcode cp;
	private int idRole;
	private int idMember;
	transient private UserService userService;
	transient private Logger log= Logger.getLogger(UpdateMemberBean.class);
	
	public UpdateMemberBean() {
		this.userService= new UserServiceImpl();
	}
	
	@PostConstruct
	public void init() {
	}
	
	public String goToUpdateProfil() {
		log.debug("id membre: "+ idMember);
		user= userService.getById(idMember);
		return "updateProfil";		
	}
	
	public String goToUpdate() {
		log.debug("id:" + idMember);
		user= userService.getById(idMember);
		cp= user.getPostalcode();
		return "updateMember";
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
	
	
	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int role) {
		this.idRole = role;
	}
	

	public int getIdMember() {
		return idMember;
	}

	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}

	public String saveUser() throws IOException {
		Role role= new Role();
		role.setId(idRole);
		user.setRole(role);
		if(userService.updateUser(user)) {
			FacesMessage message= new FacesMessage("Mise à jour effectué avec succés");
			 FacesContext.getCurrentInstance().addMessage(null, message);
			return "members";
		}
		return "";
	}
	
	public String saveProfil() throws IOException {		
		if(userService.updateUser(user)) {
			FacesMessage message= new FacesMessage("Mise à jour effectué avec succés");
			 FacesContext.getCurrentInstance().addMessage(null, message);
			return "profil";
		}
		return "";
	}
	
	
}
