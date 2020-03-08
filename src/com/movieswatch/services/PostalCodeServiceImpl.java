package com.movieswatch.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Postalcode;

/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */


public class PostalCodeServiceImpl implements PostalCodeService{

	//@Inject
	private EntityFinder<Postalcode> finder;
	
	public PostalCodeServiceImpl() {
		this.finder= new EntityFinderImpl<Postalcode>();
	}
	
	@Override
	public Postalcode getByNumber(String number) {
		Map<String, String> param= new HashMap<String, String>();
		param.put("number", number);
		
		return finder.findOneByNamedQuery("Postalcode.findByNumber", new Postalcode(), param);
	}

	
}
