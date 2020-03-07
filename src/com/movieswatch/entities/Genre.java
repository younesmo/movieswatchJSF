package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genres database table.
 * 
 */
@Entity
@Table(name="genres")
@NamedQuery(name="Genre.findAll", query="SELECT G from Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String name;

	//bi-directional many-to-one association to MovieGenre
	@OneToMany(mappedBy="genre")
	private List<MovieGenre> movieGenres;

	public Genre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MovieGenre> getMovieGenres() {
		return this.movieGenres;
	}

	public void setMovieGenres(List<MovieGenre> movieGenres) {
		this.movieGenres = movieGenres;
	}

	public MovieGenre addMovieGenre(MovieGenre movieGenre) {
		getMovieGenres().add(movieGenre);
		movieGenre.setGenre(this);

		return movieGenre;
	}

	public MovieGenre removeMovieGenre(MovieGenre movieGenre) {
		getMovieGenres().remove(movieGenre);
		movieGenre.setGenre(null);

		return movieGenre;
	}

}