/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Allergy;

/**
 *
 * @author gisel
 */
public interface AllergyManager {
    public void addAllergy(Allergy a)throws SQLException;
    public Allergy selectAllergy(Integer aId);
    public ArrayList<Allergy> getAllergiesFromPatient(Integer patientId); 
}