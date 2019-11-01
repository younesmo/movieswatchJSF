package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the personnages database table.
 * 
 */
@Entity
@Table(name="personnages")
@NamedQuery(name="Personnage.findAll", query="SELECT p FROM Personnage p")
public class Personnage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERSONAGE", unique=true, nullable=false)
	private int idPersonage;

	@Column(length=255)
	private String nom;

	@Column(length=255)
	private String prenom;

	//bi-directional many-to-one association to FilmsPersonnage
	@OneToMany(mappedBy="personnage")
	private List<FilmsPersonnage> filmsPersonnages;

	//bi-directional many-to-one association to PersonnesPersonnage
	@OneToMany(mappedBy="personnage")
	private List<PersonnesPersonnage> personnesPersonnages;

	public Personnage() {
	}

	public int getIdPersonage() {
		return this.idPersonage;
	}

	public void setIdPersonage(int idPersonage) {
		this.idPersonage = idPersonage;
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

	public List<FilmsPersonnage> getFilmsPersonnages() {
		return this.filmsPersonnages;
	}

	public void setFilmsPersonnages(List<FilmsPersonnage> filmsPersonnages) {
		this.filmsPersonnages = filmsPersonnages;
	}

	public FilmsPersonnage addFilmsPersonnage(FilmsPersonnage filmsPersonnage) {
		getFilmsPersonnages().add(filmsPersonnage);
		filmsPersonnage.setPersonnage(this);

		return filmsPersonnage;
	}

	public FilmsPersonnage removeFilmsPersonnage(FilmsPersonnage filmsPersonnage) {
		getFilmsPersonnages().remove(filmsPersonnage);
		filmsPersonnage.setPersonnage(null);

		return filmsPersonnage;
	}

	public List<PersonnesPersonnage> getPersonnesPersonnages() {
		return this.personnesPersonnages;
	}

	public void setPersonnesPersonnages(List<PersonnesPersonnage> personnesPersonnages) {
		this.personnesPersonnages = personnesPersonnages;
	}

	public PersonnesPersonnage addPersonnesPersonnage(PersonnesPersonnage personnesPersonnage) {
		getPersonnesPersonnages().add(personnesPersonnage);
		personnesPersonnage.setPersonnage(this);

		return personnesPersonnage;
	}

	public PersonnesPersonnage removePersonnesPersonnage(PersonnesPersonnage personnesPersonnage) {
		getPersonnesPersonnages().remove(personnesPersonnage);
		personnesPersonnage.setPersonnage(null);

		return personnesPersonnage;
	}

}