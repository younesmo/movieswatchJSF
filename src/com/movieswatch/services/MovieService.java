package com.movieswatch.services;

import java.util.List;

import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;

public interface MovieService {

	List<Movie> getMovies();
	Movie getMovieById(int id);
	List<MoviesFormat> getMoviesFormat(int id);
	MoviesFormat getMovieFormat(int id);
	//TITRE
	List<Movie> getMoviesByTitlePaysGenre(String title,String pays, String genre);
	List<Movie> getMoviesByTitlePays(String title,String pays);
	List<Movie> getMoviesByTitle(String title);
	//Pays
	List<Movie> getMoviesByPaysGenre(String pays,String genre);
	List<Movie> getMoviesByPaysTitle(String pays,String title);
	List<Movie> getMoviesByPays(String pays);
	//Genre
	List<Movie> getMoviesByGenreTitle(String genre,String title);
	List<Movie> getMoviesByGenrePays(String genre,String pays);
	List<Movie> getMoviesByGenre(String genre);
}
