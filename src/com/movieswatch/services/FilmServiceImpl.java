package com.movieswatch.services;

import java.util.List;

import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Film;

public class FilmServiceImpl implements FilmService{

	private EntityFinder<Film> filmFinder;
	
	public FilmServiceImpl()
	{
		this.filmFinder= new EntityFinderImpl<Film>();
	}

	@Override
	public List<Film> getFilms() {
		return filmFinder.findByNamedQuery("Film.findAll", new Film(), null);
	}

	@Override
	public Film getByFilmId(int id) {
		return filmFinder.findOne(new Film(), id);
	}

}
