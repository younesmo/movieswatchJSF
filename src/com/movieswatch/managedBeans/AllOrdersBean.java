package com.movieswatch.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.movieswatch.entities.Order;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;

@Named
@RequestScoped
public class AllOrdersBean implements Serializable{

	private List<Order> orders;
	transient private OrderService orderService;
	
	public AllOrdersBean() {
		this.orderService= new OrderServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		orders= orderService.getAllOrders();
	}
	
	/*public String goToBill(String id) {
		return "";
	}*/

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
