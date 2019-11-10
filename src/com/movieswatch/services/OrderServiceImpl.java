package com.movieswatch.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;
import com.movieswatch.entities.Bill;
import com.movieswatch.entities.Movie;
import com.movieswatch.entities.User;

public class OrderServiceImpl implements OrderService {

    private EntityFinder<Order> orderFinder;
    private EntityFinder<OrderMovie> orderMovieFinder;
	private EntityManager em;		


    public OrderServiceImpl() {
    	this.orderFinder= new EntityFinderImpl<Order>();
    	this.orderMovieFinder= new EntityFinderImpl<OrderMovie>();
    }
	@Override
	public Order getCart(User currentUser) {
    	this.em= EMF.getEM();
		Map param= new HashMap();
		param.put("id", currentUser.getId());
		param.put("status","non-paye");
		Order cart= orderFinder.findOneByNamedQuery("Order.getCart", new Order(), param);
		
		if(cart==null) {
			cart= new Order();
			cart.setUser(currentUser);
			cart.setBill(null);
			cart.setStatus("non-paye");
			EntityTransaction transac= em.getTransaction();
			try {
				transac.begin();
				em.persist(cart);
				transac.commit();
				}
			finally {
				if(transac.isActive())
					transac.rollback();
				em.clear();
				em.close();
			}
		}
		return cart;
	}
	@Override
	public boolean addMovieInCart(User currentUser, Movie movie) {
    	this.em= EMF.getEM();
		boolean movieAdded= false;
		OrderMovie om= new OrderMovie();
		Order cart= getCart(currentUser);
		om.setOrder(cart);
		om.setMovie(movie);
		EntityTransaction transac= em.getTransaction();
		try {
			transac.begin();
			em.persist(em.merge(om));
			cart.addOrderMovie(om);
			em.merge(cart);
			transac.commit();
			movieAdded= true;
			}
		finally {
			if(transac.isActive())
				transac.rollback();
			em.clear();
			em.close();
			}	
		return movieAdded;
		}
	
	
	public boolean deleteFromCart(int idMovieToRemove, User currentUser) {
    	this.em= EMF.getEM();
		boolean movieDeleted= false;
		Order cart= getCart(currentUser);
		OrderMovie itemToRemove= null;
		
		for(OrderMovie cf : cart.getOrderMovies()) {
			if(cf.getMovie().getId()== idMovieToRemove) {
				itemToRemove = cf;
				break;
			}
		}
		
		EntityTransaction transac= em.getTransaction();
		try {
			transac.begin();
			em.remove(em.merge(itemToRemove));
			cart.removeOrderMovie(itemToRemove);
			em.merge(cart);
			transac.commit();
			movieDeleted= true;
		}
		finally {
			if(transac.isActive()) {
				transac.rollback();
			}
			em.clear();
			em.close();
		}
		return movieDeleted;
	}
	
	@Override
	public boolean payCart(User currentUser) {
    	this.em= EMF.getEM();
		boolean cartPaid= false;
		Order cart= getCart(currentUser);
		Bill b= new Bill();
		EntityTransaction transac= em.getTransaction();
		
		try {
			transac.begin();
			
			if(cart!=null) {

				b.setDate(Date.valueOf(LocalDate.now()));
				cart.setBill(b);
				cart.setStatus("paye");
				em.merge(cart);
			}
			transac.commit();
			cartPaid= true;
		}
		finally {
			if(transac.isActive()) {
				transac.rollback();
			}
			em.clear();
			em.close();
		}
		return cartPaid;
	}
	
	

}
