/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.AllergyManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Allergy;

/**
 *
 * @author gisel
 */
public class SQLiteAllergyManager implements AllergyManager{

    private Connection c;
    
    public SQLiteAllergyManager(Connection c){
        this.c = c;
    }
      public SQLiteAllergyManager() {
        super();
    }

    /**
     * Creates and adds a new allergy into the database
     * @param a - [Allergy] Allergy that is added to the database
     * @throws SQLException
     */
    @Override
    public void addAllergy(Allergy a) throws SQLException {
        try{
            String sq1 = "INSERT INTO allergy (aname, type) VALUES (?,?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, a.getAllergyName());
            preparedStatement.setString(2, a.getAllergyType());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Selects an allergy by using the allergy's id.
     * @param aId - [Integer] Id associated with the allergy.
     * @return - [Allergy] The allergy to whom the inserted id corresponds.
     */
    @Override
    public Allergy selectAllergy(Integer aId) {
        try {
            String sql = "SELECT * FROM allergy WHERE aid = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,aId);
            ResultSet rs = p.executeQuery();
            Allergy allergy = null;
            if(rs.next()){
                allergy = new Allergy(rs.getInt("aid"),rs.getString("aname"),rs.getString("type"));
            }
            p.close();
            rs.close();
            return allergy;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Select every allergy related to the patient
     * @param patientId
     * @return - [List] List of all the allergies.
     */
    @Override
    public ArrayList<Allergy> getAllergiesFromPatient(Integer patientId) {
        try {
            String sql = "SELECT * FROM allergy_patient WHERE patient_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            ArrayList<Allergy> aList = new ArrayList<Allergy>();
            
            while (rs.next()) {
                aList.add(selectAllergy(rs.getInt("allergy_id")));
            }
            p.close();
            rs.close();
            return aList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
