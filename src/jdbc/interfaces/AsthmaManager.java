/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.interfaces;

import java.sql.SQLException;
import pojos.Asthma;

/**
 *
 * @author gisel
 */
public interface AsthmaManager {
    public void addAsthma(Asthma a) throws SQLException;
    public Asthma selectAsthma(Integer asthma_id);
    public Asthma getAsthmaFromPatient(Integer patientId);
    public int getLastId();
}