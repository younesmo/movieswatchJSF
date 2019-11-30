package com.movieswatch.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.movieswatch.entities.User;
import com.movieswatch.services.UserService;
import com.movieswatch.services.UserServiceImpl;

@RequestScoped
@Named
public class UsersBean implements Serializable{
	
	private List<User> users= new ArrayList<User>();
	private List<User> admins= new ArrayList<User>();
	private List<User> members= new ArrayList<User>();
	private List<User> accounters= new ArrayList<User>();
	transient private UserService userService;
	transient private Logger log= Logger.getLogger(UsersBean.class);
	
	public UsersBean() {
		this.userService= new UserServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		users= userService.getAllUsers();
		sortUsers();
	}
	
	public void sortUsers() {
		for(User user: users) {
			if(user.getRole().getName().equals("User")) 
				members.add(user);
			else 
				if(user.getRole().getName().equals("Admin"))
					admins.add(user);
				else
					accounters.add(user);
		}
	}
	
	public void deleteUser(String id) throws IOException {
		boolean isDone= userService.deleteUser(Integer.valueOf(id));
		if(!isDone)
			log.debug("Remove user: error");
		else {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
	}
	
	public String addMember() {
		return "addMember";
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getAdmins() {
		return admins;
	}

	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<User> getAccounters() {
		return accounters;
	}

	public void setAccounters(List<User> accounters) {
		this.accounters = accounters;
	}
	
	
}
