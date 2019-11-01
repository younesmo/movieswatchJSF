package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the utilisateurs database table.
 * 
 */
@Entity
@Table(name="utilisateurs")
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_UTILISATEUR", unique=true, nullable=false)
	private int idUtilisateur;

	@Column(length=255)
	private String ADrue;

	@Temporal(TemporalType.DATE)
	@Column(name="date_naissance", nullable=false)
	private Date dateNaissance;

	@Column(nullable=false, length=255)
	private String email;

	@Column(length=255)
	private String nom;

	@Column(name="num_mobile", length=45)
	private String numMobile;

	@Column(nullable=false, length=255)
	private String passwd;

	@Column(length=255)
	private String prenom;

	//bi-directional many-to-one association to Commande
	@OneToMany(mappedBy="utilisateur")
	private List<Commande> commandes;

	//bi-directional many-to-one association to FilmsUtilisateur
	@OneToMany(mappedBy="utilisateur")
	private List<FilmsUtilisateur> filmsUtilisateurs;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ID_ROLE", nullable=false)
	private Role role;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="REFERE")
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to Utilisateur
	@OneToMany(mappedBy="utilisateur")
	private List<Utilisateur> utilisateurs;

	//bi-directional many-to-one association to Codepostaux
	@ManyToOne
	@JoinColumn(name="ID_CODEPOSTAL", nullable=false)
	private Codepostaux codepostaux;

	public Utilisateur() {
	}

	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getADrue() {
		return this.ADrue;
	}

	public void setADrue(String ADrue) {
		this.ADrue = ADrue;
	}

	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumMobile() {
		return this.numMobile;
	}

	public void setNumMobile(String numMobile) {
		this.numMobile = numMobile;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(Commande commande) {
		getCommandes().add(commande);
		commande.setUtilisateur(this);

		return commande;
	}

	public Commande removeCommande(Commande commande) {
		getCommandes().remove(commande);
		commande.setUtilisateur(null);

		return commande;
	}

	public List<FilmsUtilisateur> getFilmsUtilisateurs() {
		return this.filmsUtilisateurs;
	}

	public void setFilmsUtilisateurs(List<FilmsUtilisateur> filmsUtilisateurs) {
		this.filmsUtilisateurs = filmsUtilisateurs;
	}

	public FilmsUtilisateur addFilmsUtilisateur(FilmsUtilisateur filmsUtilisateur) {
		getFilmsUtilisateurs().add(filmsUtilisateur);
		filmsUtilisateur.setUtilisateur(this);

		return filmsUtilisateur;
	}

	public FilmsUtilisateur removeFilmsUtilisateur(FilmsUtilisateur filmsUtilisateur) {
		getFilmsUtilisateurs().remove(filmsUtilisateur);
		filmsUtilisateur.setUtilisateur(null);

		return filmsUtilisateur;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().add(utilisateur);
		utilisateur.setUtilisateur(this);

		return utilisateur;
	}

	public Utilisateur removeUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().remove(utilisateur);
		utilisateur.setUtilisateur(null);

		return utilisateur;
	}

	public Codepostaux getCodepostaux() {
		return this.codepostaux;
	}

	public void setCodepostaux(Codepostaux codepostaux) {
		this.codepostaux = codepostaux;
	}

}