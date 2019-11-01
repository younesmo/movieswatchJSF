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
@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_GENRE", unique=true, nullable=false)
	private int idGenre;

	@Column(length=45)
	private String nom;

	//bi-directional many-to-one association to FilmsGenre
	@OneToMany(mappedBy="genre")
	private List<FilmsGenre> filmsGenres;

	public Genre() {
	}

	public int getIdGenre() {
		return this.idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<FilmsGenre> getFilmsGenres() {
		return this.filmsGenres;
	}

	public void setFilmsGenres(List<FilmsGenre> filmsGenres) {
		this.filmsGenres = filmsGenres;
	}

	public FilmsGenre addFilmsGenre(FilmsGenre filmsGenre) {
		getFilmsGenres().add(filmsGenre);
		filmsGenre.setGenre(this);

		return filmsGenre;
	}

	public FilmsGenre removeFilmsGenre(FilmsGenre filmsGenre) {
		getFilmsGenres().remove(filmsGenre);
		filmsGenre.setGenre(null);

		return filmsGenre;
	}

}