package com.movieswatch.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MovieGenre;
import com.movieswatch.entities.MovieCountry;
import com.movieswatch.entities.MovieCharacter;
import com.movieswatch.entities.MoviePerson;
import com.movieswatch.services.MovieService;
import com.movieswatch.services.MovieServiceImpl;

@Named
@RequestScoped
public class MoviesBean implements Serializable{
	
	private List<Movie> movies;
	transient private MovieService movieService;
	transient private static Logger logger = Logger.getLogger(MoviesBean.class);
	private int idMovie;
	private String type;
	private String keyword;
	
	public MoviesBean() {
		this.movieService= new MovieServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		movies= movieService.getMovies();
	}
	
	public void deleteMovie() throws IOException {
		boolean isMovieDeleted=movieService.deleteMovie(idMovie);
		if(isMovieDeleted) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
		else
			logger.debug("error");
	}
		
	public String goToAddMovie() {
		return "addMovie";
	}


	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}
	
}
