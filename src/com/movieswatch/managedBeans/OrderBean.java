package com.movieswatch.managedBeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Order;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;

@RequestScoped
@Named
public class OrderBean implements Serializable {

	private Order order;
	transient private OrderService orderService;
	transient private static Logger logger = Logger.getLogger(OrderBean.class);

	
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
