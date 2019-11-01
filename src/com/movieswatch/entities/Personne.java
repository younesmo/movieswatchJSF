package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the personnes database table.
 * 
 */
@Entity
@Table(name="personnes")
@NamedQuery(name="Personne.findAll", query="SELECT p FROM Personne p")
public class Personne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERSONNE", unique=true, nullable=false)
	private int idPersonne;

	@Lob
	private String biographie;

	@Temporal(TemporalType.DATE)
	@Column(name="date_debut_carriere")
	private Date dateDebutCarriere;

	@Temporal(TemporalType.DATE)
	@Column(name="date_deces")
	private Date dateDeces;

	@Temporal(TemporalType.DATE)
	@Column(name="date_naissance")
	private Date dateNaissance;

	@Column(length=255)
	private String nom;

	@Column(length=255)
	private String prenom;

	//bi-directional many-to-one association to FilmsPersonne
	@OneToMany(mappedBy="personne")
	private List<FilmsPersonne> filmsPersonnes;

	//bi-directional many-to-one association to PersonnesPersonnage
	@OneToMany(mappedBy="personne")
	private List<PersonnesPersonnage> personnesPersonnages;

	public Personne() {
	}

	public int getIdPersonne() {
		return this.idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getBiographie() {
		return this.biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}

	public Date getDateDebutCarriere() {
		return this.dateDebutCarriere;
	}

	public void setDateDebutCarriere(Date dateDebutCarriere) {
		this.dateDebutCarriere = dateDebutCarriere;
	}

	public Date getDateDeces() {
		return this.dateDeces;
	}

	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}

	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<FilmsPersonne> getFilmsPersonnes() {
		return this.filmsPersonnes;
	}

	public void setFilmsPersonnes(List<FilmsPersonne> filmsPersonnes) {
		this.filmsPersonnes = filmsPersonnes;
	}

	public FilmsPersonne addFilmsPersonne(FilmsPersonne filmsPersonne) {
		getFilmsPersonnes().add(filmsPersonne);
		filmsPersonne.setPersonne(this);

		return filmsPersonne;
	}

	public FilmsPersonne removeFilmsPersonne(FilmsPersonne filmsPersonne) {
		getFilmsPersonnes().remove(filmsPersonne);
		filmsPersonne.setPersonne(null);

		return filmsPersonne;
	}

	public List<PersonnesPersonnage> getPersonnesPersonnages() {
		return this.personnesPersonnages;
	}

	public void setPersonnesPersonnages(List<PersonnesPersonnage> personnesPersonnages) {
		this.personnesPersonnages = personnesPersonnages;
	}

	public PersonnesPersonnage addPersonnesPersonnage(PersonnesPersonnage personnesPersonnage) {
		getPersonnesPersonnages().add(personnesPersonnage);
		personnesPersonnage.setPersonne(this);

		return personnesPersonnage;
	}

	public PersonnesPersonnage removePersonnesPersonnage(PersonnesPersonnage personnesPersonnage) {
		getPersonnesPersonnages().remove(personnesPersonnage);
		personnesPersonnage.setPersonne(null);

		return personnesPersonnage;
	}

}