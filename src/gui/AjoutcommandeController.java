/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import Entities.Produit;
import Entities.confcommande;
import javafx.scene.Node;
import javafx.stage.Stage;
import services.ServiceProduitm;
import services.Serviceconfcommande1;
import static utils.util.pr;
import static utils.util.user;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjoutcommandeController implements Initializable {

    @FXML
    private Button afficher1;
    @FXML
    private Label label;
    @FXML
    private TableView<Produit> afficherprocommande;
    @FXML
    private TableColumn<?, ?> NomProduit;
    @FXML
    private TableColumn<?, ?> Region;
    @FXML
    private TableColumn<?, ?> Categorie;
    @FXML
    private TableColumn<?, ?> Prix;
    @FXML
    private TableColumn<?, ?> id11;
    @FXML
    private Text nom1;
    @FXML
    private Text prenom1;
    @FXML
    private Text adresse1;
    @FXML
    private TextField nom2;
    @FXML
    private TextField prenom2;
    @FXML
    private TextField adresse2;
    @FXML
    private Button confirmer;
    @FXML
    private Button retour;
     ObservableList<Produit> listpc = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
         nom2.setText(user.getFname());
        prenom2.setText(user.getLname());
        adresse2.setText(user.getAddress());
               NomProduit.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
       Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
     
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
               id11.setCellValueFactory(new PropertyValueFactory<>("id"));

       ServiceProduitm sp= new ServiceProduitm();
   try{
       listpc = sp.afficherCommande();
  
    afficherprocommande.setItems(listpc);

       
         
    }catch (SQLException ex) {
            Logger.getLogger(ServiceProduitm.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }    

    @FXML
    private void afficher1(ActionEvent event) 
    {
    }

    @FXML
    private void confirmerCommande(ActionEvent event) throws SQLException
    {    Serviceconfcommande1 sp = new Serviceconfcommande1();
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
 Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.close();
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de Selection");
            alert.setHeaderText("vous n'avez pas sélectionner un produit!");
            alert.setContentText("veuillez sélectionner un produit dans la table");
            alert.showAndWait();
 label.setText("");
        }
   
        } 
    

    @FXML
    private void retour(ActionEvent event) 
    {
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.close();
    }
    
}
