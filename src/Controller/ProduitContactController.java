/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ProduitClientController.pstat;
import Entities.User;
import java.io.File;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pi.controller.FXMLMesPController;
import services.MailT;
import services.UserService;
import static services.UserService.conn;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class ProduitContactController implements Initializable {
    @FXML
    private Label nomP;
    @FXML
    private Label stock;
    @FXML
    private Label prix;
    @FXML
    private Label region;
    @FXML
    private Label cat;
    @FXML
    private Label dateLanc;
    @FXML
    private Label desc;
    @FXML
    private ImageView imgV;
    @FXML
    private TextArea messsage;
    @FXML
    private Button contacterBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomP.setText(pstat.getNomProduit());
        stock.setText(Integer.toString(pstat.getStock()));
        prix.setText(Double.toString(pstat.getPrix()));
        region.setText(pstat.getRegion());
        cat.setText(pstat.getCategorie());
        dateLanc.setText(pstat.getDateLancement());
        desc.setText(pstat.getDescription());
        File f=new File("C:\\wamp64\\www\\SoukI\\web\\images3\\"+pstat.getNomImage());
        Image img=new Image(f.toURI().toString());
        imgV.setImage(img);
    }    

    @FXML
    private void contacter(ActionEvent event) throws IOException {
        User u;
        UserService us= new UserService();
        u=us.RechercherUsertById(conn);
        System.out.println(u.getEmail());
        String msg = messsage.getText() + "\n mon email est \n" + u.getEmail() + "\n mon numéro est " + "\n";
        String sujet = "souk desktop";
        String toEmail = "mounabenkouks@gmail.com";
        MailT a = new MailT(toEmail,sujet,msg);
        a.envoyer();
        Notifications.create().hideAfter(Duration.seconds(2)).position(Pos.CENTER).text("email envoyer").showConfirm();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProduitContact.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Produits!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
}
