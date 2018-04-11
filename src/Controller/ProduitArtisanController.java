/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AnnoncesArtisanController.da;
import Entities.Annonce;
import Entities.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.AnnonceServices;
import services.ServiceProduit;
import static services.UserService.conn;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class ProduitArtisanController implements Initializable {
    
     public static Produit da ;
    
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
    private TableView<Produit> listAnnonce;
    @FXML
    private TableColumn<Produit, String> nomP;
    @FXML
    private TableColumn<Produit, String> Region;
    @FXML
    private TableColumn<Produit,String> cat;
    @FXML
    private TableColumn<Produit, String> description;
    @FXML
    private TableColumn<Produit, Double> prix;
    @FXML
    private TableColumn<Produit, Integer> Stock;
    @FXML
    private Button Ajout;
    @FXML
    private Button suppBtn;
    @FXML
    private Button ModifBtn;
    @FXML
    private TextField seach;
    @FXML
    private ImageView imagev;
    @FXML
    private Button StatBtn;
    @FXML
    private Button CmdConfBtn;

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
        listAnnonce.setItems(ob);
        nomP.setCellValueFactory(new PropertyValueFactory<>("NomProduit"));
        cat.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        Region.setCellValueFactory(new PropertyValueFactory<>("Region"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        listAnnonce.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        FilteredList<Produit> fil= new FilteredList<>(ob,e->true);
        seach.setOnKeyReleased((KeyEvent e) -> {
            seach.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
                fil.setPredicate((Predicate <? super Produit>) Produit->{
                    if(newValue==null||newValue.isEmpty()){return true;}
                    String lower=newValue.toLowerCase();
                    if(Produit.getNomProduit().toLowerCase().contains(lower)){return true;}
                    else if(Produit.getCategorie().toLowerCase().contains(lower)){return true;}
                    else if(Produit.getDescription().toLowerCase().contains(lower)){return true;}
                    return false;
                });
            });
            SortedList<Produit> k = new SortedList<>(fil);
            k.comparatorProperty().bind(listAnnonce.comparatorProperty());
            listAnnonce.setItems(k);
        });
        listAnnonce.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
         File f=new File("C:\\wamp64\\www\\SoukI\\web\\images3\\"+newSelection.getNomImage());
          Image img=new Image(f.toURI().toString());
          imagev.setImage(img);
          da=newSelection;
    }});  
    }    

    @FXML
    private void Home(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Home!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    @FXML
    private void Produits(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProduitArtisan.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Produits!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void Annonces(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnoncesArtisan.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Annonces!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    @FXML
    private void Event(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("affich.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Evenements!");
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
    private void AjouterAnn(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ProduitArtisan.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Produits!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Produits!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        ObservableList<Produit> r,fo;
        ServiceProduit Ann=new ServiceProduit();
        fo=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        if(r!=null){
            r.stream().forEach((A) -> {
                ServiceProduit.deleteProduit2(A.getId());
            });
        }
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ProduitArtisan.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Produits!");
        primary.setScene(scene2);
        primary.show();
    }

    @FXML
    private void Modif(ActionEvent event) throws IOException {
        ObservableList<Produit> r,fo;
        AnnonceServices Ann=new AnnonceServices();
        fo=listAnnonce.getItems();
        r=listAnnonce.getSelectionModel().getSelectedItems();
        if(r.size()>0){
        
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ProduitArtisan.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Annonce!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifierProduit.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        r.stream().forEach((A) -> {
            ModifierProduitController controllerModifA = loader.getController();
            controllerModifA.DATA(A);
        });
        primaryStage.setTitle("Modifier Produit!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        }
    }

    @FXML
    private void Stat(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ProduitArtisan.fxml"));
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
    private void CmdConf(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ProduitArtisan.fxml"));
        Scene scene2 = new Scene(root2); 
        primary.setTitle("Home!");
        primary.setScene(scene2);
        primary.show();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/admincommande.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Commande à confirmer!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
