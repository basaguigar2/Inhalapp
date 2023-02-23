/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.interfaces;

import java.sql.SQLException;
import pojos.Disease;

/**
 *
 * @author gisel
 */
public interface DiseaseManager {
    public void addDisease(Disease d) throws SQLException;
    public Disease selectDisease(Integer disease_id);
    public Disease getDiseaseFromPatient(Integer patientId);
}
