/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.sqlite;

import jdbc.interfaces.AsthmaManager;
import java.rmi.NotBoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Asthma;

/**
 *
 * @author gisel
 */
public class SQLiteAsthmaManager implements AsthmaManager{

    private Connection c;
    
    public SQLiteAsthmaManager(Connection c){
        this.c = c;
    }
      public SQLiteAsthmaManager() {
        super();
    }

    @Override
    public void addAsthma(Asthma a) throws SQLException {
        try{
            String sq1 = "INSERT INTO asthma (stage, PS, PEF, SAT_O2, dayTimeSymptoms, rescueMedication,  nocturnalSymptoms, limitations, pulmonar_function, exacerbations) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sq1);
            preparedStatement.setString(1, a.getStage_string());
            preparedStatement.setInt(2, a.getPS());
            preparedStatement.setInt(3, a.getPEF());
            preparedStatement.setInt(4, a.getSAT_O2());
            preparedStatement.setInt(5, a.getDayTimeSymptoms_w());
            preparedStatement.setInt(6, a.getrescueMedication_w());
            preparedStatement.setInt(7, a.getnocturnalSymptoms_w());
            preparedStatement.setInt(8, a.getlimitations());
            preparedStatement.setInt(9, a.getpulmonar_function());
            preparedStatement.setInt(10, a.getexarcebations_y());
            preparedStatement.executeUpdate();	
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            Logger.getLogger(SQLiteAsthmaManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Asthma selectAsthma(Integer asthma_id) {
        try {
            String sql = "SELECT * FROM asthma WHERE asthma_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,asthma_id);
            ResultSet rs = p.executeQuery();
            Asthma asthma = null;
            if(rs.next()){
                asthma = new Asthma(rs.getInt("asthma_id"), rs.getString("stage"),rs.getInt("PS"),rs.getInt("PEF"), rs.getInt("SAT_O2"),
                        rs.getInt("dayTimeSymptoms"),rs.getInt("rescueMedication"),rs.getInt("nocturnalSymptoms"), rs.getInt("limitations"),
                        rs.getInt("pulmonar_function"), rs.getInt("exacerbations"));
            }
            p.close();
            rs.close();
            return asthma;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Asthma getAsthmaFromPatient(Integer patientId) {
        try {
            String sql = "SELECT * FROM asthma_patient WHERE patient_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, patientId);
            ResultSet rs = p.executeQuery();
            Asthma a  = selectAsthma(rs.getInt("asthma_id"));
            p.close();
            rs.close();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
