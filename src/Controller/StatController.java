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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import services.EvenementServices;

public class StatController implements Initializable {

    @FXML
    private PieChart pie;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i,j,k;
        EvenementServices a = new EvenementServices();
        i= a.Count("formation");
        j=a.Count("Exposition");
        k= a.Count("autres");
        ObservableList<PieChart.Data> pieE=
             FXCollections.observableArrayList(
             new PieChart.Data("formation", i),
             new PieChart.Data("Exposition", j),
             new PieChart.Data("autres", k)
        );
        pie.setData(pieE);  
    }    
}
