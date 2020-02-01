package com.movieswatch.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;
import com.movieswatch.entities.User;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;
import com.movieswatch.services.MovieService;
import com.movieswatch.services.MovieServiceImpl;
import com.movieswatch.utils.SessionUtils;

@Named
@SessionScoped
public class MovieBean implements Serializable {

	private Movie movie;
	private MoviesFormat movieFormat= new MoviesFormat();
	private List<MoviesFormat> moviesFormat;
	private int idMovie;
	transient private MovieService movieService;
	transient private OrderService orderService;
	transient private static Logger logger = Logger.getLogger(MovieBean.class);
		
	public MovieBean() {
		this.movieService= new MovieServiceImpl();
		this.orderService= new OrderServiceImpl();
	}
	
	@PostConstruct
	public void init() {
	}
	
	public String addInCart() {
		User currentUser= SessionUtils.getCurrentUser();
		boolean isMovieAdded= orderService.addMovieInCart(currentUser, movieFormat);
		if(isMovieAdded)
			return "cart";
		else {
			logger.debug("error");
			return "";
		}
	}

	public String goToMovieDetails() {
		logger.debug(idMovie);
		this.movie= movieService.getMovieById(idMovie);
		this.moviesFormat= movieService.getMoviesFormat(idMovie);
		return "movieDetails";
	}
	
	public void initMovieFormat() {
		logger.debug("entered");
		movieFormat= movieService.getMovieFormat(movieFormat.getId());
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public MoviesFormat getMovieFormat() {
		return movieFormat;
	}

	public void setMovieFormat(MoviesFormat movieFormat) {
		this.movieFormat = movieFormat;
	}

	public List<MoviesFormat> getMoviesFormat() {
		return moviesFormat;
	}

	public void setMoviesFormat(List<MoviesFormat> moviesformat) {
		this.moviesFormat = moviesformat;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}
	
	
}
