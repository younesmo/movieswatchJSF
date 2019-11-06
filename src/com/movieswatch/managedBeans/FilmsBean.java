package com.movieswatch.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Film;
import com.movieswatch.entities.FilmsGenre;
import com.movieswatch.entities.FilmsPay;
import com.movieswatch.entities.FilmsPersonnage;
import com.movieswatch.entities.FilmsPersonne;
import com.movieswatch.services.FilmService;
import com.movieswatch.services.FilmServiceImpl;

@Named
@RequestScoped
public class FilmsBean implements Serializable{
	
	private List<Film> films;
	transient private FilmService filmService;
	transient private static Logger logger = Logger.getLogger(FilmsBean.class);
	private int idFilm;
	private String type;
	private String keyword;
	
	public FilmsBean() {
		this.filmService= new FilmServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		films= filmService.getFilms();
	}
	
	public void updateList() {
		List<Film> filmListToSend= new ArrayList<>();

		switch(type) {
		
		case "all" : 
			filmListToSend = films;
			break;
	
		case "personne": 
			for(Film film: films) {
				for(FilmsPersonne fp : film.getFilmsPersonnes()) {
					if(fp.getPersonne().getNom().toLowerCase().contains(keyword.toLowerCase()) 
							|| fp.getPersonne().getPrenom().toLowerCase().contains(keyword.toLowerCase()))
						filmListToSend.add(film);
				}
			}
			break;
		
		case "titre" : 
			for(Film film: films) {
				if(film.getTitreOriginal().toLowerCase().contains(keyword.toLowerCase()))
					filmListToSend.add(film);
			}
			break;
		
		case "annee" : 
			for(Film film: films) {
				if(film.getAnneeProduction().toLowerCase().contains(keyword.toLowerCase()))
					filmListToSend.add(film);
			}
			break;
			
		case "genre" :
			for(Film film: films) {
				for(FilmsGenre fg: film.getFilmsGenres()) {
					if(fg.getGenre().getNom().toLowerCase().contains(keyword.toLowerCase()))
						filmListToSend.add(film);
				}
			}
			break;
		
		case "pays":
			for(Film film: films) {
				for(FilmsPay fp: film.getFilmsPays()) {
					if(fp.getPay().getFormeCourte().toLowerCase().contains(keyword.toLowerCase()) 
							|| fp.getPay().getCodePays().toLowerCase().contains(keyword.toLowerCase())
							|| fp.getPay().getNationalite().toLowerCase().contains(keyword.toLowerCase()))
						filmListToSend.add(film);
				}
			}
			break;
			
		case "csa" : 
			for(Film film: films) {
				if(film.getCsa().getAgeMin().toLowerCase().contains(keyword.toLowerCase()))
					filmListToSend.add(film);
			}
			break;
		
		case "personnage":
			for(Film film: films) {
				for(FilmsPersonnage fp : film.getFilmsPersonnages()) {
					if(fp.getPersonnage().getNom().toLowerCase().contains(keyword.toLowerCase()) 
							|| fp.getPersonnage().getPrenom().toLowerCase().contains(keyword.toLowerCase()))
						filmListToSend.add(film);
				}
			}
			break;
	}

		films= filmListToSend;
	}
	
	public String goToFilmDetails(String idFilm) {
		logger.debug(idFilm);
		this.idFilm= Integer.parseInt(idFilm);
		return "filmDetails";
	}
	
	public String goToAddFilm() {
		return "addFIlm";
	}
	
	public List<Film> getFilms() {
		return films;
	}
	
	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public FilmService getFilmService() {
		return filmService;
	}

	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
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

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}
	
	
	
	
	
	
}
