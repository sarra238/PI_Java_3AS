/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
import Entities.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ServiceProduit;
import services.ServiceProduitm;
import static utils.util.somme;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class ProduitClientController implements Initializable {
    public static Produit pstat;
    
    @FXML
    private TableView<Produit> tabAnn;
    @FXML
    private TableColumn<Produit, String> NomAnn;
    @FXML
    private ImageView imageAnn;
    @FXML
    private Label desc;
    @FXML
    private Label nom;
    @FXML
    private Label Categorie;
    @FXML
    private Label prix;
    @FXML
    private TextField seach;
    @FXML
    private Label nbrText;
    @FXML
    private Label Categorietext;
    @FXML
    private Label prixtext;
    @FXML
    private Label stocktext;
    @FXML
    private Label stock;
    @FXML
    private Button Home;
    @FXML
    private Button Produits;
    @FXML
    private Button Annonces;
    @FXML
    private Button Evénements;
    @FXML
    private Button Restaurants;
    @FXML
    private Button SAV;
    @FXML
    private Label regiontext;
    @FXML
    private Label region;
    @FXML
    private Button addtoCartBtn;
    @FXML
    private Button panier;

    ObservableList<Produit> listProduit = FXCollections.observableArrayList();
    ObservableList<Produit> listProduitcommande = FXCollections.observableArrayList();
    @FXML
    private Button StatBtn;
    @FXML
    private Button contactBtn;
     
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProduit Ann=new ServiceProduit();
        ArrayList A= (ArrayList) ServiceProduit.selectAllProduit();
        ObservableList ob=FXCollections.observableArrayList(A);
        tabAnn.setItems(ob);
        NomAnn.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        
        tabAnn.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        FilteredList<Produit> fil= new FilteredList<>(ob,e->true);
        seach.setOnKeyReleased((KeyEvent e) -> {
            seach.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
                fil.setPredicate((Predicate <? super Produit>) Annonce->{
                    if(newValue==null||newValue.isEmpty()){return true;}
                    String lower=newValue.toLowerCase();
                    if(Annonce.getNomProduit().toLowerCase().contains(lower)){return true;}
                    else if(Annonce.getCategorie().toLowerCase().contains(lower)){return true;}
                    return false;
                });
            });
            SortedList<Produit> k = new SortedList<>(fil);
            k.comparatorProperty().bind(tabAnn.comparatorProperty());
            tabAnn.setItems(k);
        });
        tabAnn.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
       ObservableList<Annonce> anno;
          File f=new File("C:\\wamp64\\www\\SoukI\\web\\images3\\"+newSelection.getNomImage());
          Image img=new Image(f.toURI().toString());
          imageAnn.setImage(img);
          prix.setVisible(false);
          nom.setVisible(true);
          nom.setText(newSelection.getNomProduit());
          desc.setVisible(true);
          desc.setText(newSelection.getDescription());
          Categorie.setVisible(true);
          Categorietext.setVisible(true);
          Categorie.setText(newSelection.getCategorie());
          prixtext.setVisible(true);
          prix.setText(Double.toString(newSelection.getPrix()));
          stock.setVisible(true);
          stocktext.setVisible(true);
          stock.setText(Integer.toString(newSelection.getStock()));
          region.setVisible(true);
          regiontext.setVisible(true);
          region.setText(newSelection.getRegion());
          pstat=newSelection;
    }});  
    }    

    @FXML
    private void Home(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("HomeC.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Home!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Produits(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProduitClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Produits!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Annonces(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonces!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Event(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AffichEventClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Evenement!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Resto(ActionEvent event) {
    }

    @FXML
    private void Sav(ActionEvent event) {
    }

    @FXML
    private void addToCart(ActionEvent event) {
        ServiceProduitm sp = new ServiceProduitm();
     
      tabAnn.setEditable(true);
        int selectedIndex = tabAnn.getSelectionModel().getSelectedIndex();

         Produit p = tabAnn.getSelectionModel().getSelectedItem();
       int x=p.getId();

        if (selectedIndex >= 0) {
           tabAnn.getItems().remove(selectedIndex);
           System.out.println(x);
           System.out.println(p.getLongitude());
           sp.approuver(p.getLongitude());
           somme +=p.getPrix();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de Selection un produit");
            alert.setHeaderText("vous n'avez pas sélectionner un produit !");
            alert.setContentText("veuillez sélectionner un produit dans la table");
            alert.showAndWait();

        }
    }

    @FXML
    private void goToPanier(ActionEvent event) throws SQLException, IOException {
        ServiceProduitm sp = new ServiceProduitm();

      listProduitcommande = sp.afficherproduitcommande();
      if (   listProduitcommande.isEmpty())
      {Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Votre panier est vide");
            alert.setHeaderText("Vous n'avez pas encore commander un produit");
            alert.setContentText("veuillez choisir commande un produit");
            alert.showAndWait();}
     else{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProduitClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("roduits!");
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage Stage=new Stage();
        Parent root2=FXMLLoader.load(getClass().getResource("/pi/gui/panier.fxml"));
        Scene scene2 = new Scene(root2);
        Stage.setTitle("Panier!");
        Stage.setScene(scene2);
        Stage.show();
           }
    }

    @FXML
    private void Stat(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ProduitClient.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Produits!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StatProduitRegion.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Stat!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void contacter(ActionEvent event) throws IOException {
       ObservableList<Produit> r,fo;
        fo=tabAnn.getItems();
        r=tabAnn.getSelectionModel().getSelectedItems();
        if(r.size()>0){
        
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ProduitClient.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Produits!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ProduitContact.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Stat!");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
    }
    
}
