package com.movieswatch.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Format;
import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;
import com.movieswatch.entities.Postalcode;
import com.movieswatch.entities.User;
import com.movieswatch.managedBeans.EditProfilBean;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


public class MovieServiceImpl implements MovieService{

	private EntityFinder<Movie> movieFinder;
	transient private Logger log= Logger.getLogger(MovieServiceImpl.class);

	private EntityFinder<Format> formatFinder;
	private EntityFinder<MoviesFormat> movieFormatFinder;
	private EntityManager manager;

	
	public MovieServiceImpl()
	{
		this.movieFinder= new EntityFinderImpl<Movie>();
		this.movieFormatFinder= new EntityFinderImpl<MoviesFormat>();
		this.formatFinder= new EntityFinderImpl<Format>();
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
	public void addMovie(Movie movie, List<MoviesFormat> formats) {
		this.manager= EMF.getEM();
		
		EntityTransaction transac= manager.getTransaction();
		 try {
			 transac.begin();
			 
			 manager.persist(movie);
			 manager.flush();

			 for(MoviesFormat mf: formats) {
				 log.debug(mf.getFormat().getId());
				 Format f= formatFinder.findOne(new Format(), mf.getFormat().getId());
				 mf.setFormat(f);
				 mf.setMovie(movie);;

				 movie.addMoviesFormat(mf);
				 f.addMoviesFormat(mf);
				 
				 manager.persist(mf);
			 }
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
	
	//Titre
	
		@Override
		public List<Movie> getMoviesByTitlePaysGenre(String title, String pays, String genre) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("title",title);
			param.put("pays",pays);
			param.put("genre",genre);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaTitlePaysGenre", new Movie(), param);
		}
		
		@Override
		public List<Movie> getMoviesByTitlePays(String title, String pays) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("title",title);
			param.put("pays",pays);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaTitlePays", new Movie(), param);
		}
		
		@Override
		public List<Movie> getMoviesByTitle(String title) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("title",title);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaTitle", new Movie(), param);
		}
		
		//Pays
		@Override
		public List<Movie> getMoviesByPaysGenre(String pays,String genre) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("pays",pays);
			param.put("genre",genre);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaPaysGenre", new Movie(), param);
		}
		
		@Override
		public List<Movie> getMoviesByPaysTitle(String pays,String title) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("pays",pays);
			param.put("genre",title);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaPaysTitle", new Movie(), param);
		}
		
		@Override
		public List<Movie> getMoviesByPays(String pays) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("pays",pays);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaPays", new Movie(), param);
		}
		
		//Genre
		@Override
		public List<Movie> getMoviesByGenreTitle(String genre, String title) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("genre",genre);
			param.put("title",title);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaGenreTitle", new Movie(), param);
		}
		
		@Override
		public List<Movie> getMoviesByGenrePays(String genre, String pays) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("genre",genre);
			param.put("pays",pays);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaGenrePays", new Movie(), param);
		}
		
		@Override
		public List<Movie> getMoviesByGenre(String genre) {
			Map<String,String> param= new HashMap<String, String>();
			param.put("genre",genre);
			return movieFinder.findByNamedQuery("Movie.findByCriteriaGenre", new Movie(), param);
		}

}
