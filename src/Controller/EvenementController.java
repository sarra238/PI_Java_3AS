/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.EvenementServices;
import utils.InputValidation;
import utils.Utils;

public class EvenementController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private ToggleGroup type;
    @FXML
    private TextField desc;
    @FXML
    private TextField nomE;
    @FXML
    private TextField localisation;
    @FXML
    private DatePicker DateDeb;
    @FXML
    private DatePicker Datefin;   
    @FXML
    private RadioButton formation;
    @FXML
    private RadioButton Exposition;
    @FXML
    private RadioButton autres;
    @FXML
    private Button filebtn;
    @FXML
    private ImageView imgEvent;
    
    private FileChooser fc;
    private File f;
    private Image image;
    private String uuid;
    @FXML
    private TextField nbMax;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }                
    @FXML
    private void ajouterEvenement(ActionEvent event) throws IOException {
        Evenement e = new Evenement() ;
        if (formation.isSelected()) {
            e.setType(formation.getText());
        }      
        else if (Exposition.isSelected()) {
            e.setType(Exposition.getText());
        }   
        else if  (autres.isSelected()) {
            e.setType(autres.getText());        
        }  
        e.setNomEvenement(nomE.getText());
        e.setDateDeb(DateDeb.getValue().toString());
        e.setDateFin(Datefin.getValue().toString());
        e.setDescription(desc.getText());
        e.setLocalisation(localisation.getText());
        e.setNomImg(uuid);
        e.setNbMax(Integer.parseInt(nbMax.getText()));
        EvenementServices s = new EvenementServices();
         if (InputValidation.validTextField(nomE.getText())) {
            Alert alertNom = new InputValidation().getAlert("Nom Evenement", "Saisissez un nom ");
            alertNom.showAndWait();
        } 
          if (InputValidation.validTextField(uuid)) {
            Alert alertNom = new InputValidation().getAlert("Image", "Inserez  une images");
            alertNom.showAndWait();
        } 
              if (InputValidation.validInteger(nbMax.getText())) {
            Alert alertNom = new InputValidation().getAlert("ooopps!", "Nb max et de type integer");
            alertNom.showAndWait();
        } 
          if (InputValidation.validTextField(desc.getText())) {
            Alert alertNom = new InputValidation().getAlert("Description", "Saisissez une description ");
            alertNom.showAndWait();
        } 
          if (InputValidation.validTextField(localisation.getText())) {
            Alert alertNom = new InputValidation().getAlert("localisation ","Saisissez une localisation ");
            alertNom.showAndWait();
        } 
          if (InputValidation.validTextField(DateDeb.getValue().toString())) {
            Alert alertNom = new InputValidation().getAlert("Date debut", "Saisissez un date de debut");
            alertNom.showAndWait();
        } 
          if (InputValidation.validTextField(Datefin.getValue().toString())) {
            Alert alertNom = new InputValidation().getAlert("Description", "Saisissez une description ");
            alertNom.showAndWait();
        } 
          if ((e.getDateDeb().compareTo(e.getDateFin()))==-1){
        s.AjouterEvenement(e);}
          else { Alert alertNom = new InputValidation().getAlert("ooops", "date debut superieur a la date fin ");
            alertNom.showAndWait();}
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    private void fileChooser(ActionEvent event) throws IOException {
        fc = new FileChooser();
        f = fc.showOpenDialog(null);
        if(f!=null)
        {
            uuid=f.getName();
            
            image=new Image(f.toURI().toString(),100,150,true,true);
            imgEvent.setImage(image); 
            Utils u =new Utils();
            String Emp ="C:\\wamp64\\www\\SoukI\\web\\images2\\"+uuid;
            u.CopyImage(Emp, f.toPath().toString());
        }    
    }
}