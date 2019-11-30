package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the codepostaux database table.
 * 
 */
@Entity
@Table(name="postalcodes")
@NamedQueries({@NamedQuery(name="Postalcode.findAll", query="SELECT c FROM Postalcode c"),
@NamedQuery(name="Postalcode.findByNumber", query="SELECT c FROM Postalcode  c where c.number=:number")})
public class Postalcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(name="city_name", length=255)
	private String city_name;

	@Column(length=5)	
	@NotNull(message="Veuillez saisir un code postal")
	private String number;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="id_country", nullable=false)
	private Country country;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="postalcode")
	private List<User> users;

	public Postalcode() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}



}