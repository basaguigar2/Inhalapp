/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.AllergyManager;
import db.interfaces.ComorbidityManager;
import db.interfaces.DBManager;
import db.interfaces.DiseaseManager;
import db.interfaces.DoctorManager;
import db.interfaces.PatientManager;
import db.interfaces.RoleManager;
import db.interfaces.TreatmentManager;
import db.interfaces.UserManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gisel
 */
public class SQLiteManager implements DBManager{

    private Connection c;
    private PatientManager patient;
    private DoctorManager doctor;
    private TreatmentManager treatment;
    private DiseaseManager disease;
    private ComorbidityManager comorbidity;
    private AllergyManager allergy;
    private UserManager user;
    private RoleManager role;
    
    public SQLiteManager(){
        super();
    }
    
    @Override
    public void connect() {
         try {
            Class.forName("org.sqlite.JDBC"); 
            c = DriverManager.getConnection("jdbc:sqlite:./db/InhalApp.db"); 
            c.createStatement().execute("PRAGMA foreign_keys=ON"); 
            patient = new SQLitePatientManager(c);
            doctor = new SQLiteDoctorManager(c);
            treatment = new SQLiteTreatmentManager(c);
            disease = new SQLiteDiseaseManager(c);
            comorbidity = new SQLiteComorbidityManager(c);
            allergy = new SQLiteAllergyManager(c);
            user = new SQLiteUserManager(c);
            role = new SQLiteRoleManager(c);
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PatientManager getPatientManager() {
        return patient;
    }

    @Override
    public DoctorManager getDoctorManager() {
        return doctor;
    }

    @Override
    public TreatmentManager getTreatmentManager() {
        return treatment;
    }

    @Override
    public ComorbidityManager getComorbidityManager() {
        return comorbidity;
    }

    @Override
    public DiseaseManager getDiseaseManager() {
        return disease;
    }

    @Override
    public AllergyManager getAllergyManager() {
        return allergy;
    }

    @Override
    public Connection getConnection() {
        return c;
    }

    @Override
    public boolean createTables() {
        try{
            Statement stmt1 = c.createStatement();
            String sql1 = "CREATE TABLE role " 
                + "(roleid INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + "type TEXT NOT NULL)"; 
            stmt1.execute(sql1);
            stmt1.close();
            
            Statement stmt2 = c.createStatement();
            String sql2 = "CREATE TABLE users " 
                + "(userid INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + "userName TEXT NOT NULL, " 
                + "userPassword TEXT NOT NULL , "
                + "email TEXT NOT NULL , "
                + "userRoleid FOREING KEY REFERENCES role(roleid) ON UPDATE CASCADE ON DELETE SET NULL) ";
            stmt2.executeUpdate(sql2);
            stmt2.close();
            
            Statement stmt3 = c.createStatement();
            String sql3 = "CREATE TABLE patient " 
                    + "(medical_card_number INTEGER PRIMARY KEY, " 
                    + "name TEXT NOT NULL, " 
                    + "surname TEXT NOT NULL, "
                    + "age INTEGER, "
                    + "gender TEXT NOT NULL, "
                    + "pregnancy BOOLEAN, "
                    + "userId FOREING KEY REFERENCES users(userid) ON UPDATE RESTRICT ON DELETE CASCADE)";
            stmt3.executeUpdate(sql3);
            stmt3.close();
            
            Statement stmt4 = c.createStatement();
            String sql4 = "CREATE TABLE doctor " 
                    + "(doctorId INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + "dname TEXT NOT NULL, " 
                    + "dsurname TEXT NOT NULL, "
                    + "userId FOREING KEY REFERENCES users(userid) ON UPDATE CASCADE ON DELETE SET NULL)";
            stmt4.executeUpdate(sql4);
            stmt4.close();
            
            Statement stmt5 = c.createStatement();
            String sql5 = "CREATE TABLE treatment " 
                    + "(tid INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + "drug TEXT NOT NULL, " 
                    + "dose TEXT , "
                    + "id_patient FOREING KEY REFERENCES patient(medical_card_number) ON UPDATE CASCADE ON DELETE SET NULL)";
            stmt5.executeUpdate(sql5);
            stmt5.close();
            
            Statement stmt6 = c.createStatement();
            String sql6 = "CREATE TABLE disease " 
                    + "(disease_id INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + "disease_name TEXT NOT NULL, " 
                    + "stage TEXT NOT NULL, " 
                    + "id_patient FOREING KEY REFERENCES patient(medical_card_number) ON UPDATE CASCADE ON DELETE SET NULL)";
            stmt6.executeUpdate(sql6);
            stmt6.close();
            
            Statement stmt7 = c.createStatement();
            String sql7 = "CREATE TABLE comorbidity " 
                    + "(cid INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + "cname TEXT NOT NULL, "
                    + "id_patient FOREING KEY REFERENCES patient(medical_card_number) ON UPDATE CASCADE ON DELETE SET NULL)";
            stmt7.executeUpdate(sql7);
            stmt7.close();
            
            Statement stmt8 = c.createStatement();
            String sql8 = "CREATE TABLE allergy " 
                    + "(aid INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + "aname TEXT NOT NULL, "
                    + "type TEXT NOT NULL, "
                    + "id_patient FOREING KEY REFERENCES patient(medical_card_number) ON UPDATE CASCADE ON DELETE SET NULL)";
            stmt8.executeUpdate(sql8);
            stmt8.close();
            
            Statement stmt9 = c.createStatement();
            String sql9 = "CREATE TABLE doctor_patient "
                               + "(patient_id REFERENCES patient(medical_card_number) ON UPDATE RESTRICT ON DELETE CASCADE,"
                               + " doctor_id REFERENCES doctor(doctorId) ON UPDATE RESTRICT ON DELETE CASCADE, "
                               + " PRIMARY KEY (patient_id,doctor_id))";
            stmt9.executeUpdate(sql9);
            stmt9.close();
            
            Statement stmt10 = c.createStatement();
            String sql10 = "CREATE TABLE treatment_patient "
                               + "(patient_id REFERENCES patient(medical_card_number) ON UPDATE RESTRICT ON DELETE CASCADE,"
                               + " treatment_id REFERENCES treatment(tid) ON UPDATE RESTRICT ON DELETE CASCADE, "
                               + " PRIMARY KEY (patient_id,treatment_id))";
            stmt10.executeUpdate(sql10);
            stmt10.close();
            
            Statement stmt11 = c.createStatement();
            String sql11 = "CREATE TABLE disease_patient "
                               + "(patient_id REFERENCES patient(medical_card_number) ON UPDATE RESTRICT ON DELETE CASCADE,"
                               + " disease_id REFERENCES disease(disease_id) ON UPDATE RESTRICT ON DELETE CASCADE, "
                               + " PRIMARY KEY (patient_id,disease_id))";
            stmt11.executeUpdate(sql11);
            stmt11.close();
            
            Statement stmt12 = c.createStatement();
            String sql12 = "CREATE TABLE comorbidity_patient "
                               + "(patient_id REFERENCES patient(medical_card_number) ON UPDATE RESTRICT ON DELETE CASCADE,"
                               + " comorbidity_id REFERENCES comorbidity(cid) ON UPDATE RESTRICT ON DELETE CASCADE, "
                               + " PRIMARY KEY (patient_id,comorbidity_id))";
            stmt12.executeUpdate(sql12);
            stmt12.close();
            
            Statement stmt13 = c.createStatement();
            String sql13 = "CREATE TABLE allergy_patient_patient "
                               + "(patient_id REFERENCES patient(medical_card_number) ON UPDATE RESTRICT ON DELETE CASCADE,"
                               + " allergy_id REFERENCES allergy(aid) ON UPDATE RESTRICT ON DELETE CASCADE, "
                               + " PRIMARY KEY (patient_id,allergy_id))";
            stmt13.executeUpdate(sql13);
            stmt13.close();
            
            return true;
        }catch(SQLException tables_error){
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
    
}
