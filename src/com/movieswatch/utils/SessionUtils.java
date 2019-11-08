package com.movieswatch.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.movieswatch.entities.User;

public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}


	public static User getCurrentUser() {
		HttpSession session = getSession();
		return (User) session.getAttribute("currentUser");
	}

	
}
