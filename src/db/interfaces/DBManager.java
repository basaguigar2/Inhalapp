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
    public TreatmentManager getTreatmentManager();
    public ComorbidityManager getComorbidityManager();
    public EpocManager getEPOCManager();
    public AsthmaManager getAsthmaManager();
    public Connection getConnection();
    public UserManager getUserMAnager();
    public boolean createTables();
}
