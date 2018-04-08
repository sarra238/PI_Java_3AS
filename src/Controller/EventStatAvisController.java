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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import services.partEvServices;

public class EventStatAvisController implements Initializable {
    @FXML
    private PieChart pie;
    @FXML
    private Label labelStat;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i,k,f;
       partEvServices A = new partEvServices();
       i=A.CountAvis("n'est pas interessé(e)");
       k=A.CountAvis("interessé(e)");
       f=A.CountAvis("participer");
        ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("n'est pas interessé(e)",i),
                       new PieChart.Data("interessé(e)",k),
                       new PieChart.Data("participer",f)
               );
        pie.setData(pieChartData);
        pie.getData().stream().forEach((PieChart.Data data) -> {
            data.getNode().addEventHandler(MouseEvent.ANY,(MouseEvent e)->{
                labelStat.setVisible(true);
                labelStat.setText(data.getName()+ " : "+data.getPieValue()+"%");
            });
       });
    }    
    
}
