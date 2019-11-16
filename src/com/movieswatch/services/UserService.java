package com.movieswatch.services;

import com.movieswatch.entities.User;

public interface UserService {

	void insertUser(User user);
	User findByEmail(String email);
	User getByEmailAndPassword(User user);
	boolean updateUser(User updatedUser);
}
