package com.movieswatch.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.movieswatch.entities.Commande;
import com.movieswatch.entities.CommandesFilm;
import com.movieswatch.entities.Utilisateur;

public class FactureGeneratorUtils {

	public static void generateFacture(Utilisateur user, Commande panier, ServletContext servletContext) throws FileNotFoundException {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document,new FileOutputStream(servletContext.getRealPath("/")+"/facture/" +panier.getIdCommande()+".pdf"));
			document.open();
			document.add(new Paragraph(user.getNom() + user.getPrenom()));
				document.add(new Paragraph(user.getEmail()));
				document.add(new Paragraph(panier.getIdCommande()));
				document.add(new Paragraph(panier.getFacture().getDate().toString()));
				for(CommandesFilm c: panier.getCommandesFilms()) {
					document.add(new Paragraph(c.getFilm().getTitreOriginal()));
				}
				document.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
