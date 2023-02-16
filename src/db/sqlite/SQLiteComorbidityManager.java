/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.ComorbidityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Comorbidity;

/**
 *
 * @author gisel
 */
public class SQLiteComorbidityManager implements ComorbidityManager{
    
    private Connection c;
    
    public SQLiteComorbidityManager(Connection c){
        this.c = c;
    }
      public SQLiteComorbidityManager() {
        super();
    }
      
    /**
     * Creates and adds a new comorbidity into the database
     * @param cm - [Comorbidity] Comorbidity that is added to the database
     * @throws SQLException
     */
    @Override
    public void addComorbidity(Comorbidity cm) throws SQLException {
        try{
            String sq1 = "INSERT INTO comorbidity (cname) VALUES (?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, cm.getComorbidityName());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Selects a comorbidity by using the comorbidity's id.
     * @param cId - [Integer] Id associated with the comorbidity.
     * @return - [Comorbidity] The comorbidity to whom the inserted id corresponds.
     */
    @Override
    public Comorbidity selectComorbidity(Integer cId){
        try {
            String sql = "SELECT * FROM comorbidity WHERE cid = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,cId);
            ResultSet rs = p.executeQuery();
            Comorbidity comorbidity = null;
            if(rs.next()){
                comorbidity = new Comorbidity(rs.getInt("cid"),rs.getString("cname"));
            }
            p.close();
            rs.close();
            return comorbidity;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Select every comorbidity related to the patient
     * @param patientId
     * @return - [List] List of all the comorbidities.
     */
    @Override
    public ArrayList<Comorbidity> getComorbiditiesFromPatient(Integer patientId) {
        try {
            String sql = "SELECT * FROM comorbidity_patient WHERE patient_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            ArrayList<Comorbidity> cList = new ArrayList<Comorbidity>();
            
            while (rs.next()) {
                cList.add(selectComorbidity(rs.getInt("comorbidity_id")));
            }
            p.close();
            rs.close();
            return cList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}