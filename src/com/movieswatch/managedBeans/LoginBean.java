package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Utilisateur;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;
import com.movieswatch.utils.SessionUtils;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private Utilisateur user;
	transient private static Logger logger = Logger.getLogger(LoginBean.class);

	
	//@Inject
	transient private UserService userService;
	
	public LoginBean() {
		this.userService= new UserServiceImpl();
		this.user= new Utilisateur();
	}
	
	@PostConstruct
	public void init() {
	}
	
	public String connectUser() {
		if(userService==null) {
			userService= new UserServiceImpl();
			logger.debug("userService= null");
		}
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
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
