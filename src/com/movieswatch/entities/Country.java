package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pays database table.
 * 
 */
@Entity
@Table(name="country")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(name="country_code", length=45)
	private String countryCode;

	@Column(name="short_name", length=255)
	private String shortName;

	@Column(length=255)
	private String nationality;

	//bi-directional many-to-one association to Postalcode
	@OneToMany(mappedBy="country")
	private List<Postalcode> postalcodes;

	//bi-directional many-to-one association to MovieCountry
	@OneToMany(mappedBy="country")
	private List<MovieCountry> filmsPays;

	public Country() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Postalcode> getPostalCodes() {
		return this.postalcodes;
	}

	public void setPostalCodes(List<Postalcode> postalcodes) {
		this.postalcodes = postalcodes;
	}

	public Postalcode addPostalCode(Postalcode postalcode) {
		getPostalCodes().add(postalcode);
		postalcode.setCountry(this);

		return postalcode;
	}

	public Postalcode removePostalCode(Postalcode postalcode) {
		getPostalCodes().remove(postalcode);
		postalcode.setCountry(null);

		return postalcode;
	}

	public List<MovieCountry> getMoviesCountry() {
		return this.filmsPays;
	}

	public void setMoviesCountry(List<MovieCountry> movieCountry) {
		this.filmsPays = movieCountry;
	}

	public MovieCountry addMovieCountry(MovieCountry movieCountry) {
		getMoviesCountry().add(movieCountry);
		movieCountry.setCountry(this);

		return movieCountry;
	}

	public MovieCountry removeMovieCountry(MovieCountry movieCountry) {
		getMoviesCountry().remove(movieCountry);
		movieCountry.setCountry(null);

		return movieCountry;
	}

}