package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the films database table.
 * 
 */
@Entity
@Table(name="films")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FILM", unique=true, nullable=false)
	private int idFilm;

	@Column(name="annee_production", length=45)
	private String anneeProduction;

	@Column(length=45)
	private String budget;

	@Column(length=1)
	private String metrage;

	@Column(name="num_isan", length=45)
	private String numIsan;

	@Lob
	private String synopsis;

	@Column(name="titre_original", length=255)
	private String titreOriginal;

	@Column(name="url_affiche", length=255)
	private String urlAffiche;

	//bi-directional many-to-one association to CommandesFilm
	@OneToMany(mappedBy="film")
	private List<CommandesFilm> commandesFilms;

	//bi-directional many-to-one association to Csa
	@ManyToOne
	@JoinColumn(name="ID_CSA", nullable=false)
	private Csa csa;

	//bi-directional many-to-one association to FilmsGenre
	@OneToMany(mappedBy="film")
	private List<FilmsGenre> filmsGenres;

	//bi-directional many-to-one association to FilmsPay
	@OneToMany(mappedBy="film")
	private List<FilmsPay> filmsPays;

	//bi-directional many-to-one association to FilmsPersonnage
	@OneToMany(mappedBy="film")
	private List<FilmsPersonnage> filmsPersonnages;

	//bi-directional many-to-one association to FilmsPersonne
	@OneToMany(mappedBy="film")
	private List<FilmsPersonne> filmsPersonnes;

	//bi-directional many-to-one association to FilmsUtilisateur
	@OneToMany(mappedBy="film")
	private List<FilmsUtilisateur> filmsUtilisateurs;

	public Film() {
	}

	public int getIdFilm() {
		return this.idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public String getAnneeProduction() {
		return this.anneeProduction;
	}

	public void setAnneeProduction(String anneeProduction) {
		this.anneeProduction = anneeProduction;
	}

	public String getBudget() {
		return this.budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getMetrage() {
		return this.metrage;
	}

	public void setMetrage(String metrage) {
		this.metrage = metrage;
	}

	public String getNumIsan() {
		return this.numIsan;
	}

	public void setNumIsan(String numIsan) {
		this.numIsan = numIsan;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getTitreOriginal() {
		return this.titreOriginal;
	}

	public void setTitreOriginal(String titreOriginal) {
		this.titreOriginal = titreOriginal;
	}

	public String getUrlAffiche() {
		return this.urlAffiche;
	}

	public void setUrlAffiche(String urlAffiche) {
		this.urlAffiche = urlAffiche;
	}

	public List<CommandesFilm> getCommandesFilms() {
		return this.commandesFilms;
	}

	public void setCommandesFilms(List<CommandesFilm> commandesFilms) {
		this.commandesFilms = commandesFilms;
	}

	public CommandesFilm addCommandesFilm(CommandesFilm commandesFilm) {
		getCommandesFilms().add(commandesFilm);
		commandesFilm.setFilm(this);

		return commandesFilm;
	}

	public CommandesFilm removeCommandesFilm(CommandesFilm commandesFilm) {
		getCommandesFilms().remove(commandesFilm);
		commandesFilm.setFilm(null);

		return commandesFilm;
	}

	public Csa getCsa() {
		return this.csa;
	}

	public void setCsa(Csa csa) {
		this.csa = csa;
	}

	public List<FilmsGenre> getFilmsGenres() {
		return this.filmsGenres;
	}

	public void setFilmsGenres(List<FilmsGenre> filmsGenres) {
		this.filmsGenres = filmsGenres;
	}

	public FilmsGenre addFilmsGenre(FilmsGenre filmsGenre) {
		getFilmsGenres().add(filmsGenre);
		filmsGenre.setFilm(this);

		return filmsGenre;
	}

	public FilmsGenre removeFilmsGenre(FilmsGenre filmsGenre) {
		getFilmsGenres().remove(filmsGenre);
		filmsGenre.setFilm(null);

		return filmsGenre;
	}

	public List<FilmsPay> getFilmsPays() {
		return this.filmsPays;
	}

	public void setFilmsPays(List<FilmsPay> filmsPays) {
		this.filmsPays = filmsPays;
	}

	public FilmsPay addFilmsPay(FilmsPay filmsPay) {
		getFilmsPays().add(filmsPay);
		filmsPay.setFilm(this);

		return filmsPay;
	}

	public FilmsPay removeFilmsPay(FilmsPay filmsPay) {
		getFilmsPays().remove(filmsPay);
		filmsPay.setFilm(null);

		return filmsPay;
	}

	public List<FilmsPersonnage> getFilmsPersonnages() {
		return this.filmsPersonnages;
	}

	public void setFilmsPersonnages(List<FilmsPersonnage> filmsPersonnages) {
		this.filmsPersonnages = filmsPersonnages;
	}

	public FilmsPersonnage addFilmsPersonnage(FilmsPersonnage filmsPersonnage) {
		getFilmsPersonnages().add(filmsPersonnage);
		filmsPersonnage.setFilm(this);

		return filmsPersonnage;
	}

	public FilmsPersonnage removeFilmsPersonnage(FilmsPersonnage filmsPersonnage) {
		getFilmsPersonnages().remove(filmsPersonnage);
		filmsPersonnage.setFilm(null);

		return filmsPersonnage;
	}

	public List<FilmsPersonne> getFilmsPersonnes() {
		return this.filmsPersonnes;
	}

	public void setFilmsPersonnes(List<FilmsPersonne> filmsPersonnes) {
		this.filmsPersonnes = filmsPersonnes;
	}

	public FilmsPersonne addFilmsPersonne(FilmsPersonne filmsPersonne) {
		getFilmsPersonnes().add(filmsPersonne);
		filmsPersonne.setFilm(this);

		return filmsPersonne;
	}

	public FilmsPersonne removeFilmsPersonne(FilmsPersonne filmsPersonne) {
		getFilmsPersonnes().remove(filmsPersonne);
		filmsPersonne.setFilm(null);

		return filmsPersonne;
	}

	public List<FilmsUtilisateur> getFilmsUtilisateurs() {
		return this.filmsUtilisateurs;
	}

	public void setFilmsUtilisateurs(List<FilmsUtilisateur> filmsUtilisateurs) {
		this.filmsUtilisateurs = filmsUtilisateurs;
	}

	public FilmsUtilisateur addFilmsUtilisateur(FilmsUtilisateur filmsUtilisateur) {
		getFilmsUtilisateurs().add(filmsUtilisateur);
		filmsUtilisateur.setFilm(this);

		return filmsUtilisateur;
	}

	public FilmsUtilisateur removeFilmsUtilisateur(FilmsUtilisateur filmsUtilisateur) {
		getFilmsUtilisateurs().remove(filmsUtilisateur);
		filmsUtilisateur.setFilm(null);

		return filmsUtilisateur;
	}

}