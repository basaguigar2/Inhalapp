/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.AsthmaManager;
import db.interfaces.ComorbidityManager;
import db.interfaces.DBManager;
import db.interfaces.PatientManager;
import db.interfaces.TreatmentManager;
import db.interfaces.UserManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import db.interfaces.EpocManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gisel
 */
public class SQLiteManager implements DBManager {

    private Connection c;
    private PatientManager patient;
    private TreatmentManager treatment;
    private EpocManager epoc;
    private AsthmaManager asthma;
    private ComorbidityManager comorbidity;
    private UserManager user;

    public SQLiteManager() {
        super();
    }

    @Override
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./db/InhalApp5.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            patient = new SQLitePatientManager(c);
            treatment = new SQLiteTreatmentManager(c);
            epoc = new SQLiteEpocManager(c);
            asthma = new SQLiteAsthmaManager(c);
            comorbidity = new SQLiteComorbidityManager(c);
            user = new SQLiteUserManager(c);
            System.out.println("Conectado " + c);
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
            System.out.println("Clase no encontrada");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error SQL");
        }
    }

    @Override
    public void disconnect() {
        try {
            c.close();
            System.out.println("Desconectado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SQLitePatientManager getPatientManager() {
        return new SQLitePatientManager(c);
    }

    @Override
    public SQLiteTreatmentManager getTreatmentManager() {
        return new SQLiteTreatmentManager(c);
    }

    @Override
    public SQLiteComorbidityManager getComorbidityManager() {
        return new SQLiteComorbidityManager(c);
    }

    @Override
    public SQLiteEpocManager getEPOCManager() {
        return new SQLiteEpocManager(c);
    }

    @Override
    public SQLiteAsthmaManager getAsthmaManager() {
        return new SQLiteAsthmaManager(c);
    }

    @Override
     public SQLiteUserManager getUserManager() {
        return new SQLiteUserManager(c);
    }

    @Override
    public Connection getConnection() {
        return c;
    }

    @Override
    public boolean createTables() {
        try {
            if (c.isClosed()) {
                System.out.println("Cerrado");
            } else {
                try {
                    Statement stmt1 = c.createStatement();
                    String sql1 = "CREATE TABLE users "
                            + "(userid INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userName TEXT NOT NULL, "
                            + "userPassword TEXT NOT NULL , "
                            + "name TEXT NOT NULL) ";
                    stmt1.executeUpdate(sql1);
                    stmt1.close();
                    
                    Statement stmt3 = c.createStatement();
                    String sql3 = "CREATE TABLE patient "
                            + "(patientid INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "medical_card_number INTEGER NOT NULL, "
                            + "name TEXT NOT NULL, "
                            + "age INTEGER NOT NULL, "
                            + "gender TEXT NOT NULL, "
                            + "pregnancy BOOLEAN, "
                            + "influenzaV BOOLEAN,"
                            + "pneumoniaV BOOLEAN,"
                            + "smoker BOOLEAN, "
                            + "symptoms_controlled BOOLEAN, "
                            + "hospitalization BOOLEAN, "
                            + "respiratorydisease TEXT NOT NULL, "
                            + "treatment_stage INTEGER, "
                            + "userId FOREING KEY REFERENCES users(userid) ON UPDATE RESTRICT ON DELETE CASCADE)";
                    stmt3.executeUpdate(sql3);
                    stmt3.close();
                    
                    Statement stmt5 = c.createStatement();
                    String sql5 = "CREATE TABLE treatment "
                            + "(tid INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "drug TEXT , "
                            + "dose TEXT , "
                            + "therapy TEXT)";
                    stmt5.executeUpdate(sql5);
                    stmt5.close();
                    
                    Statement stmt6 = c.createStatement();
                    String sql6 = "CREATE TABLE EPOC "
                            + "(EPOC_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "condition TEXT, "
                            + "mMRC INTEGER, "
                            + "EOS INTEGER, "
                            + "CAT INTEGER, "
                            + "exa INTEGER, "
                            + "exacerbations BOOLEAN, "
                            + "eosinophilia BOOLEAN, "
                            + "FEV INTEGER)";
                    stmt6.executeUpdate(sql6);
                    stmt6.close();
                    
                    Statement stmt7 = c.createStatement();
                    String sql7 = "CREATE TABLE asthma "
                            + "(asthma_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "stage TEXT, "
                            + "PS INTEGER, "
                            + "PEF INTEGER, "
                            + "SAT_O2 INTEGER, "
                            + "dayTimeSymptoms INTEGER, "
                            + "rescueMedication INTEGER, "
                            + "nocturnalSymptoms INTEGER, "
                            + "limitations INTEGER, "
                            + "pulmonar_function INTEGER, "
                            + "exacerbations INTEGER)";
                    stmt7.executeUpdate(sql7);
                    stmt7.close();
                    
                    Statement stmt8 = c.createStatement();
                    String sql8 = "CREATE TABLE comorbidity "
                            + "(cid INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "cname TEXT NOT NULL)";
                    stmt8.executeUpdate(sql8);
                    stmt8.close();
                    
                    Statement stmt11 = c.createStatement();
                    String sql11 = "CREATE TABLE treatment_patient "
                            + "(patient_id REFERENCES patient(patientid) ON UPDATE RESTRICT ON DELETE CASCADE,"
                            + " treatment_id REFERENCES treatment(tid) ON UPDATE RESTRICT ON DELETE CASCADE, "
                            + " PRIMARY KEY (patient_id,treatment_id))";
                    stmt11.executeUpdate(sql11);
                    stmt11.close();
                    
                    Statement stmt12 = c.createStatement();
                    String sql12 = "CREATE TABLE EPOC_patient "
                            + "(patient_id REFERENCES patient(patientid) ON UPDATE RESTRICT ON DELETE CASCADE,"
                            + " EPOC_id REFERENCES EPOC(EPOC_id) ON UPDATE RESTRICT ON DELETE CASCADE, "
                            + " PRIMARY KEY (patient_id,EPOC_id))";
                    stmt12.executeUpdate(sql12);
                    stmt12.close();
                    
                    Statement stmt13 = c.createStatement();
                    String sql13 = "CREATE TABLE asthma_patient "
                            + "(patient_id REFERENCES patient(patientid) ON UPDATE RESTRICT ON DELETE CASCADE,"
                            + " asthma_id REFERENCES asthma(asthma_id) ON UPDATE RESTRICT ON DELETE CASCADE, "
                            + " PRIMARY KEY (patient_id,asthma_id))";
                    stmt13.executeUpdate(sql13);
                    stmt13.close();
                    
                    Statement stmt14 = c.createStatement();
                    String sql14 = "CREATE TABLE comorbidity_patient "
                            + "(patient_id REFERENCES patient(patientid) ON UPDATE RESTRICT ON DELETE CASCADE,"
                            + " comorbidity_id REFERENCES comorbidity(cid) ON UPDATE RESTRICT ON DELETE CASCADE, "
                            + " PRIMARY KEY (patient_id,comorbidity_id))";
                    stmt14.executeUpdate(sql14);
                    stmt14.close();
                    return true;
                } catch (SQLException tables_error) {
                    if (tables_error.getMessage().contains("already exists")) {
                        System.out.println("Database already exists.");
                        return false;
                    } else {
                        System.out.println("Error creating tables! Abort.");
                        tables_error.printStackTrace();
                        return false;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int getLastId() {
        int result = 0;
        try {
            String query = "SELECT last_insert_rowid() AS lastId";
            PreparedStatement p = c.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            result = rs.getInt("lastId");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
