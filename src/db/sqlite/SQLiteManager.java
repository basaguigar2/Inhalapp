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
                    + "age TEXT NOT NULL, "
                    + "gender TEXT NOT NULL, "
                    + "pregnancy TEXT NOT NULL, "
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
                    + "duration TEXT , "
                    + "userId FOREING KEY REFERENCES users(userid) ON UPDATE CASCADE ON DELETE SET NULL)";
            stmt5.executeUpdate(sql5);
            stmt5.close();
            
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
