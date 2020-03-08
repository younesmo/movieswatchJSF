package com.movieswatch.managedBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PlayBean implements Serializable{

	private String title;
	private String titleMovie;
	
	public PlayBean() {
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getTitleMovie() {
		return titleMovie;
	}


	public void setTitleMovie(String titleMovie) {
		this.titleMovie = titleMovie;
	}
	
	


	
	
		
}
