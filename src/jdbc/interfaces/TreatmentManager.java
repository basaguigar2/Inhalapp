/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Treatment;

/**
 *
 * @author gisel
 */
public interface TreatmentManager {
    public void addTreatment(Treatment t)throws SQLException;
    public Treatment selectTreatment(Integer tId);
    public ArrayList<Treatment> getTreatmentFromPatient(Integer patientId);
}
