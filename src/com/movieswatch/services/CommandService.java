package com.movieswatch.services;

import com.movieswatch.entities.Commande;
import com.movieswatch.entities.Film;
import com.movieswatch.entities.Utilisateur;

public interface CommandService {
	Commande getPanier(Utilisateur currentUser);
	boolean addFilmInPanier(Utilisateur currentUser, Film film);
}
