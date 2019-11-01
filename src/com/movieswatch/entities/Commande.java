package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the commandes database table.
 * 
 */
@Entity
@Table(name="commandes")
@NamedQuery(name="Commande.findAll", query="SELECT c FROM Commande c")
public class Commande implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMMANDE", unique=true, nullable=false)
	private int idCommande;

	@Column(length=1)
	private String status;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ID_UTILISATEUR", nullable=false)
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to Facture
	@ManyToOne
	@JoinColumn(name="ID_FACTURE")
	private Facture facture;

	//bi-directional many-to-one association to CommandesFilm
	@OneToMany(mappedBy="commande")
	private List<CommandesFilm> commandesFilms;

	public Commande() {
	}

	public int getIdCommande() {
		return this.idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Facture getFacture() {
		return this.facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public List<CommandesFilm> getCommandesFilms() {
		return this.commandesFilms;
	}

	public void setCommandesFilms(List<CommandesFilm> commandesFilms) {
		this.commandesFilms = commandesFilms;
	}

	public CommandesFilm addCommandesFilm(CommandesFilm commandesFilm) {
		getCommandesFilms().add(commandesFilm);
		commandesFilm.setCommande(this);

		return commandesFilm;
	}

	public CommandesFilm removeCommandesFilm(CommandesFilm commandesFilm) {
		getCommandesFilms().remove(commandesFilm);
		commandesFilm.setCommande(null);

		return commandesFilm;
	}

}