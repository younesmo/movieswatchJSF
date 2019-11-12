package com.movieswatch.managedBeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Order;
import com.movieswatch.entities.User;
import com.movieswatch.services.OrderService;
import com.movieswatch.services.OrderServiceImpl;
import com.movieswatch.utils.FactureGeneratorUtils;
import com.movieswatch.utils.JavaMailUtil;
import com.movieswatch.utils.SessionUtils;

@Named
@RequestScoped
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
	
	public void delete(String id) throws IOException {
		boolean isMovieDeleted= cartService.deleteFromCart(Integer.valueOf(id), SessionUtils.getCurrentUser());
		if(isMovieDeleted) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
		else
			logger.debug("error");
			
	}

	public String pay() throws IOException {
		ServletContext servletContext = (ServletContext) FacesContext
			    .getCurrentInstance().getExternalContext().getContext();
		User currentUser= SessionUtils.getCurrentUser();
		
		cart = cartService.payCart(currentUser);
		if(cart!=null) {
			FactureGeneratorUtils.generateFacture(currentUser, cart, servletContext);
			try {
				JavaMailUtil.sendMail(currentUser.getEmail(), cart, servletContext.getRealPath("/")+"/bills/" +cart.getId()+".pdf");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return "orders";
		}
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
