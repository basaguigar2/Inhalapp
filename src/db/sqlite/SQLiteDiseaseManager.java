/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.DiseaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.Disease;

/**
 *
 * @author gisel
 */
public class SQLiteDiseaseManager implements DiseaseManager{

    private Connection c;
    
    public SQLiteDiseaseManager(Connection c){
        this.c = c;
    }
      public SQLiteDiseaseManager() {
        super();
    }
      
    /**
    * Creates and adds a new disease into the database
    * @param d - [Disease] Disease that is added to the database
    * @throws SQLException
    */
    @Override
    public void addDisease(Disease d) throws SQLException {
        try{
            String sq1 = "INSERT INTO disease (disease_name, stage) VALUES (?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, d.getDiseaseName());
            preparedStatement.setString(2, d.getStage());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Select a disease by using its id
     * @param disease_id - [Integer] Id of the disease we are looking for.
     * @return - [Disease] The disease to whom the inserted Id corresponds.
     */
    @Override
    public Disease selectDisease(Integer disease_id) {
        try {
            String sql = "SELECT * FROM disease WHERE disease_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,disease_id);
            ResultSet rs = p.executeQuery();
            Disease disease = null;
            if(rs.next()){
                disease = new Disease(rs.getInt("disease_id"),rs.getString("disease_name"),rs.getString("stage"));
            }
            p.close();
            rs.close();
            return disease;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}