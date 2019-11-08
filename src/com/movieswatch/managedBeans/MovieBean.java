package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Movie;
import com.movieswatch.entities.User;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;
import com.movieswatch.services.MovieService;
import com.movieswatch.services.MovieServiceImpl;
import com.movieswatch.utils.SessionUtils;

@Named
@RequestScoped
public class MovieBean implements Serializable {

	private Movie movie;
	transient private MovieService movieService;
	transient private OrderService orderService;
	transient private static Logger logger = Logger.getLogger(MovieBean.class);
	
	private int idMovie;
	
	public MovieBean() {
		logger.debug(idMovie);
		this.movieService= new MovieServiceImpl();
		this.orderService= new OrderServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		this.movie= movieService.getMovieById(idMovie);
	}
	
	public String addInCart() {
		User currentUser= SessionUtils.getCurrentUser();
		boolean isMovieAdded= orderService.addMovieInCart(currentUser, movie);
		if(isMovieAdded)
			return "cart";
		else {
			logger.debug("error");
			return "";
		}
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}	
	
}
