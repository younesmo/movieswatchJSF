package com.movieswatch.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;
import com.movieswatch.entities.User;

public class MovieServiceImpl implements MovieService{

	private EntityFinder<Movie> movieFinder;
	private EntityFinder<MoviesFormat> movieFormatFinder;
	
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

	
	

	@Override
	public MoviesFormat getMovieFormat(int id) {
		return movieFormatFinder.findOne(new MoviesFormat(), id);
	}

	
}
