/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Entities.Produit;
import services.MailT;
import services.Mailtest;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLContacterController implements Initializable {

    @FXML
    private Button contacter;
    @FXML
    private TextField mail;
    @FXML
    private TextArea message;
    @FXML
    private AnchorPane p;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Produit p2 = FXMLMesPController.P1;
        
        // TODO
    }    

    @FXML
    private void contacter(ActionEvent event) {
        
String msg = message.getText() + "\n mon email est \n" + mail.getText() + "\n mon numéro est " + "\n";
String sujet = "souk desktop";
String toEmail = "mouna.kouki@esprit.tn";
MailT a = new MailT(toEmail,sujet,msg);
a.envoyer();
 Notifications.create().hideAfter(Duration.seconds(2)).position(Pos.CENTER).text("email envoyer").showConfirm();
  try {
                        AnchorPane pane1;
                        pane1 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLMesP.fxml"));
                        p.getChildren().setAll(pane1);

                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMesPController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    
}
}
