package com.movieswatch.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;
import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.User;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


public class MovieServiceImpl implements MovieService{

	private EntityFinder<Movie> movieFinder;
	private EntityFinder<MoviesFormat> movieFormatFinder;
	private EntityManager manager;

	
	public MovieServiceImpl()
	{
		this.movieFinder= new EntityFinderImpl<Movie>();
		this.movieFormatFinder= new EntityFinderImpl<MoviesFormat>();
	}

	@Override
	public List<Movie> getMovies() {
		return movieFinder.findByNamedQuery("Movie.findAll", new Movie(), null);
	}

	@Override
	public Movie getMovieById(int id) {
		return movieFinder.findOne(new Movie(), id);
	}

	@Override
	public List<MoviesFormat> getMoviesFormat(int id) {
		Map<String, Integer> param= new HashMap<String, Integer>();
		param.put("id", id);
		return movieFormatFinder.findByNamedQuery("MoviesFormat.getByMovieId", new MoviesFormat(), param);
	}

	@Override
	public MoviesFormat getMovieFormat(int id) {
		return movieFormatFinder.findOne(new MoviesFormat(), id);
	}

	@Override
	public void addMovie(Movie movie) {
		this.manager= EMF.getEM();
		
		EntityTransaction transac= manager.getTransaction();
		 try {
			 transac.begin();
			 manager.persist(movie);
			 transac.commit();
			 
		 }catch (ConstraintViolationException e) {
			    Set<ConstraintViolation<?>> embeddedConstraintViolations = e.getConstraintViolations();
			    for (ConstraintViolation details : embeddedConstraintViolations) {
			        String message = details.getMessage();
			        System.err.println(message);
			    }
		 }
		 finally {
			 if(transac.isActive())
				 transac.rollback();
			 manager.clear();
			 manager.close();
		 }
	}
	
	@Override
	public boolean deleteMovie(int id) {
		boolean isDeleted= false;
		manager= EMF.getEM();
		EntityTransaction transac= manager.getTransaction();
		try {
			 transac.begin();
			 Movie movieToDel= manager.find(Movie.class, id);
			 manager.remove(movieToDel);
			 transac.commit();
			 isDeleted= true;
		 }finally{
			 if(transac.isActive())
				 transac.rollback();
			 manager.clear();
			 manager.close();
		 }
		return isDeleted;
	}

}
