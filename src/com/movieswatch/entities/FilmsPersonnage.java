package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_personnages database table.
 * 
 */
@Entity
@Table(name="films_personnages")
@NamedQuery(name="FilmsPersonnage.findAll", query="SELECT f FROM FilmsPersonnage f")
public class FilmsPersonnage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FILM_PERSONNAGE", unique=true, nullable=false)
	private int idFilmPersonnage;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="ID_FILM", nullable=false)
	private Film film;

	//bi-directional many-to-one association to Personnage
	@ManyToOne
	@JoinColumn(name="ID_PERSONNAGE", nullable=false)
	private Personnage personnage;

	public FilmsPersonnage() {
	}

	public int getIdFilmPersonnage() {
		return this.idFilmPersonnage;
	}

	public void setIdFilmPersonnage(int idFilmPersonnage) {
		this.idFilmPersonnage = idFilmPersonnage;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Personnage getPersonnage() {
		return this.personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

}