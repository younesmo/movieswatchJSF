package com.movieswatch.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;
import com.movieswatch.entities.User;

public class FactureGeneratorUtils {

	public static void generateFacture(User user, Order order, ServletContext servletContext) throws FileNotFoundException {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document,new FileOutputStream(servletContext.getRealPath("/")+"/bills/" +order.getId()+".pdf"));
			document.open();
			document.add(new Paragraph("Nom: " + user.getLastname() + " Prenom :" + user.getFirstname()));
				document.add(new Paragraph("Adresse mail: " + user.getEmail()));
				document.add(new Paragraph("Adresse: " + user.getStreetName() +" "+ user.getPostalcode().getNumber() + " " + user.getPostalcode().getCity_name()));
				document.add(new Paragraph("Commandes: "));
				for(OrderMovie c: order.getOrderMovies()) {
					document.add(new Paragraph(c.getMovie().getTitle()));
				}
				document.add(new Paragraph("Date de commande : "+ order.getDate().toString()));
				

				document.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
