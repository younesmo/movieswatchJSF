package com.movieswatch.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;
import com.movieswatch.entities.Movie;
import com.movieswatch.entities.User;

public class OrderServiceImpl implements OrderService {

    private EntityFinder<Order> orderFinder;
    private EntityManager em;		
    private Logger log= Logger.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl() {
    	this.orderFinder= new EntityFinderImpl<Order>();
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
			cart.setDate(null);
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
	public Order payCart(User currentUser) {
    	this.em= EMF.getEM();
		boolean cartPaid= false;
		Order cart= getCart(currentUser);
		EntityTransaction transac= em.getTransaction();
		
		try {
			transac.begin();
			
			if(cart!=null) {

				cart.setDate(Date.valueOf(LocalDate.now()));;
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
		if(cartPaid)
			return cart;
		else
			return null;
	}
	
	@Override
	public List<Order> getOrders(User currentUser) {
		Map param= new HashMap();
		param.put("id", currentUser.getId());
		param.put("status","paye");
		
		return orderFinder.findByNamedQuery("Order.getCart", new Order(), param);
	}
	
	@Override
	public Order getById(int id) {
		return orderFinder.findOne(new Order(), id);
	}
	@Override
	public List<Order> getAllOrders() {
		return orderFinder.findByNamedQuery("Order.getPaidOrders", new Order(), null);
	}
	
	

}
