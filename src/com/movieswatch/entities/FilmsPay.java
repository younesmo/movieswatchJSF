package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_pays database table.
 * 
 */
@Entity
@Table(name="films_pays")
@NamedQuery(name="FilmsPay.findAll", query="SELECT f FROM FilmsPay f")
public class FilmsPay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FILM_PAYS", unique=true, nullable=false)
	private int idFilmPays;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="ID_FILM", nullable=false)
	private Film film;

	//bi-directional many-to-one association to Pay
	@ManyToOne
	@JoinColumn(name="ID_PAYS", nullable=false)
	private Pay pay;

	public FilmsPay() {
	}

	public int getIdFilmPays() {
		return this.idFilmPays;
	}

	public void setIdFilmPays(int idFilmPays) {
		this.idFilmPays = idFilmPays;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Pay getPay() {
		return this.pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

}