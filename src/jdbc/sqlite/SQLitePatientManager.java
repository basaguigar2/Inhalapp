/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.sqlite;

import jdbc.interfaces.PatientManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Patient;

/**
 *
 * @author gisel
 */
public class SQLitePatientManager implements PatientManager {

    private Connection c;

    public SQLitePatientManager(Connection c) {
        this.c = c;
    }

    public SQLitePatientManager() {
        super();
    }

    /**
     * Creates and adds a new patient into the database
     *
     * @param p - [Patient] Patient that is added to the database
     * @throws SQLException
     */
    @Override
    public void addPatient(Patient p) throws SQLException {
        try {
            String sq1 = "INSERT INTO patient (medical_card_number, name, age, gender, pregnancy, smoker, symptoms_controlled, hospitalization, respiratorydisease) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setInt(1, p.getMedical_card_number());
            preparedStatement.setString(2, p.getName());
            preparedStatement.setInt(3, p.getPatientAge());
            preparedStatement.setString(4, p.getPatientGender());
            preparedStatement.setBoolean(5, p.isPregnant());
            preparedStatement.setBoolean(6, p.isSmoker());
            preparedStatement.setBoolean(7, p.isSymptoms_controlled());
            preparedStatement.setBoolean(8, p.isHospitalization());
            preparedStatement.setString(9, p.getRespiratorydisease());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Patient added");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void introduceComorbidity(int patientId, int comorbidityId) {
        try {
            String sql = "INSERT INTO comorbidity_patient (patient_id, comorbidity_id) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, patientId);
            prep.setInt(2, comorbidityId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void introduceTreatment(int medCardNumber, int treatmentId) {
        try {
            String sql = "INSERT INTO treatment_patient (patient_id, treatment_id) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, medCardNumber);
            prep.setInt(2, treatmentId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void introduceEPOC(int medCardNumber, int EPOCId) {
        try {
            String sql = "INSERT INTO EPOC_patient (patient_id, EPOC_id) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, medCardNumber);
            prep.setInt(2, EPOCId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void introduceAsthma(int medCardNumber, int asthmaId) {
        try {
            String sql = "INSERT INTO asthma_patient (patient_id, asthma_id) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, medCardNumber);
            prep.setInt(2, asthmaId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Selects a patient by using the patients's medical card number.
     *
     * @param id - [Integer] Medical cavd number of the patient.
     * @return - [Patient] The patient to whom the inserted medical card number
     * corresponds.
     */
    @Override
    public Patient selectPatient(int id) {
        try {
            String sql = "SELECT * FROM patient WHERE medical_card_number = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            Patient patient = null;
            if (rs.next()) {
                patient = new Patient(rs.getInt("patientid"), rs.getInt("medical_card_number"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("gender"), rs.getBoolean("pregnancy"), rs.getBoolean("influenzaV"),
                        rs.getBoolean("pneumoniaV"), rs.getInt("treatment_stage"), rs.getBoolean("smoker"), rs.getBoolean("symptoms_controlled"),
                        rs.getBoolean("hospitalization"), rs.getString("respiratorydisease"));
            }
            p.close();
            rs.close();
            return patient;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Select every patient related to the doctor
     *
     * @param userid
     * @return - [ArrayList] List of all the patients.
     */
    @Override
    public ArrayList<Patient> selectAllPatients(int userid) {
        try {
            String sql = "SELECT * FROM patient where userId = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, userid);
            ResultSet rs = p.executeQuery();
            ArrayList<Patient> pList = new ArrayList<Patient>();
            while (rs.next()) {
                pList.add(new Patient(rs.getInt("patientid"), rs.getInt("medical_card_number"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("gender"), rs.getBoolean("pregnancy"), rs.getBoolean("influenzaV"),
                        rs.getBoolean("pneumoniaV"), rs.getInt("treatment_stage"), rs.getBoolean("smoker"), rs.getBoolean("symptoms_controlled"),
                        rs.getBoolean("hospitalization"), rs.getString("respiratorydisease")));
                System.out.println(rs.getString("name"));
            }
            p.close();
            rs.close();
            return pList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Patient> ListPatients(String name) {
        ArrayList<Patient> pList = new ArrayList<Patient>();
        try {
            String sql = "SELECT * FROM patient WHERE name = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, name);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                pList.add(new Patient(rs.getInt("patientid"), rs.getInt("medical_card_number"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("gender"), rs.getBoolean("pregnancy"), rs.getBoolean("influenzaV"),
                        rs.getBoolean("pneumoniaV"), rs.getInt("treatment_stage"), rs.getBoolean("smoker"), rs.getBoolean("symptoms_controlled"),
                        rs.getBoolean("hospitalization"), rs.getString("respiratorydisease")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pList;
    }

    @Override
    public boolean editPatient(int id, Boolean influenzaV,Boolean hospitalization, Boolean pneumoniaV, Integer treatment_stage) {
        String sql;
        PreparedStatement pStatement;
        try {
            if (influenzaV != null) {
                sql = "UPDATE patient SET influenzaV = ? WHERE patientid = ?";
                pStatement = c.prepareStatement(sql);
                pStatement.setBoolean(1, influenzaV);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();
            }
            
            if (hospitalization != null) {
                sql = "UPDATE patient SET hospitalization = ? WHERE patientid = ?";
                pStatement = c.prepareStatement(sql);
                pStatement.setBoolean(1, hospitalization);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();
            }
            if (pneumoniaV != null) {
                sql = "UPDATE patient SET pneumoniaV = ? WHERE patientid = ?";
                pStatement = c.prepareStatement(sql);
                pStatement.setBoolean(1, pneumoniaV);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();
            }
            if (treatment_stage != null) {
                sql = "UPDATE patient SET treatment_stage = ? WHERE patientid = ?";
                pStatement = c.prepareStatement(sql);
                pStatement.setInt(1, treatment_stage);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();
            }
            return true;
        } catch (SQLException update_patient_error) {
            update_patient_error.printStackTrace();
            return false;
        }
    }

    /**
     * Associates a patient with a user of the application
     *
     * @param userId
     * @param medCardNumber
     */
    @Override
    public void createLinkUserPatient(int userId, int medCardNumber) {
        try {
            String sql1 = "UPDATE patient SET userId = ? WHERE patientid = ? ";
            PreparedStatement pStatement = c.prepareStatement(sql1);
            pStatement.setInt(1, userId);
            pStatement.setInt(2, medCardNumber);
            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList patient_names(int user_id) {
        try {
            ArrayList<String> names = new ArrayList<>();
            String sql = "SELECT name FROM patient WHERE id_doctor = ?";
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, user_id);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                names.add(rs.getString("name"));
            }

            pStatement.close();
            rs.close();
            return names;
        } catch (SQLException ex) {
            Logger.getLogger(SQLitePatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
