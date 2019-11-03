package com.movieswatch.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.movieswatch.entities.Utilisateur;

public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}


	public static Utilisateur getCurrentUser() {
		HttpSession session = getSession();
		return (Utilisateur) session.getAttribute("currentUser");
	}

	
}
