/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db.interfaces;

import java.sql.SQLException;
import pojos.Doctor;

/**
 *
 * @author gisel
 */
public interface DoctorManager {
    public void addDoctor(Doctor d) throws SQLException;
    public void createLinkUserDoctor(Integer userId, Integer doctorId);
}
