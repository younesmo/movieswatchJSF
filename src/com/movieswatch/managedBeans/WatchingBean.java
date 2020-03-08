package com.movieswatch.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;
import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;
import com.movieswatch.entities.User;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;
import com.movieswatch.utils.SessionUtils;

@Named
@RequestScoped
public class WatchingBean implements Serializable{
	private List<Movie> movies= new ArrayList();
	private transient OrderService orderService;
	private List<Order> orders;
	
	public WatchingBean() {
		orderService= new OrderServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		User currentUser= SessionUtils.getCurrentUser();
		
		if(currentUser.getUser()== null) {
		orders= orderService.getOrders(currentUser);
		}else {
			orders= orderService.getOrders(currentUser.getUser());	
		}
		for(Order o: orders) {
			for(OrderMovie om: o.getOrderMovies()) {
				MoviesFormat mf= om.getMovie();
				movies.add(mf.getMovie());
			}
		}
	}
	
	public String goToPlay() {
		return "playMovies";
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
