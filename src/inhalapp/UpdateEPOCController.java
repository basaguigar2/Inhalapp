/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inhalapp;

import jdbc.interfaces.ComorbidityManager;
import jdbc.interfaces.DBManager;
import jdbc.interfaces.EpocManager;
import jdbc.interfaces.PatientManager;
import jdbc.interfaces.TreatmentManager;
import jdbc.sqlite.SQLiteManager;
import static inhalapp.RegisterController.showAlert;
import java.net.URL;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Window;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.Comorbidity;
import pojos.EPOC;
import pojos.Patient;
import pojos.PulmonaryCondition;
import pojos.Treatment;
import static pojos.Treatment.INFLUENZAV;

/**
 *
 * @author gisel
 */
public class UpdateEPOCController implements Initializable {

    @FXML
    private TabPane TabPane;

    @FXML
    private Tab Update_EPOC1, Update_EPOC2;

    @FXML
    private ComboBox<PulmonaryCondition> pulmonary_condition_comboBox;

    @FXML
    private CheckBox pneumonia_tab5, influenza_tab5, vision_tab5, viral_tab5, tuberculosis_tab5, cardiovascular_tab5, influenza_tab, pneumonia_tab, vision_tab, viral_tab, tuberculosis_tab, cardiovascular_tab;

    @FXML
    private TextField mMRC_tab5, CAT_tab5, exacer_tab5, eos_tab6, exacer_tab6, fev_tab6;

    @FXML
    private ToggleGroup eosinophilia, complications_EPOC;

    @FXML
    private RadioButton eosinophilia_yes, eosinophilia_no, exacer_boolean, disnea_boolean;

    @FXML
    private Button backButton, checkTreatment1, backButton1, checkTreatment2;

    @FXML
    private Label mMRCError, CATError, exaError, EOSError, FEVError;

    private Patient selectedPatient;
    private EPOC e = new EPOC();

    private static DBManager dbManager;
    private static PatientManager patientmanager;
    private static ComorbidityManager comorbiditymanager;
    private static TreatmentManager treatmentmanager;
    private static EpocManager epocmanager;
    private static SceneChanger sc;

    @FXML
    public void backtoMenu(ActionEvent event) {
        sc = new SceneChanger();
        sc.changeScenes(event, "showPatient.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedPatient = MenuUserController.getP();
        if (!selectedPatient.isHospitalization()) {
            TabPane.getSelectionModel().select(Update_EPOC1);
            Update_EPOC2.setDisable(true);
        } else {
            TabPane.getSelectionModel().select(Update_EPOC2);
            Update_EPOC1.setDisable(true);
        }
        if (selectedPatient.getPatientAge() > 64) {
            pneumonia_tab.setDisable(true);
            pneumonia_tab5.setDisable(true);
        }
        this.backButton.setDisable(false);
        this.backButton1.setDisable(false);

        dbManager = InhalApp.getDBManager();
        patientmanager = dbManager.getPatientManager();
        comorbiditymanager = dbManager.getComorbidityManager();
        treatmentmanager = dbManager.getTreatmentManager();
        epocmanager = dbManager.getEPOCManager();

        pulmonary_condition_comboBox.getItems().addAll(Arrays.asList(PulmonaryCondition.values()));
        pulmonary_condition_comboBox.getSelectionModel().select(PulmonaryCondition.NONE);
        eosinophilia = new ToggleGroup();
        eosinophilia_yes.setToggleGroup(eosinophilia);
        eosinophilia_no.setToggleGroup(eosinophilia);
        complications_EPOC = new ToggleGroup();
        exacer_boolean.setToggleGroup(complications_EPOC);
        disnea_boolean.setToggleGroup(complications_EPOC);
    }

    @FXML
    public void checkCOPD(ActionEvent event) throws SQLException, NotBoundException {
        Comorbidity c = new Comorbidity();
        if (influenza_tab5.isSelected()) {
            selectedPatient.setInfluenza_vaccine(true);
        } else {
            selectedPatient.setInfluenza_vaccine(false);
        }
        if (pneumonia_tab5.isSelected()) {
            selectedPatient.setPneumonia_vaccine(true);
        } else {
            selectedPatient.setPneumonia_vaccine(true);
        }
        if (cardiovascular_tab5.isSelected()) {
            c.setComorbidityName("cardiovascular diseases");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (tuberculosis_tab5.isSelected()) {
            c.setComorbidityName("pulmonary tuberculosis");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (viral_tab5.isSelected()) {
            c.setComorbidityName("viral infection");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (vision_tab5.isSelected()) {
            c.setComorbidityName("vision disorders");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
            System.out.println(selectedPatient.getString_comorbidities());
        }

        String condition = pulmonary_condition_comboBox.getValue().toString();
        boolean eosinophilia;

        if (eosinophilia_yes.isSelected()) {
            eosinophilia = true;
        } else {
            eosinophilia = false;
        }

        int mMRC = Integer.parseInt(mMRC_tab5.getText());
        int CAT = Integer.parseInt(CAT_tab5.getText());
        int exa = Integer.parseInt(exacer_tab5.getText());

        Boolean validData = validateCOPD(mMRC, CAT, exa);

        if (validData == true && (eosinophilia_yes.isSelected() || eosinophilia_no.isSelected()) && pulmonary_condition_comboBox.getValue() != null) {
            e.setCondition_string(condition);
            e.setEosinophilia(eosinophilia);
            e.setmMRC(mMRC);
            e.setCAT(CAT);
            e.setExa(exa);
            epocmanager.addEPOC(e);
            int epoc_Id = epocmanager.getLastId();
            patientmanager.editPatient(selectedPatient.getId(), selectedPatient.isHospitalization(), selectedPatient.isInfluenza_vaccine(), selectedPatient.isPneumonia_vaccine(), null);
            patientmanager.introduceEPOC(selectedPatient.getId(), epoc_Id);
            selectedPatient.setEpoc(e);
            checkTreatmentButtonPushed();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing data");
            alert.setHeaderText("Please, fulfill all the sections");
            alert.showAndWait();
        }
        clearDataUpdateCOPD();
    }

    @FXML
    public void checkCOPDWithDeterioration(ActionEvent event) throws SQLException {
        Comorbidity c = new Comorbidity();
        int epoc_Id = 0;
        e = epocmanager.getEPOCFromPatient(selectedPatient.getId());
        if (influenza_tab.isSelected()) {
            selectedPatient.setInfluenza_vaccine(true);
        } else {
            selectedPatient.setInfluenza_vaccine(false);
        }
        if (pneumonia_tab.isSelected()) {
            selectedPatient.setPneumonia_vaccine(true);
        } else {
            selectedPatient.setPneumonia_vaccine(false);
        }
        if (cardiovascular_tab.isSelected()) {
            c.setComorbidityName("cardiovascular diseases");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (tuberculosis_tab.isSelected()) {
            c.setComorbidityName("pulmonary tuberculosis");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (viral_tab.isSelected()) {
            c.setComorbidityName("viral infection");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (vision_tab.isSelected()) {
            c.setComorbidityName("vision disorders");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }

        boolean disnea, exacerbation;

        if (disnea_boolean.isSelected()) {
            exacerbation = false;
            disnea = true;
        } else {
            exacerbation = true;
            disnea = false;
        }

        int EOS = Integer.parseInt(eos_tab6.getText());
        int FEV = Integer.parseInt(fev_tab6.getText());
        int exa = Integer.parseInt(exacer_tab6.getText());

        Boolean validData = validateCOPDWithDeterioration(EOS, exa, FEV);

        if (validData == true && (disnea_boolean.isSelected() || exacer_boolean.isSelected())) {
            e.setEOS(EOS);
            e.setFEV(FEV);
            e.setDisnea(disnea);
            e.setExacerbations(exacerbation);
            e.setExa(exa);
            
            selectedPatient.setEpoc(e);
            if (selectedPatient.getEpoc().getEOS() == null) {
                epocmanager.addEPOC2(e);
                patientmanager.editPatient(selectedPatient.getId(), selectedPatient.isInfluenza_vaccine(), null, selectedPatient.isPneumonia_vaccine(), null);
                epoc_Id = epocmanager.getLastId();
                patientmanager.introduceEPOC(selectedPatient.getId(), epoc_Id);
                checkTreatmentButtonPushed();
            } else {
                patientmanager.editPatient(selectedPatient.getId(), selectedPatient.isInfluenza_vaccine(), null, selectedPatient.isPneumonia_vaccine(), null);
                epocmanager.editEPOC(epoc_Id, EOS, FEV, exa, exacerbation);
                selectedPatient.setEpoc(e);
                checkTreatmentButtonPushed();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing data");
            alert.setHeaderText("Please, fulfill all the sections");
            alert.showAndWait();
        }
        clearDataUpdateCOPDWithDeterioration();
    }

    public void checkTreatmentButtonPushed() throws SQLException {
        Window window = TabPane.getScene().getWindow();
        int initial_length = selectedPatient.treatment_list.size();
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        KieSession ksession = kc.newKieSession("diagnosisKS");

        ksession.insert(selectedPatient);

        ksession.fireAllRules();
        ksession.dispose();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        List<Treatment> treatment = selectedPatient.getTreatment_List();
        System.out.println(selectedPatient.getTreatment_List());
        if (treatment.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, window, "No treatment available", "Change device and investigate other symptoms");
        } else {
            if (treatment == null) {
                alert.setHeaderText("No adequate treatment was found");
            } else {
                int final_length = treatment.size() - initial_length;
                String conc = "";
                for (int i = treatment.size() - final_length; i < treatment.size(); i++) {
                    System.out.println(treatment.get(i));
                    conc += treatment.get(i) + "\n";
                }
                
                alert.setTitle("The recommended treatment is: ");
                alert.setHeaderText(conc);
                alert.show();
                treatmentmanager.addTreatment(selectedPatient.treatment_list.get(treatment.size() - 1));
                int treatmentId = dbManager.getLastId();
                selectedPatient.setHospitalization(true);
                patientmanager.editPatient(selectedPatient.getId(), null, selectedPatient.isHospitalization(), null, null);
                patientmanager.introduceTreatment(selectedPatient.getId(), treatmentId);
            }
        }
    }

    public boolean validateCOPD(int mMRC, int CAT, int exa) {
        boolean validData = true;

        if (this.mMRC_tab5.getText().equals("") || Integer.parseInt(this.mMRC_tab5.getText()) < 0 || Integer.parseInt(this.mMRC_tab5.getText()) > 4) {
            validData = false;
        }
        if (this.CAT_tab5.getText().equals("") || Integer.parseInt(this.CAT_tab5.getText()) < 0 || Integer.parseInt(this.CAT_tab5.getText()) > 40) {
            validData = false;
        }
        if (this.exacer_tab5.getText().equals("") || Integer.parseInt(this.exacer_tab5.getText()) < 0 || Integer.parseInt(this.exacer_tab5.getText()) > 2) {
            validData = false;
        }
        return validData;
    }

    public boolean validateCOPDWithDeterioration(int EOS, int exa, int FEV) {
        boolean validData = true;

        if (this.eos_tab6.getText().equals("")) {
            validData = false;
        }
        if (this.exacer_tab6.getText().equals("") || Integer.parseInt(this.exacer_tab6.getText()) < 0 || Integer.parseInt(this.exacer_tab6.getText()) > 2) {
            validData = false;
        }
        if (this.fev_tab6.getText().equals("")) {
            validData = false;
        }
        return validData;
    }

    public void clearDataUpdateCOPD() {
        this.mMRC_tab5.clear();
        this.CAT_tab5.clear();
        this.exacer_tab5.clear();
        this.pulmonary_condition_comboBox.setValue(PulmonaryCondition.NONE);
        this.influenza_tab5.setSelected(false);
        this.pneumonia_tab5.setSelected(false);
        this.vision_tab5.setSelected(false);
        this.viral_tab5.setSelected(false);
        this.tuberculosis_tab5.setSelected(false);
        this.cardiovascular_tab5.setSelected(false);
        this.eosinophilia.selectToggle(eosinophilia_no);
    }

    public void clearDataUpdateCOPDWithDeterioration() {
        this.eos_tab6.clear();
        this.exacer_tab6.clear();
        this.fev_tab6.clear();
        this.influenza_tab.setSelected(false);
        this.pneumonia_tab.setSelected(false);
        this.vision_tab.setSelected(false);
        this.viral_tab.setSelected(false);
        this.tuberculosis_tab.setSelected(false);
        this.cardiovascular_tab.setSelected(false);
        this.complications_EPOC.selectToggle(exacer_boolean);
    }
}
