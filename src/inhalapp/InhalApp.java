/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inhalapp;

import db.interfaces.DBManager;
import db.sqlite.SQLiteManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gisel
 */
public class InhalApp extends Application{
    private static DBManager dbManager = new SQLiteManager();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        dbManager.connect();
        dbManager.createTables();
        launch(args);
        dbManager.disconnect();
    }
}
