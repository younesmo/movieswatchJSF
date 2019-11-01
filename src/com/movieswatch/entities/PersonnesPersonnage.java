package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the personnes_personnages database table.
 * 
 */
@Entity
@Table(name="personnes_personnages")
@NamedQuery(name="PersonnesPersonnage.findAll", query="SELECT p FROM PersonnesPersonnage p")
public class PersonnesPersonnage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERSONNES_PERSONNAGES", unique=true, nullable=false)
	private int idPersonnesPersonnages;

	//bi-directional many-to-one association to Personnage
	@ManyToOne
	@JoinColumn(name="ID_PERSONAGE", nullable=false)
	private Personnage personnage;

	//bi-directional many-to-one association to Personne
	@ManyToOne
	@JoinColumn(name="ID_PERSONNE", nullable=false)
	private Personne personne;

	public PersonnesPersonnage() {
	}

	public int getIdPersonnesPersonnages() {
		return this.idPersonnesPersonnages;
	}

	public void setIdPersonnesPersonnages(int idPersonnesPersonnages) {
		this.idPersonnesPersonnages = idPersonnesPersonnages;
	}

	public Personnage getPersonnage() {
		return this.personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

}