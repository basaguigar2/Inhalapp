/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.sqlite;

import db.interfaces.DoctorManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojos.Doctor;
/**
 *
 * @author gisel
 */
public class SQLiteDoctorManager implements DoctorManager{
    
    private Connection c;

    public SQLiteDoctorManager(Connection c) {
        this.c = c;
    }

    public SQLiteDoctorManager() {
        super();
    }
    
    /**
     * Creates and adds a new doctor into the database
     * @param d - [Doctor] Doctor that is added to the database
     * @throws SQLException
     */
    @Override
    public void addDoctor(Doctor d) throws SQLException  {
        try{
            String sq1 = "INSERT INTO doctor (dname, dsurname) VALUES (?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, d.getDoctorName());
            preparedStatement.setString(2, d.getDoctorSurname());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Associates a doctor with a user of the application
     * @param userId
     * @param doctorId
     
     */
    @Override
    public void createLinkUserDoctor(Integer userId, Integer doctorId) {
        try {
            String sql1 = "UPDATE doctor SET userId = ? WHERE doctorId = ? ";
            PreparedStatement pStatement = c.prepareStatement(sql1);
            pStatement.setInt(1, userId);
            pStatement.setInt(2, doctorId);
            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
