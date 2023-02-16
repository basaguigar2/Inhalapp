/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pojos;

import java.rmi.NotBoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gisel
 */
public class Doctor {
    private Integer doctorId;
    private String dname;
    private String dsurname;
    private Integer userId;

    public Doctor() {
    }

    public Doctor(Integer doctorId, String dname, String dsurname, Integer userId) {
        this.doctorId = doctorId;
        this.dname = dname;
        this.dsurname = dsurname;
        this.userId = userId;
    }

    public Doctor(Integer doctorId, String dname, String dsurname) {
        this.doctorId = doctorId;
        this.dname = dname;
        this.dsurname = dsurname;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return dname;
    }

    public void setDoctorName(String dname) {
        this.dname = dname;
    }

    public String getDoctorSurname() {
        return dsurname;
    }

    public void setDoctorSurname(String dsurname) {
        this.dsurname = dsurname;
    }

    public Integer getDoctorUserId() {
        return userId;
    }

    public void setDoctorUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Doctor{" + "doctorId=" + doctorId + ", dname=" + dname + ", dsurname=" + dsurname + '}';
    }
    
}
