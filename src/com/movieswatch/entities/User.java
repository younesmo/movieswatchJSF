package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findById", query="SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name="User.findByEmail", query="SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name="User.connexion", query="SELECT u FROM User u where u.email = :email and u.password = :password ")})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(name="street_name",length=255)
	@NotNull(message = "Veuillez saisir une rue")
	private String streetName;

	@Temporal(TemporalType.DATE)
	@Column(name="birthdate", nullable=false)
	@NotNull(message = "Veuillez saisir une date de naissance")
	private Date birthdate;

	@Column(nullable=false, length=255)
	@Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Merci de saisir une adresse mail valide" )
	@NotNull(message = "Veuillez saisir une adresse email")
	private String email;

	@Column(length=255)
	@NotNull(message = "Veuillez saisir un lastname")
	private String lastname;

	@Column(name="mobile_number", length=45)
	@NotNull(message = "Veuillez saisir un numero de telephone")
	private String mobileNumber;

	@Column(nullable=false, length=255)
	@NotNull(message = "Veuillez saisir un mot de passe")
	private String password;

	@Column(length=255)
	@NotNull
	private String firstname;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user")
	private List<Order> order;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ID_ROLE", nullable=false)
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="REFERE")
	private User user;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="user")
	private List<User> users;

	//bi-directional many-to-one association to Postalcode
	@ManyToOne
	@JoinColumn(name="id_postalcode", nullable=false)
	private Postalcode postalcode;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetname) {
		this.streetName = streetname;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String numMobile) {
		this.mobileNumber = numMobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<Order> getOrders() {
		return this.order;
	}

	public void setOrders(List<Order> order) {
		this.order = order;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUser(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUser(null);

		return user;
	}

	public Postalcode getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(Postalcode postalcode) {
		this.postalcode = postalcode;
	}

}