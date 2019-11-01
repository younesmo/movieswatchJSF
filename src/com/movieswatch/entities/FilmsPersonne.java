package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_personnes database table.
 * 
 */
@Entity
@Table(name="films_personnes")
@NamedQuery(name="FilmsPersonne.findAll", query="SELECT f FROM FilmsPersonne f")
public class FilmsPersonne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FILM_PERSONNE", unique=true, nullable=false)
	private int idFilmPersonne;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="ID_FILM", nullable=false)
	private Film film;

	//bi-directional many-to-one association to Personne
	@ManyToOne
	@JoinColumn(name="ID_PERSONNE", nullable=false)
	private Personne personne;

	public FilmsPersonne() {
	}

	public int getIdFilmPersonne() {
		return this.idFilmPersonne;
	}

	public void setIdFilmPersonne(int idFilmPersonne) {
		this.idFilmPersonne = idFilmPersonne;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

}