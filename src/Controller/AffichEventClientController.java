/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
import org.controlsfx.control.NotificationPane;
import services.EvenementServices;
import static services.UserService.conn;
import services.notifEventServices;
import services.partEvServices;

public class AffichEventClientController implements Initializable {
 public static int idE ; 
 public static Evenement d ; 
    @FXML
    private TableView<Evenement> tabEvent;
    @FXML
    private TableColumn<Evenement, String> NomEvent;
    @FXML
    private ImageView imageAnn;
    @FXML
    private Label desc;
    @FXML
    private Label nom;
    @FXML
    private Label type;
    @FXML
    private Label DateD;
    @FXML
    private TextField seach;
    private Label locali;
    @FXML
    private TextField id;
    @FXML
    private Label DateF;
    @FXML
    private Hyperlink DétailBtn;
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
    private NotificationPane noti;
    @FXML
    private Button notif;
    @FXML
    private Label nb;
    @FXML
    private PieChart pie;
 /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         notifEventServices ns= new notifEventServices();
        nb.setText(String.valueOf( ns.Count(conn)));
        EvenementServices Ann=new EvenementServices();
        ArrayList A= (ArrayList) Ann.AfficherAllEvenementIn(conn);
        ObservableList ob=FXCollections.observableArrayList(A);
        tabEvent.setItems(ob);
        NomEvent.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        tabEvent.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        FilteredList<Evenement> fil= new FilteredList<>(ob,e->true);
        seach.setOnKeyReleased((KeyEvent e) -> {
            seach.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
                fil.setPredicate((Predicate <? super Evenement>) Evenement->{
                    if(newValue==null||newValue.isEmpty()){return true;}
                    String lower=newValue.toLowerCase();
                    return Evenement.getNomEvenement().toLowerCase().contains(lower);
                });
            });
            SortedList<Evenement> k = new SortedList<>(fil);
            k.comparatorProperty().bind(tabEvent.comparatorProperty());
            tabEvent.setItems(k);
        });
        tabEvent.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Evenement> obs, Evenement oldSelection, Evenement newSelection) -> {
    if (newSelection != null) {
       ObservableList<Annonce> anno;
       File f;
          f=new File("C:\\wamp64\\www\\SoukI\\web\\images2\\"+newSelection.getNomImg());
          Image img=new Image(f.toURI().toString());
          imageAnn.setImage(img);
          nom.setVisible(true);
          nom.setText(newSelection.getNomEvenement());
          desc.setVisible(true);
          desc.setText(newSelection.getDescription());
          type.setVisible(true);
          type.setText(newSelection.getType());
          DateD.setVisible(true);
          DateD.setText(newSelection.getDateDeb());
          DateF.setVisible(true);
          DateF.setText(newSelection.getDateFin());
          locali.setVisible(true);
          locali.setText(newSelection.getLocalisation());
          id.setText(Integer.toString(newSelection.getId()));
          d=newSelection;}}); 
         tabEvent.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Evenement> obs, Evenement oldSelection, Evenement newSelection) -> {
    if (newSelection != null) {
        partEvServices part=new partEvServices();
        System.out.println(newSelection.getId());
        int j=part.CountAvis2("n'est pas interessé(e)",newSelection.getId());
        int k=part.CountAvis2("interessé(e)",newSelection.getId());
        int r=part.CountAvis2("participer",newSelection.getId());
        System.out.println(j);
        System.out.println(r);
        System.out.println(k);
         ObservableList<PieChart.Data> pieE=
             FXCollections.observableArrayList(
             new PieChart.Data("n'est pas interessé(e)",j),
             new PieChart.Data("interessé(e)",k),
             new PieChart.Data("participer", r)
        );
        pie.setData(pieE);
    }}); 
        
    } 
    @FXML
    private void Détail(ActionEvent event) throws IOException {
     Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Parent root2 = FXMLLoader.load(getClass().getResource("AffichEventClient.fxml"));
     Scene scene2 = new Scene(root2);    
     primary.setTitle("Evenement!");
     primary.show();
     Stage primaryStage=new Stage();
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("EventDetails.fxml"));
     Parent root = loader.load();      
     Scene scene = new Scene(root);
     ObservableList<Evenement> r,f;
     EvenementServices Ann=new EvenementServices();
     f=(ObservableList<Evenement>) tabEvent.getItems();
     r=(ObservableList<Evenement>) tabEvent.getSelectionModel().getSelectedItems();
     if(r.size()>0){
     r.stream().map((A) -> {
         EventDetailsController controller = loader.getController();
         controller.iData(A);
         return A;
     }).map((A) -> {
         d=A;
         return A;  
     }).forEach((A) -> {
         idE=A.getId();
     });
     primaryStage.setTitle("details");
     primaryStage.setScene(scene);
     primaryStage.show(); 
     }
     
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
    private void Produits(ActionEvent event) {
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
    private void notifs(ActionEvent event) throws IOException {
        
     Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Parent root2 = FXMLLoader.load(getClass().getResource("AffichEventClient.fxml"));
     Scene scene2 = new Scene(root2);    
     primary.setTitle("Evenement!");
     primary.show();
     notifEventServices ns= new notifEventServices();
     System.out.println( ns.Count(conn));
     Stage primaryStage=new Stage();
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("ListNotifEV.fxml"));
     Parent root = loader.load();      
     Scene scene = new Scene(root);
     primaryStage.setTitle("Notifications");
     primaryStage.setScene(scene);
     primaryStage.show(); 
    }
}
