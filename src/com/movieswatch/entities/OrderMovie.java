package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commandes_films database table.
 * 
 */
@Entity
@Table(name="order_movie")
@NamedQuery(name="OrderMovie.findAll", query="SELECT c FROM OrderMovie c")
public class OrderMovie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="id_order", nullable=false)
	private Order order;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="id_movie", nullable=false)
	private MoviesFormat movie;

	public OrderMovie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public MoviesFormat getMovie() {
		return this.movie;
	}

	public void setMovie(MoviesFormat movie) {
		this.movie = movie;
	}

}