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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import services.UserService;
import utils.InputValidation;
import utils.SmsInscri;

public class UserSignInController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField adresse;
    @FXML
    private RadioButton femme;
    @FXML
    private RadioButton homme;
    @FXML
    private TextField email;
    @FXML
    private RadioButton client;
    @FXML
    private RadioButton artisan;
    @FXML
    private TextField tel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button btn;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ToggleGroup roles;
    @FXML
    private Hyperlink cnx;
    @FXML
    private RadioButton Admin;

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
    public void Inscri(ActionEvent event) throws IOException
    {
        User U = new User();
        U.setFname(nom.getText());
        U.setLname(prenom.getText());
        U.setAge((int) Float.parseFloat(age.getText()));
        if(femme.isSelected()){ U.setGender(femme.getText());} else {U.setGender(homme.getText());}
        U.setAddress(adresse.getText());
        U.setEmail(email.getText());
        if(client.isSelected()){ U.setRole("a:1:{i:0;s:12:\"ROLE_CLIENT\";}");} else if(artisan.isSelected()) {U.setRole("a:1:{i:0;s:12:\"ROLE_ARTISAN\";}");} //else {U.setRole("a:1:{i:0;s:13:"+"\"ROLE_ADMIN\""+";}");} 
        U.setPhoneNumber((int) Float.parseFloat(tel.getText()));
        U.setUsername(username.getText());
        U.setPassword(mdp.getText());
        if (InputValidation.validTextField(nom.getText())) {
            Alert alertNom = new InputValidation().getAlert("Nom", "Saisissez votre nom");
            alertNom.showAndWait();
        } 
        else {

            if (InputValidation.validTextField(prenom.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Prenom", "Saisissez votre Prenom");
                alertPrenom.showAndWait();
            } 
            else {
                if (!InputValidation.validEmail(email.getText())) {
                    Alert alertEmail = new InputValidation().getAlert("Email", "Saisissez une adresse email valide");
                    alertEmail.showAndWait();
                }
                else {
                    Integer verif = InputValidation.validPwd(mdp.getText());
                    if (verif == 0) {
                        Alert alertMDP = new InputValidation().getAlert("Mot de passe", "Les mots de passe ne correspondent pas.");
                        alertMDP.showAndWait();
                    } 
                    else {
                        if (!InputValidation.validUsername(username.getText())) {
                            Alert alertUsername = new InputValidation().getAlert("Nom d'utilisateur", "Saisissez un nom d'utilisateur valide");
                            alertUsername.showAndWait();
                        } 
                        else {
                            if (InputValidation.isPhoneNumber(tel.getText()) == 0) {
                                Alert alertnum = new InputValidation().getAlert("Numero Telephone", "Saisissez un numero de telephone valide");
                                alertnum.showAndWait();
                            }
                            else {
        UserService Us = new UserService();
        Us.signIn(U);
       /* SmsInscri sms=new SmsInscri();
        sms.sendSms(U.getPhoneNumber());*/
        Alert alert = new InputValidation().getAlert("Succes", "Vous êtes inscrit dans l'application souk el medina!");
        alert.showAndWait();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Connexion!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        }}}}}}
        
    }
    @FXML
    public void Login(ActionEvent event) throws IOException
    {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login!");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
    
}
