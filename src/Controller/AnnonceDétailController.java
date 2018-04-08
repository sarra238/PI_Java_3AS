/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AnnoncesClientController.d;
import Entities.AvisAnnonce;
import Entities.CommentAnn;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.AvisAnnoncesServices;
import services.CommentAnnoncesServices;
import static services.UserService.conn;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class AnnonceDétailController implements Initializable {
    @FXML
    private Label nom;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private Label desc;
    @FXML
    private ImageView imgV;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Hyperlink EvalBtn;
    @FXML
    private TextField commentText;
    @FXML
    private Button CommentBtn;
    @FXML
    private TableView<CommentAnn> tableCom;
    @FXML
    private TableColumn<CommentAnn, String> commentaire;
    @FXML
    private TableColumn<CommentAnn, String> date;
    @FXML
    private TableColumn<CommentAnn, Integer> user;
    @FXML
    private Button SuppComBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setText(d.getNomAnnonce());
        type.setText(d.getType());
        prix.setText(Float.toString((float) d.getPrixReducton()));
        desc.setText(d.getDescription());
        File f=new File("C:\\wamp64\\www\\SoukI\\web\\imagesAnnonce\\"+d.getImage());
        Image img=new Image(f.toURI().toString());
        imgV.setImage(img);
        ObservableList ob =FXCollections.observableArrayList("Mauvais","Passable","Bien","Assez Bien","TrésBien");
        combo.setItems(ob);
        CommentAnnoncesServices v =  new  CommentAnnoncesServices();    
        ArrayList arrayList = (ArrayList) v.AfficherAllComment2(d.getId());
        ObservableList observablelist = FXCollections.observableArrayList(arrayList);
        tableCom.setItems(observablelist);
        commentaire.setCellValueFactory(new PropertyValueFactory<>("commentAnn"));
        date.setCellValueFactory(new PropertyValueFactory<>("d"));
        user.setCellValueFactory(new PropertyValueFactory<>("idUser"));
    }    

    @FXML
    private void Evaluer(ActionEvent event) throws IOException {
        AvisAnnoncesServices AAS= new AvisAnnoncesServices();
        AvisAnnonce b=new AvisAnnonce();
        
            b.setAvis((String) combo.getValue());
            b.setIdA(d.getId());
            b.setIdUser(conn);
            AAS.AjouterAvisAnnonce2(b); 
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnonceDétail.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Détail Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    @FXML
    private void commenter(ActionEvent event) throws IOException {
        CommentAnnoncesServices cs = new CommentAnnoncesServices();
        CommentAnn  c = new CommentAnn();
        c.setIdUser(conn);
        c.setIdA(d.getId());
        c.setCommentAnn(commentText.getText());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = new Date();
        c.setD(format.format(date2));
        if(c.getCommentAnn()!=null){cs.AjouterCommentAnnonce2(c); }
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnonceDétail.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Détail Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    @FXML
    private void SupprimerCommentaire(ActionEvent event) throws IOException {
        ObservableList<CommentAnn> r,fo;
        CommentAnnoncesServices Ann=new CommentAnnoncesServices();
        fo=tableCom.getItems();
        r=tableCom.getSelectionModel().getSelectedItems();
        if(r.size()>0){ //&& user.equals(conn)){
           for(CommentAnn A : r){
            Ann.SupprimerCommentA2(A);
           }
        }
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AnnonceDétail.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Détail Annonce!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
    
}
