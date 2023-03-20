/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inhalapp;

import db.interfaces.UserManager;
import db.sqlite.SQLiteUserManager;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Properties;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.*;
import pojos.User;

/**
 *
 * @author gisel
 */
public class RegisterController {
    
    private static  UserManager userman= new SQLiteUserManager();
    
    @FXML
    private PasswordField password, repeatpassword;

    @FXML
    private Button register;

    @FXML
    private TextField username,emailtxt;
    
    private boolean check=true;
    private static SceneChanger sc;

    @FXML
    void RegisterUser(ActionEvent event) throws IOException {
        
        Window window = register.getScene().getWindow();

        if((username.getText().isEmpty())) {
            showAlert(Alert.AlertType.ERROR, window, "Error!", "Please enter your username");
            return;
        }
        
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Error!", "Please enter your password");
            return;
        }
        
        if (repeatpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Error!", "Please enter your password repeated");
            
            return;
        }
        
        String username = this.username.getText();
        String email= this.emailtxt.getText();
        
        if(checkData(email)==false){
            showAlert(Alert.AlertType.ERROR, window, "Error!", "Please enter an existent gmail.com account");
            return;
        }
        
        String password = this.password.getText();
        String confirmPassword = this.repeatpassword.getText();
        
        if(!password.equals(confirmPassword)) {
            check=false;
            showAlert(Alert.AlertType.ERROR, window, "Error!", "he passwords introduced do not match");
            return;
        }else{
               check=true;
        }
        if(check==true){
            try{
                boolean userRepeated= userman.existingUserName(username);

                if(userRepeated) {
                    infoMessage("ERROR, existing user", null, "Failed");
                }else {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] hash = md.digest();
                    User user = new User(username, Arrays.toString(hash));
                    userman.addUser(user);
                    sendEmail("Welcome to Inhalapp!", "Your user is: "+username+"\n"+"Your password is: "+password,email);
                    Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));
                    Scene scene = new Scene(root);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void backtoMenu(ActionEvent event) {
        sc = new SceneChanger();
        sc.changeScenes(event, "logIn.fxml");
    }

    public static void infoMessage(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
       
       public void sendEmail (String asunto, String cuerpo, String destinatario){
        String remitente = "inhalapp@gmail.com";

        Properties props = System.getProperties();
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.clave", "Inhalapp2023*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
         //El puerto SMTP seguro de Google

        Session session = Session.getInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipients(Message.RecipientType.TO, destinatario);
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "Inhalapp2023*");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

       public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
       }
       
       public boolean checkData(String email){
           boolean data= true;
           if(email.contains("@gmail.com")==false){
               data=false;
               check=false;
           }else{
               check=true;
           }
           return data;
       }
}
