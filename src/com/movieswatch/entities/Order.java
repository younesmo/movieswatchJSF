package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the commandes database table.
 * 
 */
@Entity
@Table(name="order")
@NamedQueries({@NamedQuery(name="Order.findAll", query="SELECT c FROM Order c"),
	@NamedQuery(name="Order.getPanier", query="select c from Order c where c.user.id = :id and c.status =:status")})
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(length=1)
	private String status;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user", nullable=false)
	private User user;

	//bi-directional many-to-one association to Bill
	@ManyToOne
	@JoinColumn(name="id_bill")
	private Bill bill;

	//bi-directional many-to-one association to OrderMovie
	@OneToMany(mappedBy="order")
	private List<OrderMovie> orderMovies;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idCommande) {
		this.id = idCommande;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public List<OrderMovie> getOrderMovies() {
		return this.orderMovies;
	}

	public void setOrderMovies(List<OrderMovie> orderMovies) {
		this.orderMovies = orderMovies;
	}

	public OrderMovie addOrderMovie(OrderMovie orderMovie) {
		getOrderMovies().add(orderMovie);
		orderMovie.setOrder(this);

		return orderMovie;
	}

	public OrderMovie removeOrderMovie(OrderMovie orderMovie) {
		getOrderMovies().remove(orderMovie);
		orderMovie.setOrder(null);

		return orderMovie;
	}

}