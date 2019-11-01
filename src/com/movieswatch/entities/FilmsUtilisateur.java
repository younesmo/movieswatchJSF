package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the films_utilisateurs database table.
 * 
 */
@Entity
@Table(name="films_utilisateurs")
@NamedQuery(name="FilmsUtilisateur.findAll", query="SELECT f FROM FilmsUtilisateur f")
public class FilmsUtilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FILM_UTILISATEUR", unique=true, nullable=false)
	private int idFilmUtilisateur;

	@Column(name="progression_visionnage")
	private int progressionVisionnage;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="ID_FILM", nullable=false)
	private Film film;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ID_UTILISATEUR", nullable=false)
	private Utilisateur utilisateur;

	public FilmsUtilisateur() {
	}

	public int getIdFilmUtilisateur() {
		return this.idFilmUtilisateur;
	}

	public void setIdFilmUtilisateur(int idFilmUtilisateur) {
		this.idFilmUtilisateur = idFilmUtilisateur;
	}

	public int getProgressionVisionnage() {
		return this.progressionVisionnage;
	}

	public void setProgressionVisionnage(int progressionVisionnage) {
		this.progressionVisionnage = progressionVisionnage;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}