/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AffichEventClientController.d;
import Entities.CommentEvent;
import Entities.Evenement;
import Entities.ReserEv;
import Entities.User;
import Entities.particEv;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import services.CommentEventServices;
import services.EvenementServices;
import services.ReserEvServices;
import services.UserService;
import static services.UserService.conn;
import services.partEvServices;
import utils.InputValidation;

public class EventDetailsController implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Label localisation;
    @FXML
    private Label Datefin;
    @FXML
    private Label DateDeb;
    @FXML
    private Label desc;
    @FXML
    private Label nomE;
    @FXML
    private TextField id;
    @FXML
    private ImageView imgEvent; 
    @FXML
    private ToggleGroup part;
    @FXML
    private RadioButton nint;
    @FXML
    private RadioButton par;
    @FXML
    private RadioButton in;
    @FXML
    private TextField Eid;
    private TextField comment;
    @FXML
    private TextField commentText;
    @FXML
    private Button CommentBtn;
    @FXML
    private TableView<CommentEvent> tableCom;
    @FXML
    private TableColumn<CommentEvent,String> commentaire;
    @FXML
    private TableColumn<CommentEvent,String> date;
    public int i;
    
    private Image image;
    private File f;
    public String m;
    private Button reserverB;
    public static  Evenement parID;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO   
    }   
    
   public void iData(Evenement e) 
   { 
       parID=e;
      
     partEvServices s = new partEvServices();
     if(s.Count(e.getId(), conn)== 0 ){
     id.setText(Integer.toString(e.getId()));
     nomE.setText(e.getNomEvenement());
     desc.setText(e.getDescription());
     localisation.setText(e.getLocalisation());
     DateDeb.setText(e.getDateDeb());
     Datefin.setText(e.getDateFin());
     type.setText( e.getType());
    
     f=new File("C:\\wamp64\\www\\SoukI\\web\\images2\\"+e.getNomImg());
     Image img=new Image(f.toURI().toString());
     imgEvent.setImage(img);
     } 
     else {
     id.setText(Integer.toString(e.getId()));
     nomE.setText(e.getNomEvenement());
     desc.setText(e.getDescription());
     localisation.setText(e.getLocalisation());
     DateDeb.setText(e.getDateDeb());
     Datefin.setText(e.getDateFin());
     type.setText( e.getType());
     
     f=new File("C:\\wamp64\\www\\SoukI\\web\\images2\\"+e.getNomImg());
     Image img=new Image(f.toURI().toString());
     imgEvent.setImage(img);
     
     
         switch (s.RechercherParById(e.getId(), conn).getType()) {
             case "participer":
                 par.setSelected(true);
                 in.setSelected(false);
                 nint.setSelected(false);
               
                 break;
             case "n'est pas interessé(e)":
                 nint.setSelected(true);
                 par.setSelected(false);
                 in.setSelected(false);
                 break;
             case "interessé(e)":
                 in.setSelected(true);
                 par.setSelected(false);
                 nint.setSelected(false);
                 break;
         }
     Eid.setText(Integer.toString(s.RechercherId(e.getId(), conn)));   
     
     }   
       CommentEventServices v =  new  CommentEventServices();
            System.out.println(e.getId());
            ArrayList arrayList = (ArrayList) v.AfficherAllComm(e.getId());
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            tableCom.setItems(observablelist);
            commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
   }
    @FXML
    private void creatpart(ActionEvent event) throws IOException {
        EvenementServices es = new EvenementServices();
       Evenement e1 = es.RechercherEvenementById(d.getId());
       
       System.out.println(e1.getIdUser());
       UserService us = new UserService();
            User u = us.RechercherUsertById(e1.getIdUser());
     
     int resp = u.getPhoneNumber();
     
          partEvServices s = new partEvServices();
          particEv e = new particEv();
             System.out.println(resp+""+s.Count(d.getId(), conn));
          if(s.Count(d.getId(), conn)== 0 ){
              if (in.isSelected()) {
                 e.setType(in.getText());
              }    
              else if (nint.isSelected()) {
                 e.setType(nint.getText());
                 
              }   
              else if  (par.isSelected()) {
                 e.setType(par.getText());  
                    if (d.getType().equals("formation")){
  Alert alert = new InputValidation().getAlert("Succes", "Si vous êtes vraiment interessé a la participation a cette formation vous devrez conntacter la responsable a la formation "+resp);
alert.showAndWait();
  
        u = us.RechercherUsertById(conn);
        ReserEvServices rs = new ReserEvServices();
        ReserEv r = new ReserEv();
        r.setIdEv(d.getId());
        r.setUserId(conn);
        r.setNom(u.getFname());
        r.setPrenom(u.getLname());
        r.setMail(u.getEmail());
        r.setTel(u.getPhoneNumber());
     rs.AjouterRes(r);
     

}    
                    else {
                        Alert alert = new InputValidation().getAlert("Succes", "soyez le bienvenu ");
alert.showAndWait();
                    }
                 }
              
              
             e.setUserId(conn);
             e.setIdEv(d.getId());
             s.AjouterPart(e);
             Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(getClass().getResource("EventDetails.fxml"));
             Parent root = loader.load();      
             Scene scene = new Scene(root);
             EventDetailsController controller = loader.getController();
             controller.iData(d);
             primaryStage.setTitle("Details Event");
             primaryStage.setScene(scene);
             primaryStage.show();
         }
       
        else if (s.Count(d.getId(), conn)!= 0) {
            part.getSelectedToggle().selectedProperty().addListener( (obs, oldSelection, newSelection) -> {
            try {
                if( oldSelection!=null) 
                {
                    if ((newSelection==in.isSelected())==true){
                   
                        particEv v =  s.RechercherParById(parID.getId(), conn);
                      v.setId(Integer.parseInt(Eid.getText()));
                        v.setType(in.getText());
                         nint.setSelected(false);
                 par.setSelected(false);
                 in.setSelected(true);
                        System.out.println("in");
                      s.ModifierPart(v);
                    }
                    else if ((newSelection==nint.isSelected())==true){
                           nint.setSelected(true);
                 par.setSelected(false);
                 in.setSelected(false);
                        particEv v =  s.RechercherParById(parID.getId(), conn);
                      v.setId(Integer.parseInt(Eid.getText()));
                        v.setType(nint.getText());
          
                        System.out.println("nint");
                       s.ModifierPart(v);
                    }
                   
                
                  if ((newSelection==par.isSelected())==true){
                         nint.setSelected(false);
                 par.setSelected(true);
                 in.setSelected(false);
                        particEv v =  s.RechercherParById(parID.getId(), conn);
                       v.setId(Integer.parseInt(Eid.getText()));
                        v.setType(par.getText());
                        System.out.println("par");
                      
                    
                    s.ModifierPart(v);
                     if (d.getType().equals("formation")){
  Alert alert = new InputValidation().getAlert("Succes", "Si vous êtes vraiment interessé a la participation a cette formation vous devrez conntacter la responsable a la formation "+resp);
alert.showAndWait();

        UserService a = new UserService();              
      User  m = a.RechercherUsertById(conn);
        ReserEvServices rs = new ReserEvServices();
        ReserEv r = new ReserEv();
        r.setIdEv(d.getId());
        r.setUserId(conn);
        r.setNom(m.getFname());
        r.setPrenom(m.getLname());
        r.setMail(m.getEmail());
        r.setTel(m.getPhoneNumber());
     rs.AjouterRes(r);
     
                     }
                }
                  
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(EventDetailsController.this.getClass().getResource("EventDetails.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        EventDetailsController controller = loader.getController();
                        controller.iData(d);
                        primaryStage.setTitle("Details Event");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    }
              
            }   catch (Exception ex) {
                    
                }
                    
                    });
       } 

    }

    @FXML
    private void commenter(ActionEvent event) throws IOException {
        
        CommentEventServices cs = new CommentEventServices();
        CommentEvent  c = new CommentEvent();
        c.setUserId(conn);
        c.setIdEv(d.getId());
        c.setCommentaire(commentText.getText());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = new Date();
        c.setDate(format.format(date2));
        cs.AjouterComm(c); 
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EventDetailsController.this.getClass().getResource("EventDetails.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        EventDetailsController controller = loader.getController();
        controller.iData(d);
        primaryStage.setTitle("Details Event");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void reserver(ActionEvent event) {
        User u;
        UserService us = new UserService();
        u = us.RechercherUsertById(conn);
        ReserEvServices rs = new ReserEvServices();
        ReserEv r = new ReserEv();
        r.setIdEv(d.getId());
        r.setUserId(conn);
        r.setNom(u.getFname());
        r.setPrenom(u.getLname());
        r.setMail(u.getEmail());
        r.setTel(u.getPhoneNumber());
     rs.AjouterRes(r);
        final String username = "benhadjkhouloud@gmail.com";
        final String password = "gazafil<3";
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
       Evenement e1 = es.RechercherEvenementById(d.getId());
     
       System.out.println(e1.getIdUser());
        User usr =  us.RechercherUsertById(e1.getIdUser());
             message.setFrom(new InternetAddress("benhadjkhouloud@gmail.com"));
             message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(u.getEmail()));
             message.setSubject("confirmation");
        if ("Femme".equals(u.getGender())){ m = "Madame";} else{ m = "Monsieur";}
             Evenement e = new Evenement();
       
             Evenement ER = es.RechercherEvenementById(d.getId());
             u = us.RechercherUsertById(ER.getIdUser());
             System.out.println(d.getIdUser());
             message.setText("Bonjour,"+"\n"+ m+"\n"+
             " \n Vous etes inscrit a la formation "+ER.getNomEvenement()+"le "+ER.getDateDeb()+
             "\n pour valider votre inscription voici le numero de telephone du responsabe a la formation"+usr.getPhoneNumber());
                          System.out.println(message);
             Transport.send(message);
             System.out.println("Done");
             reserverB.setDisable(true);
             
             }
        catch (MessagingException e) {System.out.println("Erreur d'envoi"); }//throw new RuntimeException(e);}
    }
}
