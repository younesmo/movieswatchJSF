package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the formats database table.
 * 
 */
@Entity
@Table(name="formats")
@NamedQuery(name="Format.findAll", query="SELECT f FROM Format f")
public class Format implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String name;

	//bi-directional many-to-one association to MoviesFormat
	@OneToMany(mappedBy="format")
	private List<MoviesFormat> moviesFormats;

	public Format() {
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

	public List<MoviesFormat> getMoviesFormats() {
		return this.moviesFormats;
	}

	public void setMoviesFormats(List<MoviesFormat> moviesFormats) {
		this.moviesFormats = moviesFormats;
	}

	public MoviesFormat addMoviesFormat(MoviesFormat moviesFormat) {
		getMoviesFormats().add(moviesFormat);
		moviesFormat.setFormat(this);

		return moviesFormat;
	}

	public MoviesFormat removeMoviesFormat(MoviesFormat moviesFormat) {
		getMoviesFormats().remove(moviesFormat);
		moviesFormat.setFormat(null);

		return moviesFormat;
	}

}