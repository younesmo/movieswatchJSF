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
	private String pays;
	private String genre;
	private String title;
	private String keyword;
	private String type;
	private List<Movie> listFilmTrie;
	private Movie filmTrie;
	transient private MovieService movieService;
	transient private static Logger logger = Logger.getLogger(MoviesBean.class);
	private int idMovie;
	
	public void updateList()
	{
		
		//TITRE
		
		if( !title.equals("") && !pays.equals("") && !genre.equals("") )
		{
			movies=movieService.getMoviesByTitlePaysGenre(title,pays,genre);
		}
		
		else if(!title.equals("") && !pays.equals(""))
		{
			movies=movieService.getMoviesByTitlePays(title,pays);
		}
		
		else if(!title.equals(""))
		{
			movies=movieService.getMoviesByTitle(title);
		}
		
		//Pays
		else if(!pays.equals("") && !genre.equals(""))
		{
			movies=movieService.getMoviesByPaysGenre(pays,genre);
		}
		
		else if(!pays.equals("") && !title.equals(""))
		{
			movies=movieService.getMoviesByPaysTitle(pays,title);
		}
		
		else if(!pays.equals(""))
		{
			movies=movieService.getMoviesByPays(pays);
		}
		
		//Genre
		
		else if(!genre.equals("") && !title.equals("") )
		{
			movies=movieService.getMoviesByGenreTitle(genre,title);
		}
		
		else if(!genre.equals("") && !pays.equals(""))
		{
			movies=movieService.getMoviesByGenrePays(genre,pays);
		}
		
		else if(!genre.equals(""))
		{
			movies=movieService.getMoviesByGenre(genre);
		}
		
	}
	
	
	public MoviesBean() {
		this.movieService= new MovieServiceImpl();
	
	}
	

	
	@PostConstruct
	public void init() {
		movies= movieService.getMovies();
		listFilmTrie = new ArrayList<Movie>();
		filmTrie = new Movie();
		
	}
	
	public void tri()
	{
		for (Movie m : movies) 
		{ 
		    if(m.equals(filmTrie))
		    {
		    	listFilmTrie.add(m);
		    	
		    }
		    
		}
		
		movies=listFilmTrie;
			
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

	
	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}
	
	public String getPays() {
		return pays;
	}



	public void setPays(String pays) {
		this.pays = pays;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
    
	
}
