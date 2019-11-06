package com.movieswatch.utils;

import java.time.LocalDate;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.movieswatch.entities.Commande;


public class JavaMailUtil {
	public static void sendMail(String recipient, Commande commande, String path) throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String mailAccount = "movieswatchproject@gmail.com";
		String password = "balaise1234";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailAccount,password);
			}
		});
		Message message = prepareMessage(session, mailAccount, recipient, commande, path);
		Transport.send(message);
		
		
		
	}

	private static Message prepareMessage(Session session, String mailAccount, String recipient, Commande commande, String path) {
		try
		{
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailAccount));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.setSubject("Votre commande chez MoviesWatch");
		
		Multipart emailContent = new MimeMultipart();
		MimeBodyPart textBodyPart = new MimeBodyPart();
		
		textBodyPart.setText("Monsieur "+commande.getUtilisateur().getNom()+",\r\n" + 
				"\r\n" + 
				"merci pour votre achat chez MoviesWatch du " + LocalDate.now() +".\r\n" + 
				"\r\n" + 
				"Ce courriel est pour vous confirmer que votre commande a �t� re�u.\r\n");
		
		MimeBodyPart pdfAttachment = new MimeBodyPart();
		pdfAttachment.attachFile(path);
		
		emailContent.addBodyPart(textBodyPart);
		emailContent.addBodyPart(pdfAttachment);
		
		message.setContent(emailContent);
		return message;
		}catch (Exception ex)
		{
			 
		}
		return null;
	}
}
