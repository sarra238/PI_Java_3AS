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
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class StatProduitRegionController implements Initializable {
    @FXML
    private PieChart pie;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         int i,j,k,r,l,m,f;
        ServiceProduit a = new ServiceProduit();
        i= ServiceProduit.Count("Tunis");
        j=ServiceProduit.Count("Ariana");
        k= ServiceProduit.Count("Sfax");
        r= ServiceProduit.Count("Sousse");
        f=ServiceProduit.Count("Gabes");
        l= ServiceProduit.Count("Nabeul");
        m= ServiceProduit.Count("Bizerte");
        ObservableList<PieChart.Data> pieE=
             FXCollections.observableArrayList(
             new PieChart.Data("Tunis", i),
             new PieChart.Data("Ariana", j),
             new PieChart.Data("Sfax", k),
             new PieChart.Data("Sousse", r),
             new PieChart.Data("Gabes", f),
             new PieChart.Data("Nabeul", l),
             new PieChart.Data("Bizerte", m)
        );
        pie.setData(pieE);  
    }    
    
}
