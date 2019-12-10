package com.movieswatch.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;

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

	@Override
	public MoviesFormat getMovieFormat(int id) {
		return movieFormatFinder.findOne(new MoviesFormat(), id);
	}

	
}
