/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import Entities.Produit;
import static services.ServiceProduit.insererProduit;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField nomProduit;
    @FXML
    private TextField stock;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private Button image;
    @FXML
    private TextField region;
    @FXML
    private TextField longitude;
    @FXML
    private TextField altitude;
    @FXML
    private ComboBox<String> categorie;
     File pDir;
    File pfile;
    int c ;
    String lien ;
    int file = 0;
    @FXML
    private ImageView img;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList categ = FXCollections.observableArrayList(
                "a", "b", "c");
                categorie.setItems(categ);
                 file = 0;
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
      /* List<Objet> list;
        list = ServiceObjet.img("Media/objet" + c + ".jpg");

        while (!list.isEmpty()) {
            c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
            list = ServiceObjet.img("Media/objet" + c + ".jpg");
        }*/

        pDir = new File("src/Media/Produit" + c + ".jpg");
        lien = "Media/Produit" + c + ".jpg";
    }    

    @FXML
    private void image(ActionEvent event) throws MalformedURLException {
             FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image..");

        /* - get saved directory - or get user`s home dir */
 /* - set filters */
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );

        /* - open dialog */
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {

            /* -- read image */
            file = 1;
            image.setText("image sélectionné");

            Image image1 = new Image(pfile.toURI().toURL().toExternalForm());
            img.setImage(image1);
    }
    }

    @FXML
    private void ajout(ActionEvent event) {
         copier(pfile, pDir);
                //@useridstatic
                  float f = Float.parseFloat(longitude.getText());
                    float f1 = Float.parseFloat(altitude.getText());
                     int p = Integer.parseInt(stock.getText());
                     float p1 = Float.parseFloat(prix.getText());

Produit o = new Produit (nomProduit.getText(), region.getText(), categorie.getSelectionModel().getSelectedItem(), p, p1,description.getText(), lien, f, f1, 3);
insererProduit(o);  
        
    }
    
          public static boolean copier(File source, File dest) { 
    try (InputStream sourceFile = new java.io.FileInputStream(source);  
            OutputStream destinationFile = new FileOutputStream(dest)) { 
        // Lecture par segment de 0.5Mo  
        byte buffer[] = new byte[512 * 1024]; 
        int nbLecture; 
        while ((nbLecture = sourceFile.read(buffer)) != -1){ 
            destinationFile.write(buffer, 0, nbLecture); 
        } 
    } catch (IOException e){ 
        e.printStackTrace(); 
        return false; // Erreur 
    } 
    return true; // Résultat OK   
}
    
}
