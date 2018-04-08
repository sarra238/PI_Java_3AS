/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Evenement;
import java.io.File;
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
import javafx.fxml.Initializable;
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
import services.EvenementServices;

public class EventConfirmController implements Initializable {
    @FXML
    private AnchorPane liste;
    @FXML
    private Button ConfirmBtn;
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
    private TableColumn<Evenement, String> dated;
    @FXML
    private TableColumn<Evenement, String> datef;
    @FXML
    private TextField seach;
    @FXML
    private ImageView imgV;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         EvenementServices v =  new  EvenementServices() ;
        ArrayList arrayList = (ArrayList) v.AfficherAllEvenementC();
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
    private void Confirm(ActionEvent event) {
        ObservableList<Evenement> r,f;
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        EvenementServices Ev=new EvenementServices();
        if(r!=null){
        r.stream().map((A) -> {
            Ev.ModifierEtat(A);
            return A;
        }).forEach((A) -> {
            f.remove(A);
        });
        }
    }

    @FXML
    private void delete(ActionEvent event) {
          ObservableList<Evenement> r,f;
        f=table_view.getItems();
        r=table_view.getSelectionModel().getSelectedItems();
        EvenementServices Ev=new EvenementServices();
        if(r!=null){
        r.stream().map((A) -> {
            Ev.SupprimerEvenement(A);
            return A;
        }).forEach((A) -> {
            f.remove(A);
        });
        }
    }
    
}
