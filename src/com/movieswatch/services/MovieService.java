package com.movieswatch.services;

import java.util.List;

import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;

public interface MovieService {

	List<Movie> getMovies();
	Movie getMovieById(int id);
	List<MoviesFormat> getMoviesFormat(int id);
	MoviesFormat getMovieFormat(int id);
}
