
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inhalapp;


import java.net.URL;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import jdbc.interfaces.AsthmaManager;
import jdbc.interfaces.ComorbidityManager;
import jdbc.interfaces.DBManager;
import jdbc.interfaces.PatientManager;
import jdbc.interfaces.TreatmentManager;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.Asthma;
import pojos.Comorbidity;
import pojos.Patient;
import pojos.Treatment;

/**
 *
 * @author gisel
 */
public class UpdateAsthmaController implements Initializable{
    
    @FXML
    private TabPane TabPane;
    
    @FXML
    private Tab Update_Asthma1, Update_Asthma2, AsthmaCrisis;
    
    @FXML
    private Button backButton, checkTreatmentButton3, backButton2, checkTreatment4, backButton3, checkTreatmentButton5;
    
    @FXML
    private TextField daySymptomsTextField, rescueMedTextField, nocturnalSymptomsTextField, exacerbationsTextField, pulmonarFunctionTextField, satTextField, pefTextField, psTextField;
    
    @FXML
    private CheckBox cardioDisease_tab1, tuberculosis_tab1, viralInfection_tab1, glaucoma_tab1, urinary_tab1, hss_tab1, dbs_tab1, diabetes_tab1, 
            hypertiroidism_tab1, pUlcer_tab1, osteoporosis_tab1, osteoporosis_tab2, pUlcer_tab2, hypertiroidism_tab2, diabetes_tab2, hss_tab2, 
            urinary_tab2, glaucoma_tab2, viralInfection_tab2, tuberculosis_tab2, cardioDisease_tab2, dbd_tab2;
    
    @FXML
    private ToggleGroup symptoms_controlled;
            
    @FXML
    private RadioButton symptoms_yes, symptoms_no;
    
    @FXML
    private ComboBox<Integer> LimitationsChoiceBox;
    
    @FXML
    private Label daySError, rmError, nightSError, exacerbationError, pfError, satError, pefError, psError;
    
    private Patient selectedPatient;
    private Asthma a = new Asthma();
    
    private static DBManager dbManager;
    private static PatientManager patientmanager;
    private static ComorbidityManager comorbiditymanager;
    private static TreatmentManager treatmentmanager;
    private static AsthmaManager asthmamanager;
    private static SceneChanger sc;
    
    @FXML
    public void backtoMenu(ActionEvent event) {
        sc = new SceneChanger();
        sc.changeScenes(event, "showPatient.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectedPatient = MenuUserController.getP();
        if(selectedPatient.getTreatment_List().isEmpty()){
        TabPane.getSelectionModel().select(Update_Asthma1);
        Update_Asthma2.setDisable(true);
        }else{
        TabPane.getSelectionModel().select(Update_Asthma2);
        Update_Asthma1.setDisable(true);
        }
        
        this.backButton.setDisable(false);
        this.backButton2.setDisable(false);
        this.backButton3.setDisable(false);
        
        dbManager = InhalApp.getDBManager();
        patientmanager = dbManager.getPatientManager();
        comorbiditymanager = dbManager.getComorbidityManager();
        treatmentmanager = dbManager.getTreatmentManager();
        asthmamanager = dbManager.getAsthmaManager();
        
        LimitationsChoiceBox.getItems().addAll(Integer.parseInt("0"), Integer.parseInt("1"),Integer.parseInt("2"), Integer.parseInt("3"));
        
        symptoms_controlled = new ToggleGroup();
        symptoms_yes.setToggleGroup(symptoms_controlled);
        symptoms_no.setToggleGroup(symptoms_controlled);

        
    }
    
    @FXML
    public void checkAsthma(ActionEvent event) throws SQLException, NotBoundException {
        System.out.println("inside check asthma");
        Comorbidity c = new Comorbidity();

        if (cardioDisease_tab1.isSelected()){
            c.setComorbidityName("cardiovascular disease");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(this.selectedPatient.getId(), comorbidity_id);
        } 
        if (tuberculosis_tab1.isSelected()){
            c.setComorbidityName("pulmonary tuberculosis");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(this.selectedPatient.getId(), comorbidity_id);
        }
        if (viralInfection_tab1.isSelected()){
            c.setComorbidityName("viral Infection");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (glaucoma_tab1.isSelected()){
            c.setComorbidityName("glaucoma");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (urinary_tab1.isSelected()){
            c.setComorbidityName("urinary retention");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (hss_tab1.isSelected()){
            c.setComorbidityName("HSS axis disease");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (dbs_tab1.isSelected()){
            c.setComorbidityName("Decreased bone mineral density");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (diabetes_tab1.isSelected()){
            c.setComorbidityName("diabetes");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (hypertiroidism_tab1.isSelected()){
            c.setComorbidityName("Hypertiroidism");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (pUlcer_tab1.isSelected()){
            c.setComorbidityName("peptic ulcer");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (osteoporosis_tab1.isSelected()){
            c.setComorbidityName("osteoporosis");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        
        int limitations = LimitationsChoiceBox.getValue();
        
        int dayS = Integer.parseInt(daySymptomsTextField.getText());
        int rm = Integer.parseInt(rescueMedTextField.getText());
        int nightS = Integer.parseInt(nocturnalSymptomsTextField.getText());
        int exacerbations = Integer.parseInt(exacerbationsTextField.getText());
        int pf = Integer.parseInt(pulmonarFunctionTextField.getText());
 
        Boolean validData = validateAsthma(dayS, rm, nightS, exacerbations, pf);
        
        if (validData == true && LimitationsChoiceBox.getValue() != null) {
            
            a.setDayTimeSymptoms_w(dayS);
            a.setrescueMedication_w(rm);
            a.setnocturnalSymptoms_w(nightS);
            a.setexarcebations_y(exacerbations);
            a.setpulmonar_function(pf);
            a.setlimitations(limitations);
            a.setStage_string("NONE");
            
            asthmamanager.addAsthma(a);
            int asthma_Id = asthmamanager.getLastId();
            
            patientmanager.introduceAsthma(selectedPatient.getId(), asthma_Id);
            selectedPatient.setAsthma(a);
            this.checkTreatmentButton3.setDisable(false);
            checkTreatmentButtonPushed();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing data");
            alert.setHeaderText("Please, fulfill all the sections");
            alert.showAndWait();
        }
     clearDataUpdateAsthma();
    }
    
    @FXML
    public void checkAsthmaControlled(ActionEvent event) throws SQLException, NotBoundException {
        Comorbidity c = new Comorbidity();
        int asthma_id=0;
        a = asthmamanager.getAsthmaFromPatient(selectedPatient.getId());
        if (osteoporosis_tab2.isSelected()){
            c.setComorbidityName("Osteoporosis");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (pUlcer_tab2.isSelected()){
            c.setComorbidityName("Peptic ulcer");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (hypertiroidism_tab2.isSelected()){
            c.setComorbidityName("Hypertiroidism");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (diabetes_tab2.isSelected()){
            c.setComorbidityName("Diabetes");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (urinary_tab2.isSelected()){
            c.setComorbidityName("Urinary retention");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (hss_tab2.isSelected()){
            c.setComorbidityName("HSS axis disease");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (dbd_tab2.isSelected()){
            c.setComorbidityName("Decreased bone density");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (glaucoma_tab2.isSelected()){
            c.setComorbidityName("Glaucoma");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (viralInfection_tab2.isSelected()){
            c.setComorbidityName("Viral infection");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (tuberculosis_tab2.isSelected()){
            c.setComorbidityName("Tuberculosis");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        if (cardioDisease_tab2.isSelected()){
            c.setComorbidityName("Cardiovascular disease");
            selectedPatient.addComorbidity(c);
            comorbiditymanager.addComorbidity(c);
            int comorbidity_id = dbManager.getLastId();
            patientmanager.introduceComorbidity(selectedPatient.getId(), comorbidity_id);
        }
        
        boolean scontrolled;
        
        if (symptoms_yes.isSelected()) {
            scontrolled = true;
        } else {
            scontrolled = false;
        }
        
        if((symptoms_yes.isSelected()||symptoms_no.isSelected())) {
            this.checkTreatment4.setDisable(false);
            selectedPatient.setSymptoms_controlled(scontrolled);
            selectedPatient.setAsthma(a);
            asthmamanager.addAsthma(a);
            asthma_id=asthmamanager.getLastId();
            patientmanager.introduceAsthma(selectedPatient.getId(),asthma_id);
            checkTreatmentButtonPushed();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing data");
            alert.setHeaderText("Please, fulfill all the sections");
            alert.showAndWait();
        }
     clearDataUpdateAsthmaControl();
    }
    
    @FXML
    public void checkAsthmaCrisis(ActionEvent event) throws SQLException, NotBoundException {
        
        int sat = Integer.parseInt(satTextField.getText());
        int pef = Integer.parseInt(pefTextField.getText());
        int ps = Integer.parseInt(psTextField.getText());
 
        Boolean validData = validateAsthmaCrisis(sat, pef, ps);
        
        if (validData == true) {
            
            
            this.checkTreatmentButton5.setDisable(false);
            a.setSAT_O2(sat);
            a.setPEF(pef);
            a.setPS(ps);
            a.setStage_string("CRISIS");
            asthmamanager.addAsthma(a);
            
            int asthma_Id = asthmamanager.getLastId();
            
            patientmanager.introduceAsthma(selectedPatient.getId(), asthma_Id);
            selectedPatient.setAsthma(a);
            
            checkTreatmentButtonPushed();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing data");
            alert.setHeaderText("Please, fulfill all the sections");
            alert.showAndWait();
        }
     clearDataAsthmaCrisis();
    }
    
    public void checkTreatmentButtonPushed() throws SQLException {
        
        int initial_length = selectedPatient.treatment_list.size();
        System.out.println(selectedPatient.getAsthma());
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        KieSession ksession = kc.newKieSession("diagnosisKS");

        ksession.insert(selectedPatient);
        
        ksession.fireAllRules();
        ksession.dispose();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        List<Treatment> treatment = selectedPatient.getTreatment_List();
        
               
        if (treatment==null || treatment.isEmpty()) {
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
            treatmentmanager.addTreatment(selectedPatient.treatment_list.get(treatment.size()-1));
            int treatmentId = dbManager.getLastId();
           
            patientmanager.introduceTreatment(selectedPatient.getId(), treatmentId);
             
          
            
           
        }
    }
    
    public boolean validateAsthma(int dayS, int rm, int nightS, int exacerbations, int pf) {
        boolean validData = true;
        
        if (this.daySymptomsTextField.getText().equals("") || Integer.parseInt(this.daySymptomsTextField.getText()) < 0 || Integer.parseInt(this.daySymptomsTextField.getText()) > 10 ) {
            validData = false;
            this.daySError.setText("*");
        }
        if (this.rescueMedTextField.getText().equals("") || Integer.parseInt(this.rescueMedTextField.getText()) < 0 || Integer.parseInt(this.rescueMedTextField.getText()) > 10) {
            validData = false;
            this.rmError.setText("*");
        }
        if (this.nocturnalSymptomsTextField.getText().equals("") || Integer.parseInt(this.nocturnalSymptomsTextField.getText()) < 0 || Integer.parseInt(this.nocturnalSymptomsTextField.getText()) > 10) {
            validData = false;
            this.nightSError.setText("*");
        }
        if (this.exacerbationsTextField.getText().equals("") || Integer.parseInt(this.exacerbationsTextField.getText()) < 0 || Integer.parseInt(this.exacerbationsTextField.getText()) > 3) {
            validData = false;
            this.exacerbationError.setText("*");
        }
        if (this.pulmonarFunctionTextField.getText().equals("") || Integer.parseInt(this.pulmonarFunctionTextField.getText()) < 0 || Integer.parseInt(this.pulmonarFunctionTextField.getText()) > 100) {
            validData = false;
            this.pfError.setText("*");
        }
        return validData;
    }
    
    public boolean validateAsthmaCrisis(int sat, int pef, int ps) {
        boolean validData = true;
        
        if (this.satTextField.getText().equals("") || Integer.parseInt(this.satTextField.getText()) < 0 || Integer.parseInt(this.satTextField.getText()) > 100 ) {
            validData = false;
            this.satError.setText("*");
        }
        if (this.pefTextField.getText().equals("") || Integer.parseInt(this.pefTextField.getText()) < 0 || Integer.parseInt(this.pefTextField.getText()) > 100) {
            validData = false;
            this.pefError.setText("*");
        }
        if (this.psTextField.getText().equals("") || Integer.parseInt(this.psTextField.getText()) < 0 || Integer.parseInt(this.psTextField.getText()) > 9) {
            validData = false;
            this.psError.setText("*");
        }
        return validData;
    }
    
    public void clearDataUpdateAsthma(){
        this.daySymptomsTextField.clear();
        this.rescueMedTextField.clear();
        this.nocturnalSymptomsTextField.clear();
        this.exacerbationsTextField.clear();
        this.pulmonarFunctionTextField.clear();
        this.LimitationsChoiceBox.setValue(0);
        this.cardioDisease_tab1.setSelected(false);
        this.tuberculosis_tab1.setSelected(false);
        this.viralInfection_tab1.setSelected(false);
        this.glaucoma_tab1.setSelected(false);
        this.urinary_tab1.setSelected(false);
        this.hss_tab1.setSelected(false);
        this.dbs_tab1.setSelected(false);
        this.diabetes_tab1.setSelected(false);
        this.hypertiroidism_tab1.setSelected(false);
        this.pUlcer_tab1.setSelected(false);
        this.osteoporosis_tab1.setSelected(false);
    }
    
    public void clearDataUpdateAsthmaControl(){
        this.symptoms_controlled.selectToggle(symptoms_yes);
        this.osteoporosis_tab2.setSelected(false);
        this.pUlcer_tab2.setSelected(false);
        this.hypertiroidism_tab2.setSelected(false);
        this.diabetes_tab2.setSelected(false);
        this.hss_tab2.setSelected(false);
        this.urinary_tab2.setSelected(false);
        this.glaucoma_tab2.setSelected(false);
        this.viralInfection_tab2.setSelected(false);
        this.tuberculosis_tab2.setSelected(false);
        this.cardioDisease_tab2.setSelected(false);
        this.dbd_tab2.setSelected(false);
    }
    
    public void clearDataAsthmaCrisis(){
        this.satTextField.clear();
        this.pefTextField.clear();
        this.psTextField.clear();
    }
}