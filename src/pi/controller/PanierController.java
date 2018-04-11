/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import static utils.util.somme;
import Entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.Csv;
//import service.Csv;

import services.ServiceProduitm;

/**
 * FXML Controller class
 *
 * @author maryem
 */
public class PanierController implements Initializable {


    @FXML
    private AnchorPane label1;
    @FXML
    private AnchorPane label3;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<Produit> affichageproduitcommande;
    @FXML
    private TableColumn<?, ?> NomProduit;
    @FXML
    private TableColumn<?, ?> Region;
    @FXML
    private TableColumn<?, ?> Categorie;
    @FXML
    private TableColumn<?, ?> Prix;
    @FXML
    private Button retour;
    @FXML
    private Button commander;
    @FXML
    private Button vider_panier;
    @FXML
    private Button map;
    @FXML
    private Button rechercher;
    @FXML
    private Label label4;
    @FXML
    private Label label;
    @FXML
    private Button prixtotale;
    @FXML
    private Button Excel;
    @FXML
    private Text titre;
    @FXML
    private TextField text1;
     ObservableList<Produit> listProduitcommande = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        NomProduit.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
       
       ServiceProduitm sp= new ServiceProduitm();
   try{
       listProduitcommande = sp.afficherCommande();
  
    affichageproduitcommande.setItems(listProduitcommande);
    }catch (SQLException ex) {
            Logger.getLogger(ServiceProduitm.class.getName()).log(Level.SEVERE, null, ex);
       }
   
                label.setText(String.valueOf(somme)+"DT");

    
    }
        // TODO
        
 
 


   

    @FXML
    private void vider_panier(ActionEvent event) {
    }

    @FXML
    private void map(ActionEvent event) throws IOException {
                              
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/gui/Gmaps.fxml"));
    Parent root = loader.load();
    Scene homePageScene = new Scene(root);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(homePageScene);
    appStage.show();
    }
  


    @FXML
    private void prixtotale(ActionEvent event) {
    }

   



    @FXML
    private void supprimerproduit_commande(ActionEvent event) {//          ServiceProduit sp = new ServiceProduit();
     
      affichageproduitcommande.setEditable(true);
        int selectedIndex = affichageproduitcommande.getSelectionModel().getSelectedIndex();

         Produit p = affichageproduitcommande.getSelectionModel().getSelectedItem();
       //int x=p.getId();
          ServiceProduitm sp = new ServiceProduitm();

        if (selectedIndex >= 0) {
          affichageproduitcommande.getItems().remove(selectedIndex);
          // System.out.println(x);
System.out.println(p.getPrix());
         sp.approuverdelate(p.getPrix());
           somme -=p.getPrix();
        
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de Selection un produit");
            alert.setHeaderText("vous n'avez pas sélectionner un produit !");
            alert.setContentText("veuillez sélectionner un produit dans la table");
            alert.showAndWait();

        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException
    {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/gui/FXMLMesP.fxml"));
    Parent root = loader.load();
    Scene homePageScene = new Scene(root);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(homePageScene);
    appStage.show();
    }

    @FXML
    private void commander1(ActionEvent event) throws IOException {
                      
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/gui/ajoutcommande.fxml"));
    Parent root = loader.load();
    Scene homePageScene = new Scene(root);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(homePageScene);
    appStage.show();
    }



    @FXML
    private void rechercher(ActionEvent event) {        
    ServiceProduitm sp = new ServiceProduitm();
        int x;
            affichageproduitcommande.setEditable(true);

         NomProduit.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
       Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
     
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
       
            listProduitcommande = sp.search_nom(text1.getText());
        affichageproduitcommande.setItems(listProduitcommande);
      

        // x=Integer.parseInt(rechercher.getText())  ;             
        if (text1.getText().equals("")) {
            label4.setText("veuillez donner le nom du produit");
        } else {
            String a=text1.getText();
            listProduitcommande = sp.search_nom(a);
            System.out.println(a);
            affichageproduitcommande.setItems(listProduitcommande);
            label4.setText("");
        }
    }


     @FXML
    private void Excel(ActionEvent event) throws SQLException
    {
        ServiceProduitm mservice= new ServiceProduitm();
         Csv csv=new Csv(mservice.afficher());
    }
    

}