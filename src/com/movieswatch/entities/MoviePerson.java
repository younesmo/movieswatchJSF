package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_personnes database table.
 * 
 */
@Entity
@Table(name="movie_person")
@NamedQuery(name="MoviePerson.findAll", query="SELECT f FROM MoviePerson f")
public class MoviePerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="id_movie", nullable=false)
	private Movie movie;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person", nullable=false)
	private Person person;
	
	@Column(name="role", length=1)
	private String ageMin;

	public MoviePerson() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idFilmPersonne) {
		this.id = idFilmPersonne;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(String ageMin) {
		this.ageMin = ageMin;
	}
	
	

}