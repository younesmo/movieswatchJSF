package com.movieswatch.services;

import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;

import java.util.List;

import com.movieswatch.entities.Movie;
import com.movieswatch.entities.User;

public interface OrderService {
	List<Order> getOrders(User currentUser);
	Order getById(int id);
	Order getCart(User currentUser);
	boolean addMovieInCart(User currentUser, Movie movie);
	boolean deleteFromCart(int idMovieToRemove, User currentUser);
	Order payCart(User currentUser);
}
