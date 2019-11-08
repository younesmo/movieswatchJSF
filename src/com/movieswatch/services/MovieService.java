package com.movieswatch.services;

import java.util.List;

import com.movieswatch.entities.Movie;

public interface MovieService {

	List<Movie> getMovies();
	Movie getMovieById(int id);
}
