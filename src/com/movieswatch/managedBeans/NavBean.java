package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.movieswatch.entities.User;
import com.movieswatch.utils.SessionUtils;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


@Named
@SessionScoped
public class NavBean implements Serializable {

	private User user;
	private boolean isChild;
	
	
	public NavBean() {
		user= SessionUtils.getCurrentUser();
		if(user !=null) {
			if(user.getUser()!=null)
				isChild=true;
		}
	}
	
	public String goToLogin() {
		return "login";
	}
	
	public String goToRegistration() {
		return "registration";
	}
	
	public String goToMembers() {
		return "members";
	}
	
	public String goToWelcome() {
		return "welcome";
	}
	
	public String goToMovies() {
		return "movies";
	}
	
	public String goToProfil() {
		return "profil";
	}
	
	public String goToCart() {
		return "cart";
	}
	
	public String goToOrders() {
		return "orders";
	}
	
	public String goToAllOrders() {
		return "allOrders";
	}

	public String goToWatch() {
		return "watch";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setIsChild(boolean isChild) {
		this.isChild = isChild;
	}

	public boolean getIsChild() {
		return isChild;
	}	
	
	
}
