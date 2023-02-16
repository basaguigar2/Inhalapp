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
    public void introduceAllergy(int medCardNumber, int allergyId);
    public void introduceDisease (int medCardNumber, int diseaseId);
    public Patient selectPatient(int medCardNumber);
    public ArrayList<Patient> selectAllPatients();
    public boolean editPatient(Integer medCardNum, String name, String surname, Integer age, String gender, boolean pregnancy);
    public void createLinkUserPatient(int userId, int medCardNumber);
    public void createLinkDoctorPatient(int medCardNumber, int doctorId);
}
