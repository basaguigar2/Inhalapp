/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Comorbidity;

/**
 *
 * @author gisel
 */
public interface ComorbidityManager {
    public void addComorbidity(Comorbidity c)throws SQLException;
    public Comorbidity selectComorbidity(Integer cId);
    public ArrayList<Comorbidity> getComorbiditiesFromPatient(Integer patientId); 
}
