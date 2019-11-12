package com.movieswatch.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.movieswatch.entities.Order;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;
import com.movieswatch.utils.SessionUtils;

@Named
@RequestScoped
public class OrdersBean implements Serializable {

	private List<Order> orders;
	transient private OrderService orderService;
	
	public OrdersBean() {
		this.orderService= new OrderServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		orders= orderService.getOrders(SessionUtils.getCurrentUser());
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
}
