package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movies_format database table.
 * 
 */
@Entity
@Table(name="movies_format")
@NamedQuery(name="MoviesFormat.findAll", query="SELECT m FROM MoviesFormat m")
public class MoviesFormat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String price;

	//bi-directional many-to-one association to Format
	@ManyToOne
	@JoinColumn(name="id_format", nullable=false)
	private Format format;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="id_movie", nullable=false)
	private Movie movie;

	public MoviesFormat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Format getFormat() {
		return this.format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}