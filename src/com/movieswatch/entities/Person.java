package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the personnes database table.
 * 
 */
@Entity
@Table(name="persons")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Lob
	private String biography;

	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	private Date birthdate;

	@Column(length=255)
	private String lastname;

	@Column(length=255)
	private String firstname;

	//bi-directional many-to-one association to MoviePerson
	@OneToMany(mappedBy="person")
	private List<MoviePerson> moviePersons;

	//bi-directional many-to-one association to PersonCharacter
	@OneToMany(mappedBy="person")
	private List<PersonCharacter> personCharacters;

	public Person() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idPersonne) {
		this.id = idPersonne;
	}

	public String getBiography() {
		return this.biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

	public List<MoviePerson> getMoviePersons() {
		return this.moviePersons;
	}

	public void setMoviePersons(List<MoviePerson> moviePersons) {
		this.moviePersons = moviePersons;
	}

	public MoviePerson addMoviePerson(MoviePerson moviePerson) {
		getMoviePersons().add(moviePerson);
		moviePerson.setPerson(this);

		return moviePerson;
	}

	public MoviePerson removeMoviePerson(MoviePerson moviePerson) {
		getMoviePersons().remove(moviePerson);
		moviePerson.setPerson(null);

		return moviePerson;
	}

	public List<PersonCharacter> getPersonCharacters() {
		return this.personCharacters;
	}

	public void setPersonCharacters(List<PersonCharacter> personCharacters) {
		this.personCharacters = personCharacters;
	}

	public PersonCharacter addPersonCharacter(PersonCharacter personCharacter) {
		getPersonCharacters().add(personCharacter);
		personCharacter.setPerson(this);

		return personCharacter;
	}

	public PersonCharacter removePersonCharacter(PersonCharacter personCharacter) {
		getPersonCharacters().remove(personCharacter);
		personCharacter.setPerson(null);

		return personCharacter;
	}

}