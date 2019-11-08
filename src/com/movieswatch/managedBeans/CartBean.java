package com.movieswatch.managedBeans;

import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;
import com.movieswatch.entities.User;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;
import com.movieswatch.utils.FactureGeneratorUtils;
import com.movieswatch.utils.JavaMailUtil;
import com.movieswatch.utils.SessionUtils;

@ManagedBean
@Named
public class CartBean implements Serializable{
	
	private Order cart;
	transient private OrderService cartService;
	transient private static Logger logger = Logger.getLogger(CartBean.class);

	
	public CartBean() {
		this.cartService= new OrderServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		User currentUser= SessionUtils.getCurrentUser();
		this.cart= cartService.getCart(currentUser);
	}
	
	public void delete(String id) {
		int idMovie= Integer.valueOf(id);
		OrderMovie movieToRemove = null;

		for(OrderMovie cf : cart.getOrderMovies()) {
			if(cf.getMovie().getId()== idMovie) {
				movieToRemove = cf;
				break;
			}
		}
		
		boolean isMovieDeleted= cartService.deleteFromCart(movieToRemove.getId());
		if(isMovieDeleted)
			cart.removeOrderMovie(movieToRemove);
		else
			logger.debug("error");
			
	}

	public String pay() throws FileNotFoundException {
		ServletContext servletContext = (ServletContext) FacesContext
			    .getCurrentInstance().getExternalContext().getContext();
		User currentUser= SessionUtils.getCurrentUser();
		
		boolean isCartPaid= cartService.payCart(currentUser);
		FactureGeneratorUtils.generateFacture(currentUser, cart, servletContext);
		try {
			JavaMailUtil.sendMail(currentUser.getEmail(), cart, servletContext.getRealPath("/")+"/bills/" +cart.getId()+".pdf");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isCartPaid)
			return "orders";
		else {
			logger.debug("error");
			return "";
		}
	}

	public Order getCart() {
		return cart;
	}

	public void setCart(Order cart) {
		this.cart = cart;
	}
	
	
	
	
}
