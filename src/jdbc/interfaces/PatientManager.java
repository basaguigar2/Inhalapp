/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.interfaces;

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
    public boolean editPatient(int id, Boolean influenzaV, Boolean hospitalization,Boolean pneumoniaV, Integer treatment_stage);
    public void createLinkUserPatient(int userId, int medCardNumber);
    public ArrayList patient_names(int doc_id);
    public ArrayList<Patient> ListPatients(String name);
    public int getLastId();
}
