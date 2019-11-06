package com.movieswatch.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.movieswatch.dao.EMF;
import com.movieswatch.dao.EntityFinder;
import com.movieswatch.dao.EntityFinderImpl;
import com.movieswatch.entities.Commande;
import com.movieswatch.entities.CommandesFilm;
import com.movieswatch.entities.Facture;
import com.movieswatch.entities.Film;
import com.movieswatch.entities.Utilisateur;

public class CommandServiceImpl implements CommandService {

    private EntityFinder<Commande> commandFinder;
    private EntityFinder<CommandesFilm> commandFilmFinder;
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
	
	
	public boolean deleteFromPanier(int idMovieToRemove) {
		boolean filmDeleted= false;
		CommandesFilm itemToRemove= commandFilmFinder.findOne(new CommandesFilm(), idMovieToRemove);

		EntityTransaction transac= em.getTransaction();
		try {
			transac.begin();
			em.remove(em.merge(itemToRemove));
			transac.commit();
			filmDeleted= true;
		}
		finally {
			if(transac.isActive()) {
				transac.rollback();
			}
			em.clear();
			em.close();
		}
		return filmDeleted;
	}
	@Override
	public boolean payPanier(Utilisateur currentUser) {
		boolean panierPaid= false;
		Commande panier= getPanier(currentUser);
		Facture f= new Facture();
		EntityTransaction transac= em.getTransaction();
		
		try {
			transac.begin();
			
			if(panier!=null) {

				f.setDate(Date.valueOf(LocalDate.now()));
				panier.setFacture(f);
				panier.setStatus("paye");
				em.merge(panier);
			}
			transac.commit();
			panierPaid= true;
		}
		finally {
			if(transac.isActive()) {
				transac.rollback();
			}
			em.clear();
			em.close();
		}
		return panierPaid;
	}
	
	

}
