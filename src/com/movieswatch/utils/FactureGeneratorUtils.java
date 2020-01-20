package com.movieswatch.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.movieswatch.entities.Order;
import com.movieswatch.entities.OrderMovie;
import com.movieswatch.entities.User;


public class FactureGeneratorUtils {

	public static String imageFile;
	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    
	public static void generateFacture(User user, Order order, ServletContext servletContext) throws MalformedURLException, IOException {
		try {
			
			Document document = new Document();
			document.open();
			/* imageFile = servletContext.getResource("/resources/default/images/avatars/movieswatch.jpg").getPath();
			
			PdfWriter.getInstance(document,new FileOutputStream(servletContext.getRealPath("/")+"/bills/" +order.getId()+".pdf"));
			
			  Image img = Image.getInstance(imageFile);
		        PdfPCell cell = new PdfPCell(img, true);
		        PdfPTable table = new PdfPTable(1);
		        table.addCell(cell);
		       */
			document.add(new Paragraph("Nom: " + user.getLastname() + " Prenom :" + user.getFirstname()));
				document.add(new Paragraph("Adresse mail: " + user.getEmail()));
				document.add(new Paragraph("Adresse: " + user.getStreetName() +" "+ user.getPostalcode().getNumber() + " " + user.getPostalcode().getCity_name()));
				document.add(new Paragraph("Commandes: "));
				for(OrderMovie c: order.getOrderMovies()) {
					document.add(new Paragraph(c.getMovie().getMovie().getTitle()));
					document.add(new Paragraph(c.getMovie().getPrice() + "€",redFont) );
				}
				document.add(new Paragraph("Moyen de paiement: "+ order.getPaymentMode()));
				document.add(new Paragraph("Date de commande : "+ order.getDate().toString()));
				

				document.close();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
