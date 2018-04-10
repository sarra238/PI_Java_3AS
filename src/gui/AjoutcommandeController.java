/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import static utils.util.user;
import static utils.util.pr;

import Entities.confcommande;
import Entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import services.ServiceProduit;
import services.Serviceconfcommande1;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author maryem
 */
public class AjoutcommandeController implements Initializable {
        ObservableList<Produit> listpc = FXCollections.observableArrayList();

    @FXML
    private Text nom1;
    @FXML
    private Text prenom1;
    @FXML
    private Text adresse1;
    @FXML
    private Button confirmer;
    @FXML
    private TextField adresse2;
    @FXML
    private TextField nom2;
    @FXML
    private TextField prenom2;
  
    @FXML
    private TableColumn<?, ?> NomProduit;
    @FXML
    private TableColumn<?, ?> Region;
    @FXML
    private TableColumn<?, ?> Categorie;
    private TableColumn<?, ?> Stock;
    @FXML
    private TableColumn<?, ?> Prix;
    private TableColumn<?, ?> Description;
    private TableColumn<?, ?> longitude;
    private TableColumn<?, ?> attude;
    @FXML
    private Button afficher1;
  
 
    @FXML
    private TableView<Produit> afficherprocommande;
    @FXML
    private TableColumn<?, ?> id11;
    @FXML
    private Label label;
    @FXML
    private Button retour;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom2.setText(user.getFname());
        prenom2.setText(user.getLname());
        adresse2.setText(user.getAddress());
        
        
  //************************************************      
           NomProduit.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
       Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
     
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
               id11.setCellValueFactory(new PropertyValueFactory<>("id"));

       ServiceProduit sp= new ServiceProduit();
   try{
       listpc = sp.afficherCommande();
  
    afficherprocommande.setItems(listpc);

       
         
    }catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
       }
        // TODO
    }    
//*******************************************************
    @FXML
    private void confirmerCommande(ActionEvent event) throws SQLException {
       
        Serviceconfcommande1 sp = new Serviceconfcommande1();
        // ServiceUser su = new ServiceUser ();
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("affichage.fxml"));
afficherprocommande.setEditable(true);
        int selectedIndex = afficherprocommande.getSelectionModel().getSelectedIndex();

        Produit p = afficherprocommande.getSelectionModel().getSelectedItem();
    
        //x=p.getId_patisserie();
if (nom2.getText().equals("") || prenom2.getText().equals("") || adresse1.getText().equals("") ) {
            label.setText("veuillez remplir tous les champs");}
   
else if (selectedIndex >= 0) 
{ 
            afficherprocommande.getItems().remove(selectedIndex);
  
        confcommande c = new confcommande(nom2.getText(), prenom2.getText(), adresse2.getText(),pr,user);
        sp.ajouterC(c);
           nom2.setText("");
           prenom2.setText("");
           adresse2.setText("");
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ajout avec succes");
            alert.setHeaderText("votre commande a etais envoyer!");
            alert.setContentText("commande envoyer  attente de repense");
            alert.showAndWait();
 label.setText("");
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de Selection");
            alert.setHeaderText("vous n'avez pas sélectionner un produit!");
            alert.setContentText("veuillez sélectionner un produit dans la table");
            alert.showAndWait();
 label.setText("");
        }
   
        } 
    //***********************************************************************


    @FXML
    private void afficher1(ActionEvent event) {
        
         NomProduit.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
       Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
     
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
               id11.setCellValueFactory(new PropertyValueFactory<>("id"));

       ServiceProduit sp= new ServiceProduit();
   try{
       listpc = sp.afficherCommande();
  
    afficherprocommande.setItems(listpc);

       
         
    }catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
       }
    
    }
    //************************************************************************

    @FXML
    private void retour(ActionEvent event) throws IOException
    {FXMLLoader loader = new FXMLLoader(getClass().getResource("panier.fxml"));
    Parent root = loader.load();
    Scene homePageScene = new Scene(root);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(homePageScene);
    appStage.show();
           }
    }
    

