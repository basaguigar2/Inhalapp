/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author gisel
 */
public class Patient {
   private Integer medical_card_number;
   private String name;
   private String surname;
   private Integer age;
   private String gender;
   private boolean pregnancy;
   
   private Integer userId;
   
   private ArrayList<Treatment> treatment = new ArrayList<>();
   private ArrayList<Comorbidity> comorbidity = new ArrayList<>();
   private ArrayList<Allergy> allergies = new ArrayList<>();
   private Disease disease = new Disease();

    public Patient() {
    }

    public Patient(Integer medical_card_number, String name, String surname, Integer age, String gender, boolean pregnancy, Integer userId) {
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.userId = userId;
    }

    public Patient(Integer medical_card_number, String name, String surname, Integer age, String gender, boolean pregnancy) {
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
    }

    public Integer getMedical_card_number() {
        return medical_card_number;
    }

    public void setMedical_card_number(Integer medical_card_number) {
        this.medical_card_number = medical_card_number;
    }

    public String getPatientName() {
        return name;
    }

    public void setPatientName(String name) {
        this.name = name;
    }

    public String getPatientSurname() {
        return surname;
    }

    public void setPatientSurname(String surname) {
        this.surname = surname;
    }

    public Integer getPatientAge() {
        return age;
    }

    public void setPatientAge(Integer age) {
        this.age = age;
    }

    public String getPatientGender() {
        return gender;
    }

    public void setPatientGender(String gender) throws NotBoundException {
        if (gender.equalsIgnoreCase("male")){
            this.gender = "Male";
        } else if (gender.equalsIgnoreCase("female")){
            this.gender = "Female";
        } else {
            throw new NotBoundException("Not valid gender. Introduce gender: male or female");
        }
    }

    public boolean isPregnant() {
        return pregnancy;
    }

    public void setPregnancy(boolean pregnancy) {
        this.pregnancy = pregnancy;
    }

    public Integer getPatientUserId() {
        return userId;
    }

    public void setPatientUserId(Integer userId) {
        this.userId = userId;
    }

    public ArrayList<Treatment> getTreatment() {
        return treatment;
    }

    public void setTreatment(ArrayList<Treatment> treatment) {
        this.treatment = treatment;
    }

    public ArrayList<Comorbidity> getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(ArrayList<Comorbidity> comorbidity) {
        this.comorbidity = comorbidity;
    }

    public ArrayList<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<Allergy> allergies) {
        this.allergies = allergies;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "Patient{" + "medical_card_number=" + medical_card_number + ", name=" + name + ", surname=" + surname + ", treatment=" + treatment + ", comorbidity=" + comorbidity + ", disease=" + disease + '}';
    }
  
}