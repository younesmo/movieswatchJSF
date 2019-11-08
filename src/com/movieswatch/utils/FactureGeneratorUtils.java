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
			PdfWriter.getInstance(document,new FileOutputStream(servletContext.getRealPath("/")+"/facture/" +order.getId()+".pdf"));
			document.open();
			document.add(new Paragraph(user.getLastname() + user.getFirstname()));
				document.add(new Paragraph(user.getEmail()));
				document.add(new Paragraph(order.getId()));
				document.add(new Paragraph(order.getBill().getDate().toString()));
				for(OrderMovie c: order.getOrderMovies()) {
					document.add(new Paragraph(c.getMovie().getTitle()));
				}
				document.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
