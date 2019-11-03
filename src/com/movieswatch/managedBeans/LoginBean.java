package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.movieswatch.entities.Utilisateur;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;
import com.movieswatch.utils.SessionUtils;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private Utilisateur user;
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	transient private UserService userService;
	
	public LoginBean() {}
	
	@PostConstruct
	public void init() {
		this.user= new Utilisateur();
		this.userService= new UserServiceImpl();
	}
	
	public String connectUser() {
		Utilisateur user2 = userService.getByEmailAndPassword(user);
		if (user2 !=null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("currentUser", user2);
			return "welcome";
		} else {
			FacesMessage message= new FacesMessage("Credentials incorrect");
			 FacesContext.getCurrentInstance().addMessage(null, message);
			
			return "login";
		}
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}
