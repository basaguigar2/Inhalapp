/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafx;

import db.interfaces.DBManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import pojos.EPOC;
import pojos.Patient;
import pojos.PulmonaryCondition;
import pojos.Treatment;
import pojos.User;

/**
 * FXML Controller class
 *
 * @author basag
 */
public abstract class InterfaceController implements Initializable, DBManager {

    /**
     * Initializes the controller class.
     */
    private User u = null;
    private Patient p = null;
    ObservableList<String> observable_names;
    @FXML
    private TabPane TabPane;
    @FXML
    private Tab LogIn, Register, ListPatient, Addpatient, CheckTreatment, Update_EPOC1, Update_EPOC2, Update_Asthma1, Update_Asthma2, AsthmaCrisis;
    @FXML
    private TextField user_log_in, user_register, medical_card_tab3, name_tab_3, sex_tab3, age_tab3, last_treatment_tab4, medical_card_tab4, name_tab4,mMRC_tab5,CAT_tab5,exacer_tab5,EOS_tab6,FEV_tab6,exacer_tab6;
    @FXML
    private PasswordField password_log_in, password_register, confirm_register;
    @FXML
    private Label register_no_log_in;
    @FXML
    private CheckBox pregnancy_tab3, smoker_tab3, pregnancy_tab4, smoker_tab4, influenza_tab5, pneumonia_tab5, cardiovascular_tab5,tuberculosis_tab5,viral_tab5,vision_tab5;
    @FXML
    private ListView<String> patient_names;
    @FXML
    private RadioButton copd_tab3, asthma_tab3, copd_tab4, asthma_tab4, pD_true, pD_false, eosinophilia_yes, eosinophilia_no, disnea_boolean, exacer_boolean;
    @FXML
    private TextArea treatment_tab3;
    @FXML
    private ToggleGroup respiratory_disease_tab3, respiratory_disease_tab4, previously_diagnosed, eosinophilia, complications_EPOC;
    @FXML
    private DatePicker age_picker;
    @FXML
    private ComboBox<String> sex_comboBox;
    @FXML
    private ComboBox<PulmonaryCondition> pulmonary_condition_comboBox;
    
    @FXML
    private void log_in_button(ActionEvent event) {
        ArrayList<Patient> names = new ArrayList<>();
        String user = user_log_in.getText();
        String password = password_log_in.getText();
        u = getUserMAnager().checkPassword(user, password);
        if (u != null) {
            int id = getUserMAnager().getId(user);
            names = getPatientManager().selectAllPatients(id);
            
            for (int i = 0; i < names.size(); i++) {
                String aux = names.get(i).getPatientName();
                int patient_id = names.get(i).getId();
                observable_names.add(patient_id + ". " + aux);
            }
            patient_names.setItems(observable_names);
            TabPane.getSelectionModel().select(ListPatient);
        } else {
            System.out.println("Error no existe un usuario asi");
        }
    }
    
    @FXML
    public void setBoldOnMouseEnter(MouseEvent event) {
        register_no_log_in.setStyle("-fx-font-weight: bold;");
    }
    
    @FXML
    public void setNormalOnMouseExit(MouseEvent event) {
        register_no_log_in.setStyle("-fx-font-weight: normal;");
    }
    
    @FXML
    private void go_to_register(MouseEvent event) {
        TabPane.getSelectionModel().select(Register);
    }
    
    @FXML
    private void register_button(ActionEvent event) {
        if (!password_register.getText().equalsIgnoreCase(confirm_register.getText())) {
            System.out.println("Passwords are not the same");
        } else {
            if (!getUserMAnager().existingUserName(user_register.getText())) {
                u.setUsername(user_register.getText());
                u.setPassword(password_register.getText());
                getUserMAnager().addUser(u);
                TabPane.getSelectionModel().select(LogIn);
            } else {
                System.out.println("Existing username");
            }
        }
    }
    
    @FXML
    private void go_to_addPatient(ActionEvent event) {
        TabPane.getSelectionModel().select(Addpatient);
    }
    
    public void setInfoPatient(Patient p) {
        medical_card_tab3.setText(String.valueOf(p.getMedical_card_number()));
        name_tab_3.setText(p.getPatientName());
        age_tab3.setText(p.getPatientAge().toString());
        sex_tab3.setText(p.getPatientGender());
        if (p.isPregnant()) {
            pregnancy_tab3.setSelected(true);
        } else {
            pregnancy_tab3.setSelected(false);
        }
        if (p.isSmoker()) {
            smoker_tab3.setSelected(true);
        } else {
            smoker_tab3.setSelected(false);
        }
        if (p.getRespiratorydisease().equalsIgnoreCase("COPD")) {
            copd_tab3.setSelected(true);
            asthma_tab3.setSelected(false);
        } else {
            copd_tab3.setSelected(false);
            asthma_tab3.setSelected(true);
        }
        ArrayList<Treatment> treatment = getTreatmentManager().getTreatmentFromPatient(p.getId());
        for (Treatment t : treatment) {
            treatment_tab3.appendText(t.toString() + "\n");
        }
    }
    
    @FXML
    public void add_Patient(ActionEvent event) throws NotBoundException, SQLException {
        Patient p = new Patient();
        p.setPatientName(name_tab4.getText());
        p.setMedical_card_number(Integer.valueOf(medical_card_tab4.getText()));
        p.setPatientAge(age_picker.getValue());
        p.setPatientGender(sex_comboBox.getValue());
        if (!pregnancy_tab4.isSelected()) {
            p.setPregnancy(false);
        } else {
            p.setPregnancy(true);
        }
        if (!smoker_tab4.isSelected()) {
            p.setSmoker(false);
        } else {
            p.setSmoker(true);
        }
        if (!copd_tab4.isSelected()) {
            p.setRespiratorydisease("Asthma");
        } else {
            p.setRespiratorydisease("COPD");
        }
        if (!pD_true.isSelected()) {
            p.setHospitalization(false);
        } else {
            p.setHospitalization(true);
            //Darle una vuelta
            Treatment t = new Treatment();
            t.setDrug(last_treatment_tab4.getText());
            p.addTreatment(t);
        }
        getPatientManager().addPatient(p);
        int id = 0;//metodo db conseguir ultimo id
        observable_names.add(String.valueOf(id) + " " + name_tab4.getText());
        patient_names.setItems(observable_names);
        TabPane.getSelectionModel().select(ListPatient);
    }
    
    @FXML
    public void update_info(ActionEvent event) {
        if (copd_tab3.isSelected()) {
            if (treatment_tab3.getText().isEmpty()) {
                TabPane.getSelectionModel().select(Update_EPOC1);
            } else {
                TabPane.getSelectionModel().select(Update_EPOC2);
            }
        } else {
            if (treatment_tab3.getText().isEmpty()) {
                TabPane.getSelectionModel().select(Update_Asthma1);
            } else {
                TabPane.getSelectionModel().select(Update_Asthma2);
            }
        }
    }
    
    @FXML
    public void check_EPOC1(ActionEvent event) {
        EPOC e = new EPOC();
        ArrayList <String> cormobidities = new ArrayList<>();
        if(influenza_tab5.isSelected()){
            //update vaccine
        }
        if(pneumonia_tab5.isSelected()){
            //update vaccine
        }
        if (cardiovascular_tab5.isSelected()){
            cormobidities.add("Cardiovascular disease");
        }
        if (tuberculosis_tab5.isSelected()){
            cormobidities.add("Tuberculosis");
        }
        if (viral_tab5.isSelected()){
            cormobidities.add("Viral Infection");
        }
        if (vision_tab5.isSelected()){
            cormobidities.add("Vision Disorder");
        }
        p.setString_comorbidities(cormobidities);
        
        e.setCondition(pulmonary_condition_comboBox.getValue());
        if (eosinophilia_yes.isSelected()) {
            e.setEosinophilia(true);
        } else {
            e.setEosinophilia(false);
        }
        e.setmMRC(Integer.parseInt(mMRC_tab5.getText()));
        e.setCAT(Integer.parseInt(CAT_tab5.getText()));
        e.setExa(Integer.parseInt(exacer_tab5.getText()));
        TabPane.getSelectionModel().select(CheckTreatment);
        //Unir paciente y EPOC o meter EPOC a db
        p.setEpoc(e);
        //Chequear con las rules
    }

    @FXML
    public void check_EPOC2(ActionEvent event) {
        EPOC e = new EPOC();
        ArrayList <String> cormobidities = new ArrayList<>();
        if(influenza_tab5.isSelected()){
            //update vaccine
        }
        if(pneumonia_tab5.isSelected()){
            //update vaccine
        }
        if (cardiovascular_tab5.isSelected()){
            cormobidities.add("Cardiovascular disease");
        }
        if (tuberculosis_tab5.isSelected()){
            cormobidities.add("Tuberculosis");
        }
        if (viral_tab5.isSelected()){
            cormobidities.add("Viral Infection");
        }
        if (vision_tab5.isSelected()){
            cormobidities.add("Vision Disorder");
        }
        p.setString_comorbidities(cormobidities);
        if (disnea_boolean.isSelected()){
            e.setExacerbations(false);
            e.setDisnea(true);
        }else{
            e.setExacerbations(true);
            e.setDisnea(false);
        }
        e.setEOS(Integer.parseInt(mMRC_tab5.getText()));
        e.setFEV(Integer.parseInt(CAT_tab5.getText()));
        e.setExa(Integer.parseInt(exacer_tab5.getText()));
        TabPane.getSelectionModel().select(CheckTreatment);
        //Unir paciente y EPOC o meter EPOC a db
        //Chequear con las rules
        p.setEpoc(e);
    }
    
    @FXML
    public void go_to_crisis(ActionEvent event) {
        TabPane.getSelectionModel().select(AsthmaCrisis);
    }
    @FXML
    public void go_back(ActionEvent event) {
        TabPane.getSelectionModel().select(ListPatient);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = new User();
        observable_names = FXCollections.observableArrayList();
        //Adding the items of the comboBox
        sex_comboBox = new ComboBox<>();
        sex_comboBox.getItems().addAll("Male", "Female", "Other");
        pulmonary_condition_comboBox = new ComboBox<>();
        pulmonary_condition_comboBox.getItems().addAll(Arrays.asList(PulmonaryCondition.values()));
        //Creation of raioButtons groups
        respiratory_disease_tab3 = new ToggleGroup();
        copd_tab3.setToggleGroup(respiratory_disease_tab3);
        asthma_tab3.setToggleGroup(respiratory_disease_tab3);
        respiratory_disease_tab4 = new ToggleGroup();
        copd_tab4.setToggleGroup(respiratory_disease_tab4);
        asthma_tab4.setToggleGroup(respiratory_disease_tab4);
        previously_diagnosed = new ToggleGroup();
        pD_true.setToggleGroup(previously_diagnosed);
        pD_false.setToggleGroup(previously_diagnosed);
        eosinophilia = new ToggleGroup();
        eosinophilia_yes.setToggleGroup(eosinophilia);
        eosinophilia_no.setToggleGroup(eosinophilia);
        complications_EPOC = new ToggleGroup();
        exacer_boolean.setToggleGroup(complications_EPOC);
        disnea_boolean.setToggleGroup(complications_EPOC);
        // Inicializar el manejador de eventos para cambiar entre pestañas
        TabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if (newTab == ListPatient) {
                    patient_names.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldString, String newString) {
                            p = getPatientManager().selectPatient(Character.getNumericValue(newString.charAt(0)));
                            setInfoPatient(p);
                        }
                    });
                    
                } else if (newTab == Addpatient) {
                    if (!pD_false.isSelected()) {
                        last_treatment_tab4.setVisible(true);
                    }
                }
            }
        });
    }
}
