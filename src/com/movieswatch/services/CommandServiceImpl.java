package com.movieswatch.services;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Commande;
import com.movieswatch.entities.CommandesFilm;
import com.movieswatch.entities.Film;
import com.movieswatch.entities.Utilisateur;

public class CommandServiceImpl implements CommandService {

    private EntityFinder<Commande> commandFinder;
	private EntityManager em;		


    public CommandServiceImpl() {
    	this.em= EMF.getEM();
    	this.commandFinder= new EntityFinderImpl<Commande>();
    }
	@Override
	public Commande getPanier(Utilisateur currentUser) {
		Map param= new HashMap();
		param.put("id", currentUser.getIdUtilisateur());
		param.put("status","non-paye");
		Commande panier= commandFinder.findOneByNamedQuery("Commande.getPanier", new Commande(), param);
		
		if(panier==null) {
			panier= new Commande();
			panier.setUtilisateur(currentUser);
			panier.setFacture(null);
			panier.setStatus("non-paye");
			EntityTransaction transac= em.getTransaction();
			try {
				transac.begin();
				em.persist(panier);
				transac.commit();
				}
			finally {
				if(transac.isActive())
					transac.rollback();
				em.clear();
				em.close();
			}
		}
		return panier;
	}
	@Override
	public boolean addFilmInPanier(Utilisateur currentUser, Film film) {
		boolean filmAdded= false;
		CommandesFilm cf= new CommandesFilm();
		Commande panier= getPanier(currentUser);
		cf.setCommande(panier);
		cf.setFilm(film);
		EntityTransaction transac= em.getTransaction();
		try {
			transac.begin();
			em.persist(em.merge(cf));
			panier.addCommandesFilm(cf);
			em.merge(panier);
			transac.commit();
			filmAdded= true;
			}
		finally {
			if(transac.isActive())
				transac.rollback();
			em.clear();
			em.close();
			}	
		return filmAdded;
		}
	
	

}
