package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Film;
import com.movieswatch.entities.Utilisateur;
import com.movieswatch.services.CommandService;
import com.movieswatch.services.CommandServiceImpl;
import com.movieswatch.services.FilmService;
import com.movieswatch.services.FilmServiceImpl;
import com.movieswatch.utils.SessionUtils;

@Named
@RequestScoped
public class FilmBean implements Serializable {

	private Film film;
	transient private FilmService filmService;
	transient private CommandService commandService;
	transient private static Logger logger = Logger.getLogger(FilmBean.class);
	
	@ManagedProperty(value = "#{filmsBean.idFilm}")
	private int idFilm;
	
	public FilmBean() {
		logger.debug(idFilm);
		this.filmService= new FilmServiceImpl();
		this.commandService= new CommandServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		this.film= filmService.getByFilmId(idFilm);
	}
	
	public String sendToPanier() {
		Utilisateur currentUser= SessionUtils.getCurrentUser();
		boolean isFilmAdded= commandService.addFilmInPanier(currentUser, film);
		if(isFilmAdded)
			return "panier";
		else
			return "error";
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public FilmService getFilmService() {
		return filmService;
	}

	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}
	
	
	
}
