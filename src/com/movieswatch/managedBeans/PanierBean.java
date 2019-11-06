package com.movieswatch.managedBeans;

import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.movieswatch.entities.Commande;
import com.movieswatch.entities.CommandesFilm;
import com.movieswatch.entities.Utilisateur;
import com.movieswatch.services.CommandService;
import com.movieswatch.services.CommandServiceImpl;
import com.movieswatch.utils.FactureGeneratorUtils;
import com.movieswatch.utils.JavaMailUtil;
import com.movieswatch.utils.SessionUtils;

@ManagedBean
@Named
public class PanierBean implements Serializable{
	
	private Commande panier;
	transient private CommandService panierService;
	transient private static Logger logger = Logger.getLogger(PanierBean.class);

	
	public PanierBean() {
		this.panierService= new CommandServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		Utilisateur currentUser= SessionUtils.getCurrentUser();
		this.panier= panierService.getPanier(currentUser);
	}
	
	public void delete(String id) {
		int idFilm= Integer.valueOf(id);
		CommandesFilm movieToRemove = null;

		for(CommandesFilm cf : panier.getCommandesFilms()) {
			if(cf.getFilm().getIdFilm()== idFilm) {
				movieToRemove = cf;
				break;
			}
		}
		
		boolean isMovieDeleted= panierService.deleteFromPanier(movieToRemove.getIdCommandeFilm());
		if(isMovieDeleted)
			panier.removeCommandesFilm(movieToRemove);
		else
			logger.debug("error");
			
	}

	public String pay() throws FileNotFoundException {
		ServletContext servletContext = (ServletContext) FacesContext
			    .getCurrentInstance().getExternalContext().getContext();
		Utilisateur currentUser= SessionUtils.getCurrentUser();
		
		boolean isPanierPaid= panierService.payPanier(currentUser);
		FactureGeneratorUtils.generateFacture(currentUser, panier, servletContext);
		try {
			JavaMailUtil.sendMail(currentUser.getEmail(), panier, servletContext.getRealPath("/")+"/facture/" +panier.getIdCommande()+".pdf");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isPanierPaid)
			return "commandes";
		else {
			logger.debug("error");
			return "";
		}
	}
	
	public Commande getPanier() {
		return panier;
	}

	public void setPanier(Commande panier) {
		this.panier = panier;
	}
	
	
}
