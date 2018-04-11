/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceProduit;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class ModifierProduitController implements Initializable {
    @FXML
    private Label prixLabel;
    @FXML
    private TextField nomP;
    @FXML
    private TextField cat;
    @FXML
    private TextArea description;
    @FXML
    private Button AjoutBtn;
    @FXML
    private Button fileBtn;
    @FXML
    private ImageView imageAnn;
    @FXML
    private Label prixLabel1;
    @FXML
    private ComboBox<String> comboR;
    @FXML
    private TextField stock;
    @FXML
    private TextField Prix;
    @FXML
    private TextField longi;
    @FXML
    private TextField alti;
    @FXML
    private Label prixLabel3;

    private Image image;
    private File f;
    private FileChooser fc;
    private String uuid;
    @FXML
    private Label id;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList ob =FXCollections.observableArrayList("Tunis","Ariana","Sfax","Sousse","Gabes","Nabeul","Bizerte");
        comboR.setItems(ob);
    }    
     public void DATA(Produit A)
    {
        id.setText(Integer.toString(A.getId()));
        nomP.setText(A.getNomProduit());
        description.setText(A.getDescription());
        Prix.setText(Double.toString(A.getPrix()));
        stock.setText(Integer.toString( A.getStock()));
        longi.setText(Double.toString( A.getLongitude()));
        alti.setText(Double.toString( A.getAttitude()));
        f=new File("C:\\wamp64\\www\\SoukI\\web\\images3\\"+A.getNomImage());
        Image img=new Image(f.toURI().toString());
        imageAnn.setImage(img);
        comboR.setValue(A.getRegion());
        cat.setText(A.getCategorie());
    }
    @FXML
    private void AjoutProduit(ActionEvent event) {
        Produit p=new Produit();
        if(fileBtn.isPressed()){p.setNomImage(uuid);}
        p.setId(Integer.parseInt(id.getText()));
        p.setNomProduit(nomP.getText());
        p.setCategorie(cat.getText());
        p.setPrix(Double.parseDouble(Prix.getText()));
        p.setStock(Integer.parseInt(stock.getText()));
        p.setAttitude(Double.parseDouble(alti.getText()));
        p.setLongitude((float) Double.parseDouble(longi.getText()));
        p.setDescription(description.getText());
        p.setRegion(comboR.getValue());
        p.setNomImage(uuid);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = new Date();
        p.setDateLancement(format.format(date2));
        ServiceProduit.updateProduit(p);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    private void fileChooser(ActionEvent event) throws IOException {
        fc = new FileChooser();
        f = fc.showOpenDialog(null);
        if(f!=null)
        {
            uuid=UUID.randomUUID().toString().replaceAll("--","")+".jpg";
            image=new Image(f.toURI().toString(),100,150,true,true);
            imageAnn.setImage(image); 
            Utils u =new Utils();
            String Emp ="C:\\wamp64\\www\\SoukI\\web\\images3\\"+uuid;
            u.CopyImage(Emp, f.toPath().toString());
        } 
    }
    
}
