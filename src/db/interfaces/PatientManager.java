/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojos.Patient;

/**
 *
 * @author gisel
 */
public interface PatientManager {
    public void addPatient(Patient p) throws SQLException; 
    public void introduceComorbidity(int medCardNumber, int comorbidityId); 
    public void introduceTreatment(int medCardNumber, int treatmentId);
    public void introduceEPOC(int medCardNumber, int EPOCId);
    public void introduceAsthma(int medCardNumber, int asthmaId);
    public Patient selectPatient(int medCardNumber);
    public ArrayList<Patient> selectAllPatients(int userId);
    public boolean editPatient(Integer medCardNum, String name, String surname, Integer age, String gender, boolean pregnancy, boolean smoker, boolean symptoms_controlled, boolean hospitalization, String respiratorydisease, Integer treatment_stage);
    public void createLinkUserPatient(int userId, int medCardNumber);
    public void createLinkDoctorPatient(int medCardNumber, int doctorId);
    public ArrayList patient_names(int doc_id);
}
