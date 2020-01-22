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
import com.itextpdf.text.Rectangle;
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
    private static Font smallItalic = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.ITALIC);
    
    
    public static PdfPCell ParagraphCell(int alignment , String paragraph, int border , Font font)
	 {
		 
		 Paragraph Paragraph = new Paragraph(paragraph ,font);
		 PdfPCell Cell = new PdfPCell(Paragraph);
		 Cell.setBorder(border);
		 Cell.setHorizontalAlignment(alignment);
		 return Cell;
	 }
  
 
	public static void generateFacture(User user, Order order, ServletContext servletContext) throws MalformedURLException, IOException {
		try {
			
			Document document = new Document();
			imageFile = servletContext.getResource("/resources/default/images/avatars/movieswatchlogo.jpg").getPath();
			PdfWriter.getInstance(document,new FileOutputStream(servletContext.getRealPath("/")+"/bills/" +order.getId()+".pdf"));
			
			document.open();
			
			PdfPTable tableTitle = new PdfPTable(2);
			tableTitle.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			Image img = Image.getInstance(imageFile);
			  img.scalePercent(5);
			
		  
			PdfPTable imgTable = new PdfPTable(1);
			
			imgTable.addCell(img);
			imgTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			imgTable.setTotalWidth(175);
			imgTable.setLockedWidth(true);
			imgTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			tableTitle.addCell(imgTable);
			tableTitle.addCell(ParagraphCell(PdfPCell.ALIGN_CENTER,"MoviesWatch Facture n°" + order.getId(),PdfPCell.NO_BORDER,titleFont));
			
			
			//Paragraph title = new Paragraph("MoviesWatch Facture n°" + order.getId(),titleFont);
			//title.setAlignment(Element.ALIGN_CENTER);
			document.add(tableTitle);
			document.add(new Paragraph(" "));
			 PdfPTable tableInfoClient = new PdfPTable(1);
			 tableInfoClient.addCell(ParagraphCell(PdfPCell.ALIGN_RIGHT,"Client : " + user.getLastname() + " " + user.getFirstname(),PdfPCell.NO_BORDER,smallItalic));
			 tableInfoClient.addCell(ParagraphCell(PdfPCell.ALIGN_RIGHT,"Adresse mail: " + user.getEmail(),PdfPCell.NO_BORDER,smallItalic));
			 tableInfoClient.addCell(ParagraphCell(PdfPCell.ALIGN_RIGHT,"Rue: " + user.getStreetName(),PdfPCell.NO_BORDER,smallItalic));
			 tableInfoClient.addCell(ParagraphCell(PdfPCell.ALIGN_RIGHT,"Code Postal :" + user.getPostalcode().getNumber() + " " + user.getPostalcode().getCity_name(),PdfPCell.NO_BORDER,smallItalic));
			 tableInfoClient.setHorizontalAlignment(Element.ALIGN_RIGHT);
			 tableInfoClient.setWidthPercentage(50);
			 
			 document.add(tableInfoClient);
			 document.add(new Paragraph(" "));
			 
			 PdfPTable tableFilm = new PdfPTable(2);
			 
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
