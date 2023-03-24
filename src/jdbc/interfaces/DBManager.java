/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.interfaces;

import jdbc.sqlite.SQLiteUserManager;
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
    public SQLiteUserManager getUserManager();
    public boolean createTables();
    public int getLastId();
}
