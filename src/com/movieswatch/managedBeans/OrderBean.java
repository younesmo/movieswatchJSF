package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.movieswatch.entities.Order;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;

@SessionScoped
@Named
public class OrderBean implements Serializable {

	private Order order;
	transient private OrderService orderService;
	
	public OrderBean() {
		this.orderService= new OrderServiceImpl();
	}
	
	public String getDetails(String id) {
		order= orderService.getById(Integer.valueOf(id));
		return "order";
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
	
}
