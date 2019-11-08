package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_personnages database table.
 * 
 */
@Entity
@Table(name="movie_character")
@NamedQuery(name="MovieCharacter.findAll", query="SELECT f FROM MovieCharacter f")
public class MovieCharacter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="id_movie", nullable=false)
	private Movie movie;

	//bi-directional many-to-one association to Character
	@ManyToOne
	@JoinColumn(name="id_character", nullable=false)
	private Character character;

	public MovieCharacter() {
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

	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

}