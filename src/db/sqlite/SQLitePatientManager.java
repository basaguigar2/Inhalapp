/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.PatientManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Patient;

/**
 *
 * @author gisel
 */
public class SQLitePatientManager implements PatientManager{
    private Connection c;

    public SQLitePatientManager(Connection c) {
        this.c = c;
    }

    public SQLitePatientManager() {
        super();
    }
   
    /**
     * Creates and adds a new patient into the database
     * @param p - [Patient] Patient that is added to the database
     * @throws SQLException
     */
    @Override
    public void addPatient(Patient p) throws SQLException {
        try{
            String sq1 = "INSERT INTO patient (medical_card_number, name, surname, age, gender, pregnancy) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setInt(1, p.getMedical_card_number());
            preparedStatement.setString(2, p.getPatientName());
            preparedStatement.setString(3, p.getPatientSurname());
            preparedStatement.setInt(4, p.getPatientAge());
            preparedStatement.setString(5, p.getPatientGender());
            preparedStatement.setBoolean(6, p.isPregnant());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void introduceComorbidity(int medCardNumber, int comorbidityId) {
        try {
            String sql = "INSERT INTO comorbidity_patient (patient_id, comorbidity_id) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, medCardNumber);
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
    public void introduceAllergy(int medCardNumber, int allergyId) {
        try {
            String sql = "INSERT INTO allergy_patient (patient_id, allergy_id) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, medCardNumber);
            prep.setInt(2, allergyId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void introduceDisease(int medCardNumber, int diseaseId) {
        try {
            String sql = "INSERT INTO disease_patient (patient_id, disease_id) " + "VALUES (?,?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, medCardNumber);
            prep.setInt(2, diseaseId);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Selects a patient by using the patients's medical card number.
     * @param medCardNumber - [Integer] Medical cavd number of the patient.
     * @return - [Patient] The patient to whom the inserted medical card number corresponds.
     */
    @Override
    public Patient selectPatient(int medCardNumber) {
        try {
            String sql = "SELECT * FROM patient WHERE medical_card_number = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,medCardNumber);
            ResultSet rs = p.executeQuery();
            Patient patient = null;
            if(rs.next()){
                patient = new Patient(rs.getInt("medical_card_number"),rs.getString("name"),rs.getString("surname"),rs.getInt("age"),
                        rs.getString("gender"),rs.getBoolean("pregnancy"));
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
     * @return - [ArrayList] List of all the patients.    
     */
    @Override
    public ArrayList<Patient> selectAllPatients() {
        try {
            String sql = "SELECT * FROM patient";
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            ArrayList <Patient> pList = new ArrayList<Patient>();

            while(rs.next()){ 
                pList.add(new Patient(rs.getInt("medical_card_number"),rs.getString("name"),rs.getString("surname"),rs.getInt("age"),rs.getString("gender"),rs.getBoolean("pregnancy")));
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
    public boolean editPatient(Integer medCardNum, String name, String surname, Integer age, String gender, boolean pregnancy) {
        String sql;
        PreparedStatement pStatement;
         try {
		if (name != null) {
			sql = "UPDATE patient SET name = ? WHERE medical_card_number = ?";
			pStatement = c.prepareStatement(sql);
			pStatement.setString(1, name);
			pStatement.setInt(2, medCardNum);
			pStatement.executeUpdate();	
		} 
		if (surname != null) {
			sql = "UPDATE patient SET surname = ? WHERE medical_card_number = ?";
			pStatement = c.prepareStatement(sql);
			pStatement.setString(1, surname);
			pStatement.setInt(2, medCardNum);
			pStatement.executeUpdate();
		}
                
                if (age != null) {
			sql = "UPDATE patient SET age = ? WHERE medical_card_number = ?";
			pStatement = c.prepareStatement(sql);
			pStatement.setInt(1,age);
			pStatement.setInt(2, medCardNum);
			pStatement.executeUpdate();
		}
                
                if (gender != null) {
			sql = "UPDATE patient SET gender = ? WHERE medical_card_number = ?";
			pStatement = c.prepareStatement(sql);
			pStatement.setString(1, gender);
			pStatement.setInt(2, medCardNum);
			pStatement.executeUpdate();	
		}
                
                if (String.valueOf(pregnancy) != null) {
			sql = "UPDATE patient SET pregnancy = ? WHERE medical_card_number = ?";
			pStatement = c.prepareStatement(sql);
			pStatement.setBoolean(1, pregnancy);
			pStatement.setInt(2, medCardNum);
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
     * @param userId
     * @param medCardNumber
     */
    @Override
    public void createLinkUserPatient(int userId, int medCardNumber) {
        try {
            String sql1 = "UPDATE patient SET userId = ? WHERE medical_card_number = ? ";
            PreparedStatement pStatement = c.prepareStatement(sql1);
            pStatement.setInt(1, userId);
            pStatement.setInt(2, medCardNumber);
            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Associates a doctor with a patient
     * @param medCardNumber
     * @param doctorId
     */
    @Override
    public void createLinkDoctorPatient(int medCardNumber, int doctorId) {
        try {
            String sql = "INSERT INTO doctor_patient (patient_id, doctor_id) VALUES (?,?)";
            PreparedStatement pStatement = c.prepareStatement(sql);
            pStatement.setInt(1, medCardNumber);
            pStatement.setInt(2, doctorId);
            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}