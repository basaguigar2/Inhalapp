/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.interfaces;

import java.sql.Connection;

/**
 *
 * @author gisel
 */
public interface DBManager {
    
    public void connect();
    public void disconnect();
    public PatientManager getPatientManager(); 
    public DoctorManager getDoctorManager();
    public TreatmentManager getTreatmentManager();
    public ComorbidityManager getComorbidityManager();
    public DiseaseManager getDiseaseManager();
    public AllergyManager getAllergyManager();
    public Connection getConnection();
    public boolean createTables();
}
