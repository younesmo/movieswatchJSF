package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the personnes_personnages database table.
 * 
 */
@Entity
@Table(name="person_character")
@NamedQuery(name="PersonCharacter.findAll", query="SELECT p FROM PersonCharacter p")
public class PersonCharacter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Character
	@ManyToOne
	@JoinColumn(name="id_character", nullable=false)
	private Character character;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id_person", nullable=false)
	private Person person;

	public PersonCharacter() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}