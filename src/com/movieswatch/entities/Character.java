package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the personnages database table.
 * 
 */
@Entity
@Table(name="character")
@NamedQuery(name="Character.findAll", query="SELECT c FROM Character c")
public class Character implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(length=255)
	private String lastname;

	@Column(length=255)
	private String firstname;

	//bi-directional many-to-one association to MovieCharacter
	@OneToMany(mappedBy="character")
	private List<MovieCharacter> movieCharacters;

	//bi-directional many-to-one association to PersonCharacter
	@OneToMany(mappedBy="character")
	private List<PersonCharacter> personCharacters;

	public Character() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idPersonage) {
		this.id = idPersonage;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<MovieCharacter> getMovieCharacters() {
		return this.movieCharacters;
	}

	public void setMovieCharacters(List<MovieCharacter> movieCharacters) {
		this.movieCharacters = movieCharacters;
	}

	public MovieCharacter addMovieCharacters(MovieCharacter movieCharacters) {
		getMovieCharacters().add(movieCharacters);
		movieCharacters.setCharacter(this);

		return movieCharacters;
	}

	public MovieCharacter removeMovieCharacter(MovieCharacter movieCharacter) {
		getMovieCharacters().remove(movieCharacter);
		movieCharacter.setCharacter(null);

		return movieCharacter;
	}

	public List<PersonCharacter> getPersonCharacters() {
		return this.personCharacters;
	}

	public void setPersonCharacters(List<PersonCharacter> personCharacters) {
		this.personCharacters = personCharacters;
	}

	public PersonCharacter addPersonCharacters(PersonCharacter personCharacter) {
		getPersonCharacters().add(personCharacter);
		personCharacter.setCharacter(this);

		return personCharacter;
	}

	public PersonCharacter removePersonCharacters(PersonCharacter personCharacter) {
		getPersonCharacters().remove(personCharacter);
		personCharacter.setCharacter(null);

		return personCharacter;
	}

}