package com.movieswatch.services;

import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;
import com.movieswatch.entities.Movie;
import com.movieswatch.entities.User;

public interface OrderService {
	Order getCart(User currentUser);
	boolean addMovieInCart(User currentUser, Movie movie);
	boolean deleteFromCart(int idMovieToRemove, User currentUser);
	boolean payCart(User currentUser);
}
