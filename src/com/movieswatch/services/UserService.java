package com.movieswatch.services;

import com.movieswatch.entities.Utilisateur;

public interface UserService {

	void insertUser(Utilisateur user);
	Utilisateur findByEmail(String email);
}
