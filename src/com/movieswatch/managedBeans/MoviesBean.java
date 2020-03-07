package com.movieswatch.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MovieGenre;
import com.movieswatch.entities.MovieCountry;
import com.movieswatch.entities.MovieCharacter;
import com.movieswatch.entities.MoviePerson;
import com.movieswatch.services.MovieService;
import com.movieswatch.services.MovieServiceImpl;

@Named
@RequestScoped
public class MoviesBean implements Serializable{
	
	private List<Movie> movies;
	transient private MovieService movieService;
	transient private static Logger logger = Logger.getLogger(MoviesBean.class);
	private int idMovie;
	private String type;
	private String keyword;
	
	public MoviesBean() {
		this.movieService= new MovieServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		movies= movieService.getMovies();
	}
	
	
	public void updateList() {
		List<Movie> moviesToSend= new ArrayList<>();

		switch(type) {
		
		case "all" : 
			moviesToSend = movieService.getMovies();
			break;
	
		case "personne": 
			for(Movie movie: movies) {
				for(MoviePerson fp : movie.getMoviePersons()) {
					if(fp.getPerson().getLastname().toLowerCase().contains(keyword.toLowerCase()) 
							|| fp.getPerson().getFirstname().toLowerCase().contains(keyword.toLowerCase()))
						moviesToSend.add(movie);
				}
			}
			break;
		
		case "titre" : 
			for(Movie movie: movies) {
				if(movie.getTitle().toLowerCase().contains(keyword.toLowerCase()))
					moviesToSend.add(movie);
			}
			break;
		
		case "annee" : 
			for(Movie movie: movies) {
				if(movie.getProductionYear().toLowerCase().contains(keyword.toLowerCase()))
					moviesToSend.add(movie);
			}
			break;
			
		case "genre" :
			for(Movie movie: movies) {
				for(MovieGenre fg: movie.getMovieGenres()) {
					if(fg.getGenre().getName().toLowerCase().contains(keyword.toLowerCase()))
						moviesToSend.add(movie);
				}
			}
			break;
		
		case "pays":
			for(Movie movie: movies) {
				for(MovieCountry fp: movie.getMovieCountries()) {
					if(fp.getCountry().getShortName().toLowerCase().contains(keyword.toLowerCase()) 
							|| fp.getCountry().getCountryCode().toLowerCase().contains(keyword.toLowerCase())
							|| fp.getCountry().getNationality().toLowerCase().contains(keyword.toLowerCase()))
						moviesToSend.add(movie);
				}
			}
			break;
			
		case "csa" : 
			for(Movie movie: movies) {
				if(movie.getCsa().getAgeMin().toLowerCase().contains(keyword.toLowerCase()))
					moviesToSend.add(movie);
			}
			break;
		
		case "personnage":
			for(Movie movie: movies) {
				for(MovieCharacter fp : movie.getMovieCharacters()) {
					if(fp.getCharacter().getLastname().toLowerCase().contains(keyword.toLowerCase()) 
							|| fp.getCharacter().getFirstname().toLowerCase().contains(keyword.toLowerCase()))
						moviesToSend.add(movie);
				}
			}
			break;
	}

		movies= moviesToSend;
	}
	
	public String goToAddMovie() {
		return "addMovie";
	}


	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}
	
}
