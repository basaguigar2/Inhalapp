/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inhalapp;

import db.interfaces.ComorbidityManager;
import db.interfaces.DBManager;
import db.interfaces.PatientManager;
import db.interfaces.TreatmentManager;
import db.sqlite.SQLiteManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojos.Comorbidity;
import pojos.Patient;
import pojos.Treatment;

/**
 *
 * @author gisel
 */
public class ShowPatientController implements Initializable, Controller{
    
    @FXML
    private TextField nameSurnameTextField, medCardeTextField, respDiseaseTextField;
    
    @FXML
    private TableView<Comorbidity> comorbidityTable;

    @FXML
    private TableView<Treatment> treatmentTable;
    
    @FXML
    private TableColumn<Comorbidity, String> comorbidityCol;

    @FXML
    private TableColumn<Treatment, String> treatmentCol;
    
    @FXML
    private Button updateCOPD, updateAsthma, backButton;
    
    private Patient selectedPatient;
    private ObservableList<Comorbidity> comorbidities = FXCollections.observableArrayList();
    private ObservableList<Treatment> treatments = FXCollections.observableArrayList();
    
    private static DBManager dbManager;
    private static PatientManager patientmanager;
    private static ComorbidityManager comorbiditymanager;
    private static TreatmentManager treatmentmanager;
    private static SceneChanger sc;
    
    @FXML
    public void backtoMenu(ActionEvent event) {
        sc = new SceneChanger();
        sc.changeScenes(event, "menuUser.fxml");
    }
    
    @FXML
    public void updateCOPDButtonPushed(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("updateEPOC.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void updateAsthmaButtonPushed(ActionEvent event) {
        try{
            sc = new SceneChanger();
            Parent root = FXMLLoader.load(getClass().getResource("updateAsthma.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void setInfoPatient(Patient patient) {
        this.selectedPatient = patient;
        this.nameSurnameTextField.setText(selectedPatient.getName());
        this.medCardeTextField.setText(selectedPatient.getMedical_card_number().toString());
        this.respDiseaseTextField.setText(selectedPatient.getRespiratorydisease());
        
        comorbidities.addAll(selectedPatient.getComorbidity());
        treatments.addAll(selectedPatient.getTreatment_List());
        comorbidityTable.setItems(comorbidities);
        treatmentTable.setItems(treatments);
        
        if(selectedPatient.getRespiratorydisease().equalsIgnoreCase("Asthma")){
            this.updateCOPD.setDisable(true);
        } else if (selectedPatient.getRespiratorydisease().equalsIgnoreCase("COPD")){
            this.updateAsthma.setDisable(true);
        }
        this.nameSurnameTextField.setEditable(false);
        this.medCardeTextField.setEditable(false);
        this.respDiseaseTextField.setEditable(false);
        this.treatmentTable.setEditable(false);
        this.comorbidityTable.setEditable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*this.updateAsthma.setDisable(true);
        this.updateCOPD.setDisable(true);*/
        this.backButton.setDisable(false);

        dbManager = InhalApp.getDBManager();
        patientmanager = dbManager.getPatientManager();
        comorbiditymanager = dbManager.getComorbidityManager();
        treatmentmanager = dbManager.getTreatmentManager();

        treatmentTable.setEditable(false);
        comorbidityTable.setEditable(false);
        
        comorbidityCol.setCellValueFactory(new PropertyValueFactory<Comorbidity, String>("cname"));
        treatmentCol.setCellValueFactory(new PropertyValueFactory<Treatment, String>("drug"));
        selectedPatient = MenuUserController.getP();
        setInfoPatient(selectedPatient);
    }
}
