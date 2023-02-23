/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.EPOC;
import db.interfaces.EpocManager;

/**
 *
 * @author gisel
 */
public class SQLiteEpocManager implements EpocManager{

    private Connection c;
    
    public SQLiteEpocManager(Connection c){
        this.c = c;
    }
      public SQLiteEpocManager() {
        super();
    }

    @Override
    public void addEPOC(EPOC e) throws SQLException {
        try{
            String sq1 = "INSERT INTO EPOC (condition, mMRC, EOS, exacerbations, FEV) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, e.getCondition_string());
            preparedStatement.setInt(2, e.getmMRC());
            preparedStatement.setInt(3, e.getEOS());
            preparedStatement.setBoolean(4, e.isExacerbations());
            preparedStatement.setInt(5, e.getFEV());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public EPOC selectEPOC(Integer EPOC_id) {
        try {
            String sql = "SELECT * FROM EPOC WHERE EPOC_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,EPOC_id);
            ResultSet rs = p.executeQuery();
            EPOC epoc = null;
            if(rs.next()){
                epoc = new EPOC(rs.getString("condition"),rs.getInt("mMRC"),rs.getInt("EOS"),rs.getBoolean("exacerbations"),
                        rs.getInt("FEV"),rs.getInt("EPOC_id"));
            }
            p.close();
            rs.close();
            return epoc;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public EPOC getEPOCFromPatient(Integer patientId) {
        try {
            String sql = "SELECT * FROM EPOC_patient WHERE patient_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            EPOC e  = selectEPOC(rs.getInt("EPOC_id"));
            p.close();
            rs.close();
            return e;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}