/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Entities.Evenement;
import Entities.User;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.UserService;

public class mailU {

   
    public static boolean sendMail( Evenement e, User u) throws SQLException {
         final String username ="benhadjkhouloud@gmail.com"; // mail de la personne qui va envoyer 
	 final String password ="gazafil<3";// password de la personne qui va envoyer 
         Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
             // props.put("mail.smtp.response","421");
         Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                @Override
	        protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication( username,password);}
		  });
         try {
            UserService rs=new UserService();
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("benhadjkhouloud@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(rs.RechercherUsertById(u.getId()).getEmail()));
            msg.setSubject("Nouveau cours ajouté!");
            msg.setText("Bonjour,\nLe cours " + e.getNomEvenement() + " a été ajouté par le Prof " + u.getUsername());
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
            return false;
        }
         return true;
    }
}
