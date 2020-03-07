package com.movieswatch.services;

import java.util.List;

import com.movieswatch.entities.User;

public interface UserService {

	User getById(int id);
	List<User> getAllUsers();
	List<User> getLinkedAccounts(User mainAccount);
	void insertUser(User user);
	User findByEmail(String email);
	User getByEmailAndPassword(User user);
	boolean updateUser(User updatedUser);
	boolean deleteUser(int id);

}
