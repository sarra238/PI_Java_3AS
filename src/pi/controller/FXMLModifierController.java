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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import Entities.Produit;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLModifierController implements Initializable {

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
    @FXML
    private ImageView img;
    private int file=0;
    @FXML
    private AnchorPane paneop;
    private File pfile ;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Produit c = FXMLLesPController.P;
         ObservableList catego = FXCollections.observableArrayList(
                "a", "b", "c");
        categorie.setItems(catego);
        String f = String.valueOf(c.getLongitude());
        String f1 = String.valueOf(c.getAttitude());
        String p = String.valueOf(c.getStock());
         String p1 = String.valueOf(c.getPrix());  
         categorie.setValue(c.getCategorie());
         
         System.out.println(p);
        nomProduit.setText(c.getNomProduit());
        stock.setText(p);
        prix.setText(p1);
        description.setText(c.getDescription());
        region.setText(c.getRegion());
        longitude.setText(f);
        altitude.setText(f1);
        
        // TODO
    }    

    @FXML
    private void image(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
fileChooser.setTitle( "Select image.." );
    
/* - get saved directory - or get user`s home dir */




    
/* - set filters */
    
fileChooser.getExtensionFilters().addAll(
  new FileChooser.ExtensionFilter( "JPG", "*.jpg" ),
  new FileChooser.ExtensionFilter( "PNG", "*.png" ),
  new FileChooser.ExtensionFilter( "BMP", "*.bmp" )
);
    
/* - open dialog */
    
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

/* - draw image */
    
if ( pfile != null )
{      
      
  /* -- read image */
    image.setText("image sélectionné");
      
  Image image1 = new Image( pfile.toURI().toURL().toExternalForm() );
        img.setImage(image1);
        file = 1 ;

        
    }    }
    

    @FXML
    private void modifier(ActionEvent event) throws IOException {
     Produit original = FXMLLesPController.P;
       float f = Float.parseFloat(longitude.getText());
         float f1 = Float.parseFloat(altitude.getText());
         int p = Integer.parseInt(stock.getText());
         float p1 = Float.parseFloat(prix.getText());                           
             original.setNomProduit(nomProduit.getText());  
             original.setCategorie(categorie.getSelectionModel().getSelectedItem());
             original.setRegion(region.getText());
             original.setDescription(description.getText());
             original.setStock(p);
             original.setPrix(p1);
             original.setAttitude(f1);
             original.setLongitude(f);
             
             if(file==0)
             {ServiceProduit.updateProduit(original,original.getId());
              Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("success");
alert.setHeaderText(" Produit Modifié");

alert.showAndWait();
             AnchorPane pane2;
               pane2 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLLesP.fxml"));
                paneop.getChildren().setAll(pane2);  }
                
                
             if(file==1)
             { original.getNomImage();
             ServiceProduit.updateProduit(original,original.getId());
             File pDir = new File("src/"+original.getNomImage());
              copier( pfile,pDir) ;
                 ServiceProduit.updateProduit(original,original.getId());
                 file = 0;
                 
//         final URL imageURL = getClass().getResource(original.getImg()); 
//        final Image image = new Image(imageURL.toExternalForm()); 
                final Image image = new Image(original.getNomImage()); 

        img.setImage(image);
              Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("success");
alert.setHeaderText(" Produit Modifié");
alert.showAndWait();
             AnchorPane pane2;
               pane2 = FXMLLoader.load(getClass().getResource("FXMLLesP.fxml"));
                paneop.getChildren().setAll(pane2);
             
             
             
             }


           
           
           
       
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
