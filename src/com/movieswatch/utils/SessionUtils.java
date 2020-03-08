package com.movieswatch.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.movieswatch.entities.User;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}


	public static User getCurrentUser() {
		HttpSession session = getSession();
		return (User) session.getAttribute("currentUser");
	}

	public static void setCurrentUser(User currentUser) {
		HttpSession session = getSession();
		session.setAttribute("currentUser", currentUser);
	}
}
