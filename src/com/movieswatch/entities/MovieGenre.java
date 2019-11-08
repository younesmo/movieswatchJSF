package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_genres database table.
 * 
 */
@Entity
@Table(name="movie_genres")
@NamedQuery(name="MovieGenre.findAll", query="SELECT f FROM MovieGenre f")
public class MovieGenre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="ID_movie", nullable=false)
	private Movie movie;

	//bi-directional many-to-one association to Genre
	@ManyToOne
	@JoinColumn(name="id_genre", nullable=false)
	private Genre genre;

	public MovieGenre() {
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

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}