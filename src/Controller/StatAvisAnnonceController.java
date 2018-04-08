/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import services.AvisAnnoncesServices;

public class StatAvisAnnonceController implements Initializable {
    @FXML
    private PieChart pie;
    @FXML
    private Label statlabel;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i,j,k,f,r;
       AvisAnnoncesServices A = new AvisAnnoncesServices();
       i=A.CountAvis("Mauvais");
       k=A.CountAvis("Passable");
       f=A.CountAvis("Bien");
       r=A.CountAvis("Assez Bien");
       j=A.CountAvis("TrésBien");
        ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("Mauvais",i),
                       new PieChart.Data("Passable",k),
                       new PieChart.Data("Bien",f),
                       new PieChart.Data("Assez Bien",r),
                       new PieChart.Data("TrésBien",j)
               );
        pie.setData(pieChartData);
        pie.getData().stream().forEach((PieChart.Data data) -> {
            data.getNode().addEventHandler(MouseEvent.ANY,(MouseEvent e)->{
                statlabel.setVisible(true);
                statlabel.setText(data.getName()+ " : "+data.getPieValue()+"%");
            });
       });
    }    

    @FXML
    private void retour(ActionEvent event) {
        /*Stage stage = (Stage)retour.getScene().getWindow();// ici je suppose que retour est un composant (button, textField, etc) annoté avec @FXML 
        stage.show();
        Stage secondStage= (Stage)( (Node) (event.getSource())).getScene().getWindow();
        secondStage.close();*/
    }
    
}
