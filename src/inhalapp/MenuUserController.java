/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inhalapp;

import db.interfaces.*;
import db.sqlite.SQLiteManager;
import java.net.URL;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.Comorbidity;
import pojos.Patient;
import pojos.Treatment;

/**
 *
 * @author gisel
 */
public class MenuUserController implements Initializable{
    
    @FXML
    private TabPane TabPane;
    
    @FXML
    private Tab ListPatient, AddPatient;
    
    @FXML
    private TextField filter_patient, medical_card_tab4, name_tab4;
    
    @FXML
    private TableView<Patient> patientTable = null;
    
    @FXML
    private TableColumn<Patient, Integer> medCol;
    
    @FXML
    private TableColumn<Patient, String> nameCol, diseaseCol;
    
    @FXML
    private Button obtainTreatmentButton, seePatientButton, addPatientButton;
    
    @FXML
    private ComboBox<String> sex_comboBox;
    
    @FXML
    private DatePicker age_picker;
    
    @FXML
    private ToggleGroup respiratory_disease_tab4, previously_diagnosed;
    
    @FXML
    private RadioButton asthma_tab4, copd_tab4, pD_true, pD_false;
    
    @FXML
    private CheckBox pregnancy_tab4, smoker_tab4;
    
    @FXML
    private Label nameError, medCardError;
    
    private static DBManager dbManager;
    private static PatientManager patientmanager;
    private static ComorbidityManager comorbiditymanager;
    private static TreatmentManager treatmentmanager;    
    private static SceneChanger sc;
    
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
            
    @FXML
    private void go_to_addPatient(ActionEvent event) {
        TabPane.getSelectionModel().select(AddPatient);
    }
    
    @FXML
    private void go_to_ListPatient(ActionEvent event) {
        TabPane.getSelectionModel().select(ListPatient);
    }
    
    @FXML
    public void seePatientButtonPushed(ActionEvent event) {
        sc = new SceneChanger();
        Patient patient = this.patientTable.getSelectionModel().getSelectedItem();
        ShowPatientController spc = new ShowPatientController();
        sc.changeScenesWithPatient(event, "showPatient.fxml", patient, spc);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.seePatientButton.setDisable(true);
        this.obtainTreatmentButton.setDisable(true);
        this.addPatientButton.setDisable(true);

        dbManager = new SQLiteManager();
        patientmanager = dbManager.getPatientManager();
        comorbiditymanager = dbManager.getComorbidityManager();
        treatmentmanager = dbManager.getTreatmentManager();

        medCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("medical_card_number"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        diseaseCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("respiratorydisease"));

        patientTable.setEditable(true);

        listPatients(filter_patient.getText());
        patientTable.setItems(patients);
        
        sex_comboBox = new ComboBox<>();
        sex_comboBox.getItems().addAll("Male", "Female");
        
        //Creation of raioButtons groups
        respiratory_disease_tab4 = new ToggleGroup();
        copd_tab4.setToggleGroup(respiratory_disease_tab4);
        asthma_tab4.setToggleGroup(respiratory_disease_tab4);
        
        previously_diagnosed = new ToggleGroup();
        pD_true.setToggleGroup(previously_diagnosed);
        pD_false.setToggleGroup(previously_diagnosed);
    }
    
    public void listPatients(String name) {
        List<Patient> patientList = new ArrayList<Patient>();
        patientList = patientmanager.ListPatients(name);
        Patient p = new Patient();

        for (int i = 0; i < patientList.size(); i++) {
            List<Comorbidity> comorbidities = new ArrayList<Comorbidity>();
            List<Treatment> treatments = new ArrayList<Treatment>();

            p = patientList.get(i);
            p.setComorbidity(comorbiditymanager.getComorbiditiesFromPatient(p.getId()));
            p.setTreatment_List(treatmentmanager.getTreatmentFromPatient(p.getId()));

            this.patients.add(p);
        }
    }
    
    public void patientSelected() {
        this.seePatientButton.setDisable(false);
        this.obtainTreatmentButton.setDisable(false);
    }
    
    @FXML
    public void obtainTreatmentButtonPushed(ActionEvent event) throws SQLException {

        Patient p = this.patientTable.getSelectionModel().getSelectedItem();
        int initial_length = p.treatment_list.size();
        
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        KieSession ksession = kc.newKieSession("diagnosisKS");

        ksession.insert(p);
        
        ksession.fireAllRules();
        ksession.dispose();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        List<String> treatment = p.getString_treatments();
        int final_length = treatment.size() + initial_length;
               
        if (treatment==null) {
            alert.setHeaderText("No adequate treatment was found");
        } else {
            alert.setHeaderText("The recommended treatment is: ");
            for (int i =0; i < treatment.size(); i++){
                Treatment t= new Treatment();
                t.setDrug(p.treatment_list.get(i).getDrug());
                t.setDose(p.treatment_list.get(i).getDose());
                t.setDose(p.treatment_list.get(i).getTherapy());
                treatmentmanager.addTreatment(t);
                int treatmentId = dbManager.getLastId();
                patientmanager.introduceTreatment(p.medical_card_number, treatmentId);
                alert.setHeaderText("Drug: " + p.treatment_list.get(final_length-i).getDrug() + "\nDose: " + p.treatment_list.get(final_length-i).getDose() + "\nTherapy: " + p.treatment_list.get(final_length-i).getTherapy());
            }
        }
    }
    
    @FXML
    public void addPatientButtonPushed(ActionEvent event) throws NotBoundException, SQLException {
        dbManager = new SQLiteManager();
        patientmanager = dbManager.getPatientManager();
        
        ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female");
        sex_comboBox.setItems(genderList);
        
        String name = name_tab4.getText();
        Integer medCard = Integer.parseInt(medical_card_tab4.getText());
        LocalDate dob = age_picker.getValue();
        LocalDate now = LocalDate.now();
        int age = (int) ChronoUnit.YEARS.between(dob, now);
        String gender = sex_comboBox.getValue();
        Boolean pregnancy;
        Boolean smoker;
        Boolean hospitalization;
        String respiratoryDisease = null;

        if (!pregnancy_tab4.isSelected()) {
            pregnancy = false;
        } else {
            pregnancy = true;
        }
        
        if (!smoker_tab4.isSelected()) {
            smoker = false;
        } else {
            smoker = true;
        }
        
        if (!copd_tab4.isSelected()) {
            respiratoryDisease = "Asthma";
        } else {
            respiratoryDisease = "COPD";
        }
        
        if (!pD_true.isSelected()) {
            hospitalization = false;
        } else {
            hospitalization = true;
        }

        Boolean validData = validate(name);
        
        if (validData == true && (copd_tab4.isSelected()||asthma_tab4.isSelected()) && (pD_true.isSelected()||pD_false.isSelected())) {
            this.nameError.setText("");
            this.medCardError.setText("");
            this.addPatientButton.setDisable(false);
            Patient p = new Patient(medCard, name, age, gender, pregnancy, smoker, hospitalization, respiratoryDisease);
            patientmanager.addPatient(p);
            TabPane.getSelectionModel().select(ListPatient);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing data");
            alert.setHeaderText("Please, fulfill all the sections");
            alert.showAndWait();
        }
     clearData();
    }
    
    public boolean validate(String name) {
        char[] chars = name.toCharArray();
        boolean validData = true;

        for (char c : chars) {
            if (Character.isDigit(c)) {
                validData = false;
                this.name_tab4.clear();
                break;
            }
        }
        
        if (this.name_tab4.getText().equals("")) {
            validData = false;
            this.nameError.setText("*");
        }
        if (this.medical_card_tab4.getText().equals("")) {
            validData = false;
            this.medCardError.setText("*");
        }
        return validData;
    }
    
    public void clearData(){
        this.name_tab4.clear();
        this.medical_card_tab4.clear();
        this.sex_comboBox.setValue("Male");
        this.age_picker.setValue(LocalDate.now());
        this.pregnancy_tab4.setSelected(false);
        this.smoker_tab4.setSelected(false);
        this.respiratory_disease_tab4.selectToggle(asthma_tab4);
        this.previously_diagnosed.selectToggle(pD_false);
    }
    
}