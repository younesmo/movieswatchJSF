package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the csa database table.
 * 
 */
@Entity
@Table(name="csa")
@NamedQuery(name="Csa.findAll", query="SELECT c FROM Csa c")
public class Csa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CSA", unique=true, nullable=false)
	private int idCsa;

	@Column(name="age_min", length=1)
	private String ageMin;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="csa")
	private List<Movie> movies;

	public Csa() {
	}

	public int getIdCsa() {
		return this.idCsa;
	}

	public void setIdCsa(int idCsa) {
		this.idCsa = idCsa;
	}

	public String getAgeMin() {
		return this.ageMin;
	}

	public void setAgeMin(String ageMin) {
		this.ageMin = ageMin;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Movie addMovie(Movie movie) {
		getMovies().add(movie);
		movie.setCsa(this);

		return movie;
	}

	public Movie removeMovie(Movie movie) {
		getMovies().remove(movie);
		movie.setCsa(null);

		return movie;
	}

}