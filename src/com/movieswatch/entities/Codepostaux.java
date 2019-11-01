package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the codepostaux database table.
 * 
 */
@Entity
@Table(name="codepostaux")
@NamedQuery(name="Codepostaux.findAll", query="SELECT c FROM Codepostaux c")
public class Codepostaux implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CODEPOSTAL", unique=true, nullable=false)
	private int idCodepostal;

	@Column(name="nom_ville", length=255)
	private String nomVille;

	@Column(length=5)
	private String numero;

	//bi-directional many-to-one association to Pay
	@ManyToOne
	@JoinColumn(name="ID_PAYS", nullable=false)
	private Pay pay;

	//bi-directional many-to-one association to Utilisateur
	@OneToMany(mappedBy="codepostaux")
	private List<Utilisateur> utilisateurs;

	public Codepostaux() {
	}

	public int getIdCodepostal() {
		return this.idCodepostal;
	}

	public void setIdCodepostal(int idCodepostal) {
		this.idCodepostal = idCodepostal;
	}

	public String getNomVille() {
		return this.nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pay getPay() {
		return this.pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().add(utilisateur);
		utilisateur.setCodepostaux(this);

		return utilisateur;
	}

	public Utilisateur removeUtilisateur(Utilisateur utilisateur) {
		getUtilisateurs().remove(utilisateur);
		utilisateur.setCodepostaux(null);

		return utilisateur;
	}

}