package com.movieswatch.services;

import java.util.List;

import com.movieswatch.entities.Film;

public interface FilmService {

	List<Film> getFilms();
	Film getByFilmId(int id);
}
