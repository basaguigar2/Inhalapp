/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.EPOC;
import jdbc.interfaces.EpocManager;

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
            String sq1 = "INSERT INTO EPOC (condition, mMRC,CAT, exa, eosinophilia) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, e.getCondition_string());
            preparedStatement.setInt(2, e.getmMRC());
            preparedStatement.setInt(3, e.getCAT());
            preparedStatement.setInt(4, e.getExa());
            preparedStatement.setBoolean(5, e.isEosinophilia());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
            System.out.println("EPOC Introducido"); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void addEPOC2(EPOC e) {
        try{
            String sq1 = "INSERT INTO EPOC ( EOS,FEV,exa,exacerbations) VALUES ( ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setInt(1, e.getEOS());
            preparedStatement.setInt(2, e.getFEV());
            preparedStatement.setInt(3, e.getExa());
            preparedStatement.setBoolean(4, e.isExacerbations());
            
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void editEPOC(Integer id,Integer eos,Integer fev,Integer exa, Boolean exacerbations){
        String sql;
        PreparedStatement pStatement;
        try {
            if (eos != null) {
                sql = "UPDATE EPOC SET EOS = ? WHERE EPOC_id = ?";
                pStatement = c.prepareStatement(sql);
                pStatement.setInt(1, eos);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();
            }
            
            if (fev != null) {
                sql = "UPDATE EPOC SET FEV = ? WHERE EPOC_id = ?";
                pStatement = c.prepareStatement(sql);
                pStatement.setInt(1, fev);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();
            }
            if (exa != null) {
                sql = "UPDATE EPOC SET exa = ? WHERE EPOC_id = ?";
                pStatement = c.prepareStatement(sql);
                pStatement.setInt(1, exa);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();
            }
            if (exacerbations != null) {
                sql = "UPDATE EPOC SET exacerbations = ? WHERE EPOC_id = ?";
                pStatement = c.prepareStatement(sql);
                pStatement.setBoolean(1, exacerbations);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();
            }
        } catch (SQLException update_patient_error) {
            update_patient_error.printStackTrace();
        }
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

    @Override
    public EPOC selectEPOC(Integer EPOC_id) {
        try {
            String sql = "SELECT * FROM EPOC WHERE EPOC_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,EPOC_id);
            ResultSet rs = p.executeQuery();
            EPOC epoc = null;
            if(rs.next()){
                epoc = new EPOC(rs.getString("condition"),rs.getInt("mMRC"),rs.getInt("EOS"),rs.getInt("CAT"),
                        rs.getInt("exa"),rs.getBoolean("exacerbations"),rs.getBoolean("eosinophilia"),rs.getInt("FEV"),rs.getInt("EPOC_id"));
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