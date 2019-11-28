package com.movieswatch.services;

import java.util.List;

import com.movieswatch.entities.User;

public interface UserService {

	List<User> getAllUsers();
	void insertUser(User user);
	User findByEmail(String email);
	User getByEmailAndPassword(User user);
	boolean updateUser(User updatedUser);
	boolean deleteUser(int id);

}
