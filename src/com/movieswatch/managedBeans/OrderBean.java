package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;

@RequestScoped
@Named
public class OrderBean implements Serializable {

	private Order order;
	private int totalPrice;
	private int idOrder;
	transient private OrderService orderService;
	transient private static Logger logger = Logger.getLogger(OrderBean.class);

	
	public OrderBean() {
		this.orderService= new OrderServiceImpl();
	}
	
	public String getDetails() {
		order= orderService.getById(idOrder);
		initTotalPrice();
		return "order";
	}
	
	public void initTotalPrice() {
		for(OrderMovie orderMovies: order.getOrderMovies() ) {
			totalPrice += Integer.valueOf(orderMovies.getMovie().getPrice());
		}		
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	
	
}
