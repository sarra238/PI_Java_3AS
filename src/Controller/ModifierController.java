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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.EvenementServices;

public class ModifierController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private TextField nomE;
    @FXML
    private TextField desc;
    @FXML
    private TextField localisation;
    @FXML
    private TextField DateDeb;
    @FXML
    private TextField Datefin;
    @FXML
    private RadioButton formation;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton Exposition;
    @FXML
    private RadioButton autres;
    @FXML
    private TextField id;
    @FXML
    private Button filebtn;
    @FXML
    private ImageView imgEvent;

    private Image image;
    private File f;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void iData(Evenement e){
id.setText(Integer.toString(e.getId()));
nomE.setText(e.getNomEvenement());
        desc.setText(e.getDescription());
localisation.setText(e.getLocalisation());
DateDeb.setText(e.getDateDeb().toString());
Datefin.setText(e.getDateFin().toString());
        switch (e.getType()) {
            case "formation":
                formation.setSelected(true);
                break;
            case "Exposition":
                Exposition.setSelected(true);
                break;
            case "autres":
                autres.setSelected(true);
                break;
            default:
                autres.setSelected(true);
                break;
        }
        f=new File("C:\\wamp64\\www\\SoukI\\web\\images2\\"+e.getNomImg());
        Image img=new Image(f.toURI().toString());
        imgEvent.setImage(img);}

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Evenement e = new Evenement( nomE.getText(),
                desc.getText(),
                DateDeb.getText(),
                Datefin.getText(),
                localisation.getText()
        );
    
        if (formation.isSelected()) {
            e.setType(formation.getText());
            
        }      else if (Exposition.isSelected()) {
            e.setType(Exposition.getText());
            
        }   
            else if  (autres.isSelected()) {
            e.setType(autres.getText());
        }           
        e.setId(Integer.parseInt(id.getText()));
        EvenementServices s = new EvenementServices();
        s.ModifierEvenement(e);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    } 

    @FXML
    private void fileChooser(ActionEvent event) {
    }
}
