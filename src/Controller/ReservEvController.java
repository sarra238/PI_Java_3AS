/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AffichEventClientController.d;
import static Controller.ListResEvController.evt;
import Entities.Evenement;
import Entities.ReserEv;
import Entities.User;
import Entities.notifEvent;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.CommentEventServices;
import services.EvenementServices;
import services.ReserEvServices;
import static services.ReserEvServices.pass;
import services.UserService;
import static services.UserService.conn;
import services.notifEventServices;

/**
 * FXML Controller class
 *
 * @author lv
 */
public class ReservEvController implements Initializable {

  
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> numtel;
    @FXML
    private TableColumn<?, ?> mail;
    @FXML
    private TableView<?> tableRes;
    @FXML
    private Button supp;
    @FXML
    private Button conf;
 public static Evenement event ; 
 public static ReserEv id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
  public void iData(Evenement e) {
       ReserEvServices v =  new  ReserEvServices();
            ArrayList arrayList = (ArrayList) v.AfficherAllRes(e.getId());
            
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            
            tableRes.setItems(observablelist);
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
             mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
               numtel.setCellValueFactory(new PropertyValueFactory<>("tel"));
             tableRes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        event = e;
  }

       @FXML
    private void supp(ActionEvent event) {
          ObservableList<ReserEv> r,f;
        f=(ObservableList<ReserEv>) tableRes.getItems();
        r=(ObservableList<ReserEv>) tableRes.getSelectionModel().getSelectedItems();
        ReserEvServices Ev=new ReserEvServices();
        if(r!=null){
        r.stream().map((A) -> {
            Ev.SupprimerREs(A);
            return A;
        }).forEach((A) -> {
            f.remove(A);
        });
        }
    }
 public int t=0;
    public static ReserEv ident;
    @FXML
    private void confirmer(ActionEvent event) throws IOException, DocumentException, java.io.IOException {
           ObservableList<ReserEv> r,f;
        f=(ObservableList<ReserEv>) tableRes.getItems();
        r=(ObservableList<ReserEv>) tableRes.getSelectionModel().getSelectedItems();
          
        ReserEvServices Ev=new ReserEvServices();
        int i;
        if(r!=null)
            {r.stream().map((A) -> {
       Ev.ModifierEtat(A);
       
         return A;
           
        }).forEach((A) -> {
            
            try {
               
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ReservEvController.this.getClass().getResource("ReservEv.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                ReservEvController controller = loader.getController();
                 System.out.println(A.getUserId()+A.getIdentifiant()  );
                controller.notification(A);
                primaryStage.setTitle("Reservations");
                primaryStage.setScene(scene);
                primaryStage.show();
                f.remove(A);
              
              
            } catch (java.io.IOException ex) {
                Logger.getLogger(ReservEvController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        });
        

}}
public void notification(ReserEv r){
     UserService us = new UserService();
        User u = new User();
      u = us.RechercherUsertById(conn);
        notifEventServices rs = new notifEventServices();
          notifEvent a = new notifEvent(); 
         int s= r.getUserId();
       a.setIdUser(s);
 a.setText("vous etes inscrit a la formation "+ event+ "vous êtes priés de consulter votre boite mail et tirer votre identifiant ");
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        a.setDate(format.format(date));
     rs.AjouteNotif(a);
      
        u = us.RechercherUsertById(r.getUserId());
        ReserEvServices rsb = new ReserEvServices();
         ReserEv RU= rsb.RechercherReserById(r.getUserId(), event.getId());
         final String username = "benhadjkhouloud@gmail.com";
        final String password = "gazafil<3";
        String m;
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");
        Session session = Session.getInstance(props,new javax.mail.Authenticator() 
            {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        try {
             Message message = new MimeMessage(session);
             EvenementServices es = new EvenementServices();
       Evenement e1 = es.RechercherEvenementById(event.getId());
     
       System.out.println(e1.getIdUser());
        User usr =  us.RechercherUsertById(e1.getIdUser());
             message.setFrom(new InternetAddress("benhadjkhouloud@gmail.com"));
             message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(u.getEmail()));
             message.setSubject("confirmation");
        if ("Femme".equals(u.getGender())){ m = "Madame";} else{ m = "Monsieur";}
             Evenement e = new Evenement();
       
             Evenement ER = es.RechercherEvenementById(event.getId());
            
             System.out.println(event.getIdUser());
             message.setText("Bonjour,"+"\n"+ m+"\n"+
             " \n Nom"+u.getFname()+"\n Prenom"+ u.getLname()+"\n identifiant:  "+pass );
                          System.out.println(message);
             Transport.send(message);
             System.out.println("Done");
           
             
             }
        catch (MessagingException e) {System.out.println("Erreur d'envoi"); }//throw new RuntimeException(e);}
    }
}

