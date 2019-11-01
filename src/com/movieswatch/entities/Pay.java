package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pays database table.
 * 
 */
@Entity
@Table(name="pays")
@NamedQuery(name="Pay.findAll", query="SELECT p FROM Pay p")
public class Pay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PAYS", unique=true, nullable=false)
	private int idPays;

	@Column(name="code_pays", length=45)
	private String codePays;

	@Column(name="forme_courte", length=255)
	private String formeCourte;

	@Column(length=255)
	private String nationalite;

	//bi-directional many-to-one association to Codepostaux
	@OneToMany(mappedBy="pay")
	private List<Codepostaux> codepostauxs;

	//bi-directional many-to-one association to FilmsPay
	@OneToMany(mappedBy="pay")
	private List<FilmsPay> filmsPays;

	public Pay() {
	}

	public int getIdPays() {
		return this.idPays;
	}

	public void setIdPays(int idPays) {
		this.idPays = idPays;
	}

	public String getCodePays() {
		return this.codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getFormeCourte() {
		return this.formeCourte;
	}

	public void setFormeCourte(String formeCourte) {
		this.formeCourte = formeCourte;
	}

	public String getNationalite() {
		return this.nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public List<Codepostaux> getCodepostauxs() {
		return this.codepostauxs;
	}

	public void setCodepostauxs(List<Codepostaux> codepostauxs) {
		this.codepostauxs = codepostauxs;
	}

	public Codepostaux addCodepostaux(Codepostaux codepostaux) {
		getCodepostauxs().add(codepostaux);
		codepostaux.setPay(this);

		return codepostaux;
	}

	public Codepostaux removeCodepostaux(Codepostaux codepostaux) {
		getCodepostauxs().remove(codepostaux);
		codepostaux.setPay(null);

		return codepostaux;
	}

	public List<FilmsPay> getFilmsPays() {
		return this.filmsPays;
	}

	public void setFilmsPays(List<FilmsPay> filmsPays) {
		this.filmsPays = filmsPays;
	}

	public FilmsPay addFilmsPay(FilmsPay filmsPay) {
		getFilmsPays().add(filmsPay);
		filmsPay.setPay(this);

		return filmsPay;
	}

	public FilmsPay removeFilmsPay(FilmsPay filmsPay) {
		getFilmsPays().remove(filmsPay);
		filmsPay.setPay(null);

		return filmsPay;
	}

}