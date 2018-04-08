/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Annonce;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AnnonceServices;
import utils.Utils;

public class ModifierAnnonceController implements Initializable {
    @FXML
    private Label prixLabel;
    @FXML
    private TextField nomAnnonce;
    @FXML
    private TextField PrixReducton;
    @FXML
    private TextArea description;
    @FXML
    private Button ModifBtn;
    @FXML
    private RadioButton Promotion;
    @FXML
    private ToggleGroup Type;
    @FXML
    private RadioButton Other;
    @FXML
    private Button fileBtn;
    @FXML
    private ImageView imageAnn;
    @FXML
    private TextField id;
    
    private Image image;
    private File f;
    private FileChooser fc;
    private String uuid;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      // TODO
    }    

    public void DATA(Annonce A)
    {
        id.setText(Integer.toString(A.getId()));
        nomAnnonce.setText(A.getNomAnnonce());
        description.setText(A.getDescription());
        PrixReducton.setText(Float.toString((float) A.getPrixReducton()));
        switch (A.getType()) {
            case "Other":
                Other.setSelected(true);
                break;
            case "Promotion":
                Promotion.setSelected(true);
                break;
        }
        f=new File("C:\\wamp64\\www\\SoukI\\web\\imagesAnnonce\\"+A.getImage());
        Image img=new Image(f.toURI().toString());
        imageAnn.setImage(img);
    }
    @FXML
    private void ModifierAnnonce(ActionEvent event) throws IOException
    {
    Annonce A=new Annonce();
        A.setId(Integer.parseInt(id.getText()));
        A.setNomAnnonce(nomAnnonce.getText());
        if(Promotion.isSelected()){
            A.setType(Promotion.getText());
            A.setPrixReducton(Float.parseFloat(PrixReducton.getText()));
            PrixReducton.setEditable(true);
        }
        else if(Other.isSelected()) { 
            A.setType(Other.getText());
            PrixReducton.setEditable(false);
            PrixReducton.setMouseTransparent(true);
        }
        if(fileBtn.isPressed()){A.setImage(uuid);}
        A.setDescription(description.getText());
        AnnonceServices Ann=new AnnonceServices();
        boolean b=(A.getDescription().isEmpty()||A.getNomAnnonce().isEmpty()||A.getType().isEmpty());
        if(b==true)
        {
            ModifBtn.setDisable(b);
        }
        else
        {
        Ann.ModifierAnnonce(A);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.close();
        }
        
    }

    @FXML
    private void fileChooser(ActionEvent event) throws IOException {
        fc = new FileChooser();
        f = fc.showOpenDialog(null);
        if(f!=null)
        {
            uuid=UUID.randomUUID().toString().replaceAll("--","")+".jpg";
            image=new Image(f.toURI().toString(),100,150,true,true);
            imageAnn.setImage(image); 
            Utils u =new Utils();
            String Emp ="C:\\wamp64\\www\\SoukI\\web\\imagesAnnonce\\"+uuid;
            u.CopyImage(Emp, f.toPath().toString());
        } 
    }
    
}
