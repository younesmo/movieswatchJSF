package com.movieswatch.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.User;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;
import com.movieswatch.utils.SessionUtils;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


@Named
@ViewScoped
public class EditProfilBean implements Serializable{
	
	private User user;
	private int idProfil;
	private List<User> childAccounts;
	private Postalcode cp;
	transient private UserService userService;
	transient private Logger log= Logger.getLogger(EditProfilBean.class);

	public EditProfilBean() {
		this.userService= new UserServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		user= SessionUtils.getCurrentUser();
		cp= user.getPostalcode();
		childAccounts= userService.getLinkedAccounts(user);
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
	
	public void deleteProfil() throws IOException {
		boolean isDone= userService.deleteUser(idProfil);
		if(!isDone)
			log.debug("Remove profil: error");
		else {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
	}
	
	public String goToEdit() {
		return "editProfil";
	}
	
	public String goToUpdateProfil() {
		return "updateProfil";		
	}
	
	public String goToAddProfil(){
		return "addProfil";
	}
	
	public String goToEditPassword() {
		return "editPassword";
	}

	public List<User> getChildAccounts() {
		return childAccounts;
	}

	public void setChildAccounts(List<User> childAccounts) {
		this.childAccounts = childAccounts;
	}

	public int getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}

	
	
}
