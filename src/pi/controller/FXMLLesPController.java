/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import Entities.Produit;
import services.ServiceProduit;

import static services.ServiceProduit.searchcateg1;
import static services.ServiceProduit.searchcategdate1;
import static services.ServiceProduit.searchdate1;
import static services.ServiceProduit.selectLesProduitE;
import static services.ServiceProduit.selectMesProduitE;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLLesPController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField cherchernom;
    @FXML
    private TextField chercherrgion;
    @FXML
    private JFXListView<Produit> liste;
    @FXML
    private Pane panevoir;
    @FXML
    private ImageView imagee;
    @FXML
    private Label nom;
    @FXML
    private Label categorie1;
    @FXML
    private Label date1;
    @FXML
    private Label prix;
    @FXML
    private Label stock;
    @FXML
    private Text description;
    @FXML
    private ImageView modifier;
    @FXML
    private ImageView ajouter;
    @FXML
    private JFXDatePicker date;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private VBox rechercher;
    List<Produit> data ;
    public static Produit P;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ObservableList categ = FXCollections.observableArrayList(
                "a", "b", "c");
                categorie.setItems(categ);
        panevoir.setVisible(false);
         data = selectMesProduitE(1, 3);
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
                          
                       Button button = new Button("Supprimer");
                        button.setStyle("-fx-background-color: #B9121B;  -fx-text-fill: #ffff;");
                        button.setOnAction((ActionEvent event) -> {
                          
                               this.getListView().getItems().remove(item);  
                               ServiceProduit.deleteProduit(item.getId());
                         try {
                              AnchorPane pane1 ;
                              pane1 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLLesP.fxml"));
                                pane.getChildren().setAll(pane1);
                           } catch (IOException ex) {                           
                               Logger.getLogger(FXMLMesPController.class.getName()).log(Level.SEVERE, null, ex);
                           }
            
//
//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pi/gui/FXML.fxml"));
//Parent root = (Parent) fxmlLoader.load();
//Stage stage = new Stage();
//stage.setScene(new Scene(root));
//stage.show();
                         
                        });
                        
                       
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);
//                            file=0;
                                nom.setText(item.getNomProduit());
                                P = item;

                                categorie1.setText(item.getCategorie());
                                //     date1.setText(item.getDateLancement());
                                //       stock.setText(item.getStock());
//                           cat.setValue(item.getCategorie());
//    lieu.setValue(item.getLieu());
//
//                  //   final URL imageURL = getClass().getResource(item.getImg()); 
//        final Image image = new Image(item.getImg()); 
//        imagee.setImage(image);
//        panevoir.setVisible(true);
                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2,button));
                            vBox.setSpacing(4);
                            //         final URL imageURL = getClass().getResource(item.getImg()); 
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
    private void modidier(MouseEvent event) throws IOException {
         Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pi/gui/FXMLModifier.fxml")));
        stage.setScene(scene);
    }

    @FXML
    private void ajouter(MouseEvent event) throws IOException {
         Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pi/gui/FXML.fxml")));
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
            
           data = searchcategdate1(c,a);
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
                                P = item;
                                categorie1.setText(item.getCategorie());
              
                         
                            });
                            Button button = new Button("Supprimer");
                        button.setStyle("-fx-background-color: #B9121B;  -fx-text-fill: #ffff;");
                        button.setOnAction((ActionEvent event) -> {
                          
                               this.getListView().getItems().remove(item);  
                               ServiceProduit.deleteProduit(item.getId());
                         try {
                              AnchorPane pane1 ;
                              pane1 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLLesP.fxml"));
                                pane.getChildren().setAll(pane1);
                           } catch (IOException ex) {                           
                               Logger.getLogger(FXMLMesPController.class.getName()).log(Level.SEVERE, null, ex);
                           }
            
//
//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pi/gui/FXML.fxml"));
//Parent root = (Parent) fxmlLoader.load();
//Stage stage = new Stage();
//stage.setScene(new Scene(root));
//stage.show();
                         
                        });
                                    
                                    

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2,button));
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

            
           data = searchdate1(a);
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
                                P = item;

                                categorie1.setText(item.getCategorie());
              
                         
                            });
                            Button button = new Button("Supprimer");
                        button.setStyle("-fx-background-color: #B9121B;  -fx-text-fill: #ffff;");
                        button.setOnAction((ActionEvent event) -> {
                          
                               this.getListView().getItems().remove(item);  
                               ServiceProduit.deleteProduit(item.getId());
                         try {
                              AnchorPane pane1 ;
                              pane1 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLLesP.fxml"));
                                pane.getChildren().setAll(pane1);
                           } catch (IOException ex) {                           
                               Logger.getLogger(FXMLMesPController.class.getName()).log(Level.SEVERE, null, ex);
                           }
            
//
//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pi/gui/FXML.fxml"));
//Parent root = (Parent) fxmlLoader.load();
//Stage stage = new Stage();
//stage.setScene(new Scene(root));
//stage.show();
                         
                        });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2,button));
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
            
           data = searchcateg1(c);
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
                                P = item;

                                categorie1.setText(item.getCategorie());
              
                         
                            });
                            Button button = new Button("Supprimer");
                        button.setStyle("-fx-background-color: #B9121B;  -fx-text-fill: #ffff;");
                        button.setOnAction((ActionEvent event) -> {
                          
                               this.getListView().getItems().remove(item);  
                               ServiceProduit.deleteProduit(item.getId());
                         try {
                              AnchorPane pane1 ;
                              pane1 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLLesP.fxml"));
                                pane.getChildren().setAll(pane1);
                           } catch (IOException ex) {                           
                               Logger.getLogger(FXMLMesPController.class.getName()).log(Level.SEVERE, null, ex);
                           }
            
//
//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pi/gui/FXML.fxml"));
//Parent root = (Parent) fxmlLoader.load();
//Stage stage = new Stage();
//stage.setScene(new Scene(root));
//stage.show();
                         
                        });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2,button));
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
     
           data = selectMesProduitE(1, 3);
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
                            //Image imaged = new Image(getClass().getResourceAsStream("Media/delete.png"));

                        Button button = new Button("Supprimer");
                        button.setStyle("-fx-background-color: #B9121B;  -fx-text-fill: #ffff;");
                        button.setOnAction((ActionEvent event) -> {
                          
                               this.getListView().getItems().remove(item);  
                               ServiceProduit.deleteProduit(item.getId());
                         try {
                              AnchorPane pane1 ;
                              pane1 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLLesP.fxml"));
                                pane.getChildren().setAll(pane1);
                           } catch (IOException ex) {                           
                               Logger.getLogger(FXMLMesPController.class.getName()).log(Level.SEVERE, null, ex);
                           }
            

////FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pi/gui/FXML.fxml"));
////Parent root = (Parent) fxmlLoader.load();
////Stage stage = new Stage();
////stage.setScene(new Scene(root));
////stage.show();
//                         
                        });
                        
                       
                            Button button2 = new Button("voir plus");

                            button2.setStyle("-fx-background-color: #ffff;  -fx-text-fill:blue; ");
                            button2.setOnAction((ActionEvent event) -> {
                                panevoir.setVisible(true);
                                nom.setText(item.getNomProduit());
                                P = item;

                                categorie1.setText(item.getCategorie());
                                //     date1.setText(item.getDateLancement());
                                //       stock.setText(item.getStock());
//                           cat.setValue(item.getCategorie());
//    lieu.setValue(item.getLieu());
//
//                  //   final URL imageURL = getClass().getResource(item.getImg()); 
//        final Image image = new Image(item.getImg()); 
//        imagee.setImage(image);
//        panevoir.setVisible(true);
                            });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2,button));
                            vBox.setSpacing(4);
                            //         final URL imageURL = getClass().getResource(item.getImg()); 
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
                                P = item;

                                categorie1.setText(item.getCategorie());
              
                         
                            });
                            Button button = new Button("Supprimer");
                        button.setStyle("-fx-background-color: #B9121B;  -fx-text-fill: #ffff;");
                        button.setOnAction((ActionEvent event) -> {
                          
                               this.getListView().getItems().remove(item);  
                               ServiceProduit.deleteProduit(item.getId());
                         try {
                              AnchorPane pane1 ;
                              pane1 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLLesP.fxml"));
                                pane.getChildren().setAll(pane1);
                           } catch (IOException ex) {                           
                               Logger.getLogger(FXMLMesPController.class.getName()).log(Level.SEVERE, null, ex);
                           }
            
//
//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pi/gui/FXML.fxml"));
//Parent root = (Parent) fxmlLoader.load();
//Stage stage = new Stage();
//stage.setScene(new Scene(root));
//stage.show();
                         
                        });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2,button));
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
                                P = item;

                                categorie1.setText(item.getCategorie());
              
                         
                            });
                            Button button = new Button("Supprimer");
                        button.setStyle("-fx-background-color: #B9121B;  -fx-text-fill: #ffff;");
                        button.setOnAction((ActionEvent event) -> {
                          
                               this.getListView().getItems().remove(item);  
                               ServiceProduit.deleteProduit(item.getId());
                         try {
                              AnchorPane pane1 ;
                              pane1 = FXMLLoader.load(getClass().getResource("/pi/gui/FXMLLesP.fxml"));
                                pane.getChildren().setAll(pane1);
                           } catch (IOException ex) {                           
                               Logger.getLogger(FXMLMesPController.class.getName()).log(Level.SEVERE, null, ex);
                           }
            
//
//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pi/gui/FXML.fxml"));
//Parent root = (Parent) fxmlLoader.load();
//Stage stage = new Stage();
//stage.setScene(new Scene(root));
//stage.show();
                         
                        });

                            VBox vBox = new VBox(
                                    new Text(item.getNomProduit()),
                                    new Text(item.getCategorie()), new Text(String.valueOf(item.getDateLancement())),
                                    new ToolBar(button2,button));
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
