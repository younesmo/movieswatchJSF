package com.movieswatch.services;

import java.util.List;

import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Movie;

public class MovieServiceImpl implements MovieService{

	private EntityFinder<Movie> movieFinder;
	
	public MovieServiceImpl()
	{
		this.movieFinder= new EntityFinderImpl<Movie>();
	}

	@Override
	public List<Movie> getMovies() {
		return movieFinder.findByNamedQuery("Movie.findAll", new Movie(), null);
	}

	@Override
	public Movie getMovieById(int id) {
		return movieFinder.findOne(new Movie(), id);
	}

}
