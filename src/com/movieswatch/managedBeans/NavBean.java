package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NavBean implements Serializable {

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
	
	public String goToFilms() {
		return "films";
	}
	
	public String goToProfil() {
		return "profil";
	}
	
	public String goToPanier() {
		return "panier";
	}
	
	public String goToCommand() {
		return "command";
	}
	
	
}
