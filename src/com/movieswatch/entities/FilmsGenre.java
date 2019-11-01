package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_genres database table.
 * 
 */
@Entity
@Table(name="films_genres")
@NamedQuery(name="FilmsGenre.findAll", query="SELECT f FROM FilmsGenre f")
public class FilmsGenre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FILM_GENRE", unique=true, nullable=false)
	private int idFilmGenre;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="ID_FILM", nullable=false)
	private Film film;

	//bi-directional many-to-one association to Genre
	@ManyToOne
	@JoinColumn(name="ID_GENRE", nullable=false)
	private Genre genre;

	public FilmsGenre() {
	}

	public int getIdFilmGenre() {
		return this.idFilmGenre;
	}

	public void setIdFilmGenre(int idFilmGenre) {
		this.idFilmGenre = idFilmGenre;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}