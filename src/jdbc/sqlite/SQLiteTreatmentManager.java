/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.sqlite;

import jdbc.interfaces.TreatmentManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Treatment;

/**
 *
 * @author gisel
 */
public class SQLiteTreatmentManager implements TreatmentManager {
    
    private Connection c;

    public SQLiteTreatmentManager(Connection c) {
        this.c = c;
    }

    public SQLiteTreatmentManager() {
        super();
    }
    
    /**
     * Creates and adds a new treatment into the database
     * @param t - [Treatment] Treatment that is added to the database
     * @throws SQLException
     */
    @Override
    public void addTreatment(Treatment t) throws SQLException {
        try{
            String sq1 = "INSERT INTO treatment (drug, dose, therapy) VALUES (?,?,?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, t.getDrug());
            preparedStatement.setString(2, t.getDose());
            preparedStatement.setString(3, t.getTherapy());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Selects a treatment by using the treatment's id.
     * @param tId - [Integer] Id associated with the treatment.
     * @return - [Treatment] The treatment to whom the inserted id corresponds.
     */
    @Override
    public Treatment selectTreatment(Integer tId) {
        try {
            String sql = "SELECT * FROM treatment WHERE tid = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,tId);
            ResultSet rs = p.executeQuery();
            Treatment treatment = null;
            if(rs.next()){
                treatment = new Treatment(rs.getInt("tid"),rs.getString("drug"),rs.getString("dose"),rs.getString("therapy"));
            }
            p.close();
            rs.close();
            return treatment;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Select every treatment related to the patient
     * @param patientId
     * @return - [List] List of all the treatments.
     */
    @Override
    public ArrayList<Treatment> getTreatmentFromPatient(Integer patientId) {
        ArrayList<Treatment> tList = new ArrayList<Treatment>();
        try {
            String sql = "SELECT * FROM treatment_patient WHERE patient_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                tList.add(selectTreatment(rs.getInt("treatment_id")));
            }
            p.close();
            rs.close();
            return tList;
        } catch (SQLException e) {
            return tList;
        }
    }
}
