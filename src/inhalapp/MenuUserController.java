/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inhalapp;

import jdbc.interfaces.*;
import jdbc.sqlite.SQLiteManager;
import java.io.IOException;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.Comorbidity;
import pojos.Patient;
import pojos.Treatment;
import pojos.User;

/**
 *
 * @author gisel
 */
public class MenuUserController implements Initializable {

    @FXML
    private TabPane TabPane;

    @FXML
    private Tab ListPatient, AddPatient;

    @FXML
    private TextField medical_card_tab4, name_tab4;

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, Integer> medCol;

    @FXML
    private TableColumn<Patient, String> nameCol;

    @FXML
    private TableColumn<Patient, String> diseaseCol;

    @FXML
    private Button seePatientButton, addPatientButton;

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

    @FXML
    private Pane addPatient_pane;

    private static DBManager dbManager = InhalApp.getDBManager();
    private static PatientManager patientmanager = dbManager.getPatientManager();
    private static ComorbidityManager comorbiditymanager = dbManager.getComorbidityManager();
    private static TreatmentManager treatmentmanager = dbManager.getTreatmentManager();
    private static SceneChanger sc;
    private static User u;
    private static Patient p;

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
        try {
            
            sc = new SceneChanger();
            p = this.patientTable.getSelectionModel().getSelectedItem();
            p.setTreatment_List(treatmentmanager.getTreatmentFromPatient(p.getId()));
            p.setComorbidity(comorbiditymanager.getComorbiditiesFromPatient(p.getId()));
            Parent root = FXMLLoader.load(getClass().getResource("showPatient.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = LogInController.getUser();
        System.out.println("Id user en menu "+ u.getUserId());
        medCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("medical_card_number"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        diseaseCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("respiratorydisease"));
        ArrayList<Patient> p = patientmanager.selectAllPatients(u.getUserId());
        patients.addAll(p);
        patientTable.setEditable(true);
        patientTable.setItems(patients);
        sex_comboBox.getItems().addAll("Male", "Female");
        //Creation of radioButtons groups
        respiratory_disease_tab4 = new ToggleGroup();
        copd_tab4.setToggleGroup(respiratory_disease_tab4);
        asthma_tab4.setToggleGroup(respiratory_disease_tab4);

        previously_diagnosed = new ToggleGroup();
        pD_true.setToggleGroup(previously_diagnosed);
        pD_false.setToggleGroup(previously_diagnosed);
    }

    @FXML
    public void addPatientButtonPushed(ActionEvent event) throws NotBoundException, SQLException {
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
            respiratoryDisease = "EPOC";
        }

        if (!pD_true.isSelected()) {
            hospitalization = false;
        } else {
            hospitalization = true;
        }

        Boolean validData = validate(name);

        if (validData == true && (copd_tab4.isSelected() || asthma_tab4.isSelected()) && (pD_true.isSelected() || pD_false.isSelected())) {

            Patient p = new Patient(medCard, name, age, gender, pregnancy, smoker, hospitalization, respiratoryDisease);
            patientmanager.addPatient(p);
            p.setId(patientmanager.getLastId());
            patientmanager.createLinkUserPatient(u.getUserId(), p.getId());
            patients.add(p);
            patientTable.setItems(patients);
            TabPane.getSelectionModel().select(ListPatient);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing or incompatible data");
            alert.setHeaderText("Please, fulfill all the sections correctly");
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
        if (this.sex_comboBox.getValue().equalsIgnoreCase("Male") && this.pregnancy_tab4.isSelected() == true){
            validData = false;
        }
        return validData;
    }

    public void clearData() {
        this.name_tab4.clear();
        this.medical_card_tab4.clear();
        this.sex_comboBox.setValue("Male");
        this.age_picker.setValue(LocalDate.now());
        this.pregnancy_tab4.setSelected(false);
        this.smoker_tab4.setSelected(false);
        this.respiratory_disease_tab4.selectToggle(asthma_tab4);
        this.previously_diagnosed.selectToggle(pD_false);
    }

    public static Patient getP() {
        return p;
    }
}
