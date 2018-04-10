/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.notifEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CommentEventServices;
import static services.UserService.conn;
import services.notifEventServices;

/**
 * FXML Controller class
 *
 * @author lv
 */
public class ListNotifEVController implements Initializable {

    @FXML
    private TableView<notifEvent> list;
    @FXML
    private TableColumn<notifEvent,String> text;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        notifEventServices v =  new  notifEventServices();
            ArrayList arrayList = (ArrayList) v.AfficherUserNotif(conn);
            ObservableList observablelist = FXCollections.observableArrayList(arrayList);
            list.setItems(observablelist);
            text.setCellValueFactory(new PropertyValueFactory<>("text"));
 v.ModifierEtat(conn);
 
        
    }    
    
}
