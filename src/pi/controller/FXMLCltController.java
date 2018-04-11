/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.controller;

import static utils.util.somme;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import Entities.Produit;
import services.ServiceProduit;
import static services.ServiceProduit.searchcateg;
import static services.ServiceProduit.searchcategdate;
import static services.ServiceProduit.searchdate;
import static services.ServiceProduit.selectLesProduitE;
import services.ServiceProduitm;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLCltController implements Initializable {

   @FXML
    private AnchorPane pane;
    @FXML
    private JFXDatePicker date;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField cherchernom;
    @FXML
    private JFXListView<Produit> liste;
    @FXML
    private Pane panevoir;
    @FXML
    private ImageView imagee;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label stock;
    @FXML
    private Text description;
    @FXML
    private Label categorie1;
    @FXML
    private Label date1;
    @FXML
    private VBox rechercher;
     List<Produit> data ;
    @FXML
    private Button contacter;
    public static Produit P1;
    @FXML
    private Button btncart;
    @FXML
    private Label Label;
    @FXML
    private ImageView panier;
    @FXML
    private Button btnaffichecart;
       ObservableList<Produit> listProduit = FXCollections.observableArrayList();
     ObservableList<Produit> listProduitcommande = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ObservableList categ = FXCollections.observableArrayList(
                "Tapis", "Foutas", "Habillement");
                categorie.setItems(categ);
        panevoir.setVisible(false);
         data = selectLesProduitE();
          cherchernom.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterList((String) oldValue, (String) newValue);
                liste.refresh();
            }
        });

        // TODO
        ObservableList<Produit> oblist = FXCollections.observableArrayList();

        oblist.addAll(0, data);
        liste.getItems().addAll(oblist);
        liste.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> arg0) {
                return new ListCell<Produit>() {

                    @Override
                    protected void updateItem(Produit item, boolean bln) {

                        super.updateItem(item, bln);
                        if (item != null) {
   
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);
//                            file=0;
System.out.println(item.getPrix());
                                nom.setText(item.getNomProduit());
                                     P1 = item;


                                categorie1.setText(item.getCategorie());
       
                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2));
                            vBox.setSpacing(4);

                            final Image image = new Image(item.getNomImage());

                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);

                            setGraphic(hBox);

                        }
                    }

                };
            }

        });
        // TODO
    }    

    @FXML
    private void voirpanier(MouseEvent event) {
    }

    
   @FXML
    private void contacter(ActionEvent event) throws IOException {
         Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pi/gui/FXMContacter.fxml")));
        stage.setScene(scene);
        
    }

    @FXML
    private void rechercher(MouseEvent event) {
        if((date.getValue()!=null )&& (categorie.getSelectionModel().getSelectedItem() != null)) 
        {
         liste.getItems().clear();
            liste.getItems().removeAll(data);
            data.removeAll(data);
            data.clear();
            liste.refresh();

            int y = Integer.parseInt(date.getValue().format(DateTimeFormatter.ofPattern("yyyy")));
            int m = Integer.parseInt(date.getValue().format(DateTimeFormatter.ofPattern("MM")));
            int d = Integer.parseInt(date.getValue().format(DateTimeFormatter.ofPattern("dd")));
            Date a = new Date(y - 1900, m - 01, d);

            String c = categorie.getSelectionModel().getSelectedItem();
            
           data = searchcategdate(1, 3,c,a);
        // TODO
        ObservableList<Produit> oblist = FXCollections.observableArrayList();

        oblist.addAll(0, data);
        liste.getItems().addAll(oblist);
        liste.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> arg0) {
                return new ListCell<Produit>() {

                    @Override
                    protected void updateItem(Produit item, boolean bln) {

                        super.updateItem(item, bln);
                        if (item != null) {
                     
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);
                                nom.setText(item.getNomProduit());
                                  P1 = item;

                                categorie1.setText(item.getCategorie());
                         
                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2));
                            vBox.setSpacing(4);
                            final Image image = new Image(item.getNomImage());

                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);

                            setGraphic(hBox);

                        }
                    }

                };
            }

        });
 
            
        }   
        else if((date.getValue()!=null )&& (categorie.getSelectionModel().getSelectedItem() == null)) 
        {
         liste.getItems().clear();
            liste.getItems().removeAll(data);
            data.removeAll(data);
            data.clear();
            liste.refresh();

            int y = Integer.parseInt(date.getValue().format(DateTimeFormatter.ofPattern("yyyy")));
            int m = Integer.parseInt(date.getValue().format(DateTimeFormatter.ofPattern("MM")));
            int d = Integer.parseInt(date.getValue().format(DateTimeFormatter.ofPattern("dd")));
            Date a = new Date(y - 1900, m - 01, d);

            
           data = searchdate(a);
        // TODO
        ObservableList<Produit> oblist = FXCollections.observableArrayList();

        oblist.addAll(0, data);
        liste.getItems().addAll(oblist);
        liste.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> arg0) {
                return new ListCell<Produit>() {

                    @Override
                    protected void updateItem(Produit item, boolean bln) {

                        super.updateItem(item, bln);
                        if (item != null) {
                     
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);
                                nom.setText(item.getNomProduit());
                                  P1 = item;

                                categorie1.setText(item.getCategorie());
                         
                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2));
                            vBox.setSpacing(4);
                            final Image image = new Image(item.getNomImage());

                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);

                            setGraphic(hBox);

                        }
                    }

                };
            }

        });
 
            
        }   
         
         else if((date.getValue()==null )&& (categorie.getSelectionModel().getSelectedItem() != null)) 
        {
         liste.getItems().clear();
            liste.getItems().removeAll(data);
            data.removeAll(data);
            data.clear();
            liste.refresh();

            String c = categorie.getSelectionModel().getSelectedItem();
            
           data = searchcateg(c);
        // TODO
        ObservableList<Produit> oblist = FXCollections.observableArrayList();

        oblist.addAll(0, data);
        liste.getItems().addAll(oblist);
        liste.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> arg0) {
                return new ListCell<Produit>() {

                    @Override
                    protected void updateItem(Produit item, boolean bln) {

                        super.updateItem(item, bln);
                        if (item != null) {
                     
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);
                                nom.setText(item.getNomProduit());
                                  P1 = item;

                                categorie1.setText(item.getCategorie());
                         
                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2));
                            vBox.setSpacing(4);
                            final Image image = new Image(item.getNomImage());

                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);

                            setGraphic(hBox);

                        }
                    }

                };
            }

        });
            
        } else {}  
            
    }

    @FXML
    private void tous(MouseEvent event) {
        liste.getItems().clear();
            liste.getItems().removeAll(data);
            data.removeAll(data);
            data.clear();
            liste.refresh();
     
           data = selectLesProduitE();
        // TODO
        ObservableList<Produit> oblist = FXCollections.observableArrayList();

        oblist.addAll(0, data);
        liste.getItems().addAll(oblist);
        liste.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> arg0) {
                return new ListCell<Produit>() {

                    @Override
                    protected void updateItem(Produit item, boolean bln) {

                        super.updateItem(item, bln);
                        if (item != null) {
                    
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);

                                nom.setText(item.getNomProduit());
                                  P1 = item;

                                categorie1.setText(item.getCategorie());

                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2));
                            vBox.setSpacing(4);
                            final Image image = new Image(item.getNomImage());

                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);

                            setGraphic(hBox);

                        }
                    }

                };
            }

        });  
            
    }

    @FXML
    private void add_to_cart(ActionEvent event) {
          ServiceProduitm sp = new ServiceProduitm();
     
      liste.setEditable(true);
        int selectedIndex = liste.getSelectionModel().getSelectedIndex();

         Produit p = liste.getSelectionModel().getSelectedItem();
       int x=p.getId();

        if (selectedIndex >= 0) {
           liste.getItems().remove(selectedIndex);
           System.out.println(x);
System.out.println(p.getLongitude());
            sp.approuver(p.getLongitude());
            somme +=p.getPrix();
            
           Label.setText("ajout avec succées");
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
            pauseTransition.setOnFinished(z -> Label.setText(""));
                    pauseTransition.play();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pas de Selection un produit");
            alert.setHeaderText("vous n'avez pas sélectionner un produit !");
            alert.setContentText("veuillez sélectionner un produit dans la table");
            alert.showAndWait();

        }

    }

    @FXML
    private void page_précédente(ActionEvent event) throws IOException, SQLException {
             ServiceProduitm sp = new ServiceProduitm();

      listProduitcommande = sp.afficherproduitcommande();
      if (   listProduitcommande.isEmpty())
      {Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Votre panier est vide");
            alert.setHeaderText("Vous n'avez pas encore commander un produit");
            alert.setContentText("veuillez choisir commande un produit");
            alert.showAndWait();}
     else{FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("/pi/gui/panier.fxml"));
    Parent root = loader.load();
    Scene homePageScene = new Scene(root);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(homePageScene);
    appStage.show();
           }}

    
    public void filterList(String oldValue, String newValue) {
    
        ObservableList<Produit> filteredList = FXCollections.observableArrayList();
        if (cherchernom == null || (newValue.length() < oldValue.length()) || newValue == null) {
//liste.refresh();
 ObservableList<Produit> oblist = FXCollections.observableArrayList();


        oblist.addAll(0, data);
                liste.getItems().addAll(oblist);


            liste.refresh();
        } else {
            newValue = newValue.toUpperCase();
            for (Produit ob : liste.getItems()) {
                String filterText = ob.getNomProduit();
                if (filterText.toUpperCase().contains(newValue)) {
                    filteredList.add(ob);
                    
        liste.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> arg0) {
                return new ListCell<Produit>() {

                    @Override
                    protected void updateItem(Produit item, boolean bln) {

                        super.updateItem(item, bln);
                        if (item != null) {
                     
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);
                                nom.setText(item.getNomProduit());
                                  P1 = item;

                                categorie1.setText(item.getCategorie());
                         
                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2));
                            vBox.setSpacing(4);
                            final Image image = new Image(item.getNomImage());

                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);

                            setGraphic(hBox);

                        }
                    }

                };
            }

        });

                }
            }
            liste.setItems(filteredList);
           

        liste.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> arg0) {
                return new ListCell<Produit>() {

                    @Override
                    protected void updateItem(Produit item, boolean bln) {

                        super.updateItem(item, bln);
                        if (item != null) {
                     
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);
                                nom.setText(item.getNomProduit());
                                  P1 = item;

                                categorie1.setText(item.getCategorie());
                         
                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2));
                            vBox.setSpacing(4);
                            final Image image = new Image(item.getNomImage());

                            ImageView imv = new ImageView(image);
                            imv.setFitHeight(130);
                            imv.setFitWidth(130);
                            HBox hBox = new HBox(imv, vBox);
                            hBox.setSpacing(10);

                            setGraphic(hBox);

                        }
                    }

                };
            }

        });
            liste.refresh();
        }
    }
    
}
