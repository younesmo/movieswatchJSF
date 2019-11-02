package com.movieswatch.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Codepostaux;

public class CodePostauxServiceImpl implements CodePostauxService{

	//@Inject
	private EntityFinder<Codepostaux> finder;
	
	public CodePostauxServiceImpl() {
		this.finder= new EntityFinderImpl<Codepostaux>();
	}
	
	@Override
	public Codepostaux getByNumber(String number) {
		Map<String, String> param= new HashMap<String, String>();
		param.put("numero", number);
		
		return finder.findOneByNamedQuery("Codepostaux.findByNumber", new Codepostaux(), param);
	}

	
}
