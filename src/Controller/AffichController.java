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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EvenementServices;

public class AffichController implements Initializable {

  
   
    @FXML
    private TableView<Evenement> table_view;
    @FXML
    private TableColumn<Evenement, String> nom_Evenement;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement, String> localisation;
    @FXML
    private AnchorPane liste;
    @FXML
    private TableColumn<Evenement, String> dated;
    @FXML
    private TableColumn<Evenement, String> datef;
    @FXML
    private Button delete;
    @FXML
    private Button stat;
    @FXML
    private Button add;
    @FXML
    private Button modifier;
    @FXML
    private TextField seach;
    @FXML
    private ImageView imgV;
    @FXML
    private Button StatAvisBtn;
    @FXML
    private Button CommentBtn;
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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementServices v =  new  EvenementServices() ;
        ArrayList arrayList = (ArrayList) v.AfficherAllEvenement();
        ObservableList ob = FXCollections.observableArrayList(arrayList);
        table_view.setItems(ob);
        nom_Evenement.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dated.setCellValueFactory(new PropertyValueFactory<>("dateDeb2"));
        datef.setCellValueFactory(new PropertyValueFactory<>("dateFin2"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        table_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        FilteredList<Evenement> fil= new FilteredList<>(ob,e->true);
        seach.setOnKeyReleased((KeyEvent e) -> {
            seach.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
                fil.setPredicate((Predicate <? super Evenement>) Evenement->{
                    if(newValue==null||newValue.isEmpty()){return true;}
                    String lower=newValue.toLowerCase();
                    if(Evenement.getNomEvenement().toLowerCase().contains(lower)){return true;}
                    else if(Evenement.getType().toLowerCase().contains(lower)){return true;}
                    return false;
                });
            });
            SortedList<Evenement> k = new SortedList<>(fil);
            k.comparatorProperty().bind(table_view.comparatorProperty());
            table_view.setItems(k);
        });
        table_view.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
          File f=new File("C:\\wamp64\\www\\SoukI\\web\\images2\\"+newSelection.getNomImg());
          Image img=new Image(f.toURI().toString());
          imgV.setImage(img);
    }});  
    }    
    @FXML
    private void delete(ActionEvent event) {
        ObservableList<Evenement> r,f;
        EvenementServices Ann=new EvenementServices();
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        if(r!=null){
        r.stream().map((A) -> {
            Ann.SupprimerEvenement(A);
            return A;
        }).forEach((A) -> {
            f.remove(A);
        });
        }
    }
    @FXML
    private void stat(ActionEvent event)throws IOException{   
      Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Parent root2 = FXMLLoader.load(getClass().getResource("affich.fxml"));
      Scene scene2 = new Scene(root2); 
      primary.setTitle("Evenement!");
      primary.setScene(scene2);
      primary.show();
      Stage primaryStage=new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("stat.fxml"));
      Scene scene = new Scene(root);    
      primaryStage.setTitle("statistiques");
      primaryStage.setScene(scene);
      primaryStage.show();
     }
    @FXML
    private void addEvent(ActionEvent event)throws IOException { 
     Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Parent root2 = FXMLLoader.load(getClass().getResource("affich.fxml"));
     Scene scene2 = new Scene(root2); 
     primary.setTitle("Evenement!");
     primary.setScene(scene2);
     primary.show();
     Stage primaryStage=new Stage();
     Parent root = FXMLLoader.load(getClass().getResource("evenement.fxml"));
     Scene scene = new Scene(root);
     primaryStage.setTitle("statistiques");
     primaryStage.setScene(scene);
     primaryStage.show();
    }
    @FXML
    private void modifier(ActionEvent event) throws IOException {
     Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Parent root2 = FXMLLoader.load(getClass().getResource("affich.fxml"));
     Scene scene2 = new Scene(root2); 
     primary.setTitle("Evenement!");
     primary.setScene(scene2);
     primary.show();
     Stage primaryStage=new Stage();
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("modifier.fxml"));
     Parent root = loader.load();      
     Scene scene = new Scene(root);
     ObservableList<Evenement> r,f;
     EvenementServices Ann=new EvenementServices();
     f=table_view.getItems();
     r=table_view.getSelectionModel().getSelectedItems();
     if(r.size()>0){
     r.stream().forEach((A) -> {
         ModifierController controller = loader.getController();
         controller.iData(A);
     });  
     primaryStage.setTitle("modifier Evenement");
     primaryStage.setScene(scene);
     primaryStage.show();   
     }
    }   

    @FXML
    private void statAvis(ActionEvent event) throws IOException {
      Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Parent root2 = FXMLLoader.load(getClass().getResource("affich.fxml"));
      Scene scene2 = new Scene(root2);
      primary.setTitle("Evenement!");
      primary.setScene(scene2);
      primary.show();
      Stage primaryStage=new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("EventStatAvis.fxml"));
      Scene scene = new Scene(root);    
      primaryStage.setTitle("Evenement!");
      primaryStage.setScene(scene);
      primaryStage.show();
    }

    @FXML
    private void Comment(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("affich.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Evenement!");
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage Stage=new Stage();
        Parent root2=FXMLLoader.load(getClass().getResource("AffichCommentEvent.fxml"));
        Scene scene2 = new Scene(root2);
        Stage.setTitle("Commentaire!");
        Stage.setScene(scene2);
        Stage.show();
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
    private void Produits(ActionEvent event) {
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
}