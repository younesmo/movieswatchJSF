package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commandes database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQueries({@NamedQuery(name="Order.findAll", query="SELECT c FROM Order c"),
	@NamedQuery(name="Order.getCart", query="select c from Order c where c.user.id = :id and c.status =:status"),
	@NamedQuery(name="Order.getPaidOrders", query="select c from Order c where c.status= 'paye'")
})
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

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	@Column(name="payment_mode", length=1)
	private String paymentMode;
	
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
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	

}