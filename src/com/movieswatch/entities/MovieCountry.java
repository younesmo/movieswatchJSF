package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_pays database table.
 * 
 */
@Entity
@Table(name="movie_country")
@NamedQuery(name="MovieCountry.findAll", query="SELECT f FROM MovieCountry f")
public class MovieCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="ID_movie", nullable=false)
	private Movie movie;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="ID_country", nullable=false)
	private Country country;

	public MovieCountry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}