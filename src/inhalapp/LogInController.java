/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inhalapp;

import db.interfaces.UserManager;
import db.sqlite.SQLiteUserManager;
import java.awt.event.MouseEvent;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import pojos.User;

/**
 *
 * @author gisel
 */
public class LogInController {
    
    private static  UserManager userman= new SQLiteUserManager();
    
    @FXML
    private PasswordField passwordlogin;

    @FXML
    private Button loginbutton;

    @FXML
    private TextField usernamelogin;
    
    
    @FXML
    public void loginUser(ActionEvent event) {
        Window owner = loginbutton.getScene().getWindow();
        if (usernamelogin.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your username.");
            return;
        }
        if (passwordlogin.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your password.");
            return;
        }

        String username = usernamelogin.getText();
        String password = passwordlogin.getText();

        User user = userman.checkPassword(username, password);

        if (user == null) {
            infoMessage("Please enter correct username or password", null, "Failed");
        } else {
            infoMessage("Successfull log in !!", null, "Message");
            try{
              Parent root = FXMLLoader.load(getClass().getResource("menuUser.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void goToRegister(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public static void infoMessage(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
    }
}
