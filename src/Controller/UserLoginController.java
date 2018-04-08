/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.UserService;
import utils.InputValidation;
import org.mindrot.jbcrypt.BCrypt;
import utils.VoiceUtils;

public class UserLoginController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink inscri;
    @FXML
    private Hyperlink MdpOublié;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void Login(ActionEvent event) throws IOException
    {
        
        VoiceUtils v=new VoiceUtils("kevin16");
        UserService Us=new UserService();
        String pass=Us.RechercherUsertByPass(username.getText());
        if(BCrypt.checkpw(mdp.getText(),pass)){ 
            String b;
            User U = new User();
            U.setUsername(username.getText());
            U.setPassword(pass);
            b=Us.login(U);
                switch (b) {
                    case "a:1:{i:0;s:11:\"ROLE_CLIENT\";}":
                    {
                        /*String[] s={"Bonjour cher client"};
                        v.sayMultiple(s);*/
                        /*Alert alert = new InputValidation().getAlert("Succes", "Bienvenue!");
                        alert.showAndWait();*/
                        Notifications.create().title("Succes").text("Bienvenue!").position(Pos.BOTTOM_RIGHT).showConfirm();
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("HomeC.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Home!");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        break;
                    }
                    case "a:1:{i:0;s:12:\"ROLE_ARTISAN\";}":
                    {
                        /*String[] s={"Bonjour cher artisan"};
                        v.sayMultiple(s);*/
                        /*Alert alert = new InputValidation().getAlert("Succes", "Bienvenue!");
                        alert.showAndWait();*/
                        Notifications.create().title("Succes").text("Bienvenue!").position(Pos.BOTTOM_RIGHT).showConfirm(); 
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Home!");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        break;
                    }
                    case "a:1:{i:0;s:13:\"ROLE_ADMIN\";}":
                    {
                        /*String[] s={"Bonjour cher administrator"};
                        v.sayMultiple(s);*/
                        /*Alert alert = new InputValidation().getAlert("Succes", "Bienvenue!");
                        alert.showAndWait();*/
                        Notifications.create().title("Succes").text("Bienvenue!").position(Pos.BOTTOM_RIGHT).showConfirm();
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("HomeAd.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Home!");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        break;
                    }
                    case "hi":
                    {
                       
                       Alert alert = new InputValidation().getAlert("Error", "Verifier Vos données!");
                       alert.showAndWait();
                       Notifications.create().title("Error!").text("Verifiez vos données!").position(Pos.BOTTOM_RIGHT).showWarning();
                       Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Home!");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        break;
                    }
                }
        }
        else if(!BCrypt.checkpw(mdp.getText(),pass))
        {
            Alert alert = new InputValidation().getAlert("Error", "Verifier Vos données!");
            alert.showAndWait();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Home!");
            primaryStage.setScene(scene);
            primaryStage.show();          
        }
    }
     
    
    @FXML
    public void Inscri(ActionEvent event) throws IOException
    {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserSignIn.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inscription!");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
    @FXML
    public void MdpOublié(ActionEvent event) throws IOException
    {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Mot de passe oublié !");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
}
