/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.CommentEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CommentEventServices;

public class AffichCommentEventController implements Initializable {
    @FXML
    private TableView<CommentEvent> tableCom;
    @FXML
    private TableColumn<CommentEvent, String> Commentaire;
    @FXML
    private TableColumn<CommentEvent, String>  date;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            CommentEventServices v =  new  CommentEventServices();
            ArrayList arrayList = (ArrayList) v.AfficherAllCommA();
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            tableCom.setItems(observablelist);
            Commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }    
    
}
