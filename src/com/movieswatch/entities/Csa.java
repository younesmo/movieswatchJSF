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

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="csa")
	private List<Film> films;

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

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setCsa(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setCsa(null);

		return film;
	}

}