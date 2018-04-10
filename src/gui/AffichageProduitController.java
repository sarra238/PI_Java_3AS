/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ServiceProduit;
import static utils.util.somme;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class AffichageProduitController implements Initializable {
    
    ObservableList<Produit> listProduit = FXCollections.observableArrayList();
    ObservableList<Produit> listProduitcommande = FXCollections.observableArrayList();
     
    @FXML
    private Button btncart;
    @FXML
    private Label label5;
    @FXML
    private TableView<Produit> tableafficheproduit;
    @FXML
    private TableColumn<Produit, String> NomProduit;
    @FXML
    private TableColumn<Produit, String> Region;
    @FXML
    private TableColumn<Produit, String> Categorie;
    @FXML
    private TableColumn<Produit, Integer> Stock;
    @FXML
    private TableColumn<Produit, Double> Prix;
    @FXML
    private TableColumn<Produit, String> Description;
    @FXML
    private TableColumn<Produit, Double> longitude;
    @FXML
    private TableColumn<Produit, Double> attude;
    @FXML
    private Button btnaffichecart;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NomProduit.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        Stock.setCellValueFactory(new PropertyValueFactory<>(" Stock"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        attude.setCellValueFactory(new PropertyValueFactory<>("attitude"));
        ServiceProduit sp= new ServiceProduit();
        try{
        listProduit = sp.afficher();
        tableafficheproduit.setItems(listProduit);
        }catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    

    @FXML
    private void add_to_cart(ActionEvent event) {
        ServiceProduit sp = new ServiceProduit();
        tableafficheproduit.setEditable(true);
        int selectedIndex = tableafficheproduit.getSelectionModel().getSelectedIndex();
        Produit p = tableafficheproduit.getSelectionModel().getSelectedItem();
        int x=p.getId();
        if (selectedIndex >= 0) {
           tableafficheproduit.getItems().remove(selectedIndex);
           System.out.println(x);
           System.out.println(p.getLongitude());
           sp.approuver(p.getLongitude());
           somme +=p.getPrix();  
           label5.setText("ajout avec succées");
           PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
           pauseTransition.setOnFinished(z -> label5.setText(""));
           pauseTransition.play();
        } 
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de Selection un produit");
            alert.setHeaderText("vous n'avez pas sélectionner un produit !");
            alert.setContentText("veuillez sélectionner un produit dans la table");
            alert.showAndWait();
        }
    }

    @FXML
    private void page_précédente(ActionEvent event) throws SQLException, IOException {
        ServiceProduit sp = new ServiceProduit();
      listProduitcommande = sp.afficherproduitcommande();
      if ( listProduitcommande.size()==0)
      {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Votre panier est vide");
            alert.setHeaderText("Vous n'avez pas encore commander un produit");
            alert.setContentText("veuillez choisir commande un produit");
            alert.showAndWait();}
     else{FXMLLoader loader = new FXMLLoader(getClass().getResource("panier.fxml"));
            Parent root = loader.load();
            Scene homePageScene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homePageScene);
            appStage.show();
           }
    }
    
}
