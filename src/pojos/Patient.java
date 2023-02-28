/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;

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
   private boolean influenza_vaccine, pneumonia_vaccine;
   private Integer treatment_stage = 0;
   private boolean smoker;
   private boolean symptoms_controlled;
   private boolean hospitalization;
   private String respiratorydisease;
   
   private Integer userId;
   
   private List<Treatment> treatment_list = new ArrayList<>();
   private List<Comorbidity> comorbidity = new ArrayList<>();
   private EPOC epoc;
   private Asthma asthma;
   
   private List<String> string_comorbidities = new ArrayList<>();
   private List<String> string_treatments = new ArrayList<>();

    public Patient() {
    }

    public Patient(Integer medical_card_number, String name, String surname, Integer age, String gender, boolean pregnancy, boolean smoker, boolean symptoms_controlled, boolean hospitalization, String respiratorydisease, Integer treatment_stage, Integer userId) {
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.smoker = smoker;
        this.symptoms_controlled = symptoms_controlled;
        this.hospitalization = hospitalization;
        this.respiratorydisease = respiratorydisease;
        this.treatment_stage = treatment_stage;
        this.userId = userId;
    }

    public Patient(Integer medical_card_number, String name, String surname, Integer age, String gender, boolean pregnancy, boolean smoker, boolean symptoms_controlled, boolean hospitalization, String respiratorydisease, Integer treatment_stage) {
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.symptoms_controlled = symptoms_controlled;
        this.hospitalization = hospitalization;
        this.respiratorydisease = respiratorydisease;
        this.treatment_stage = treatment_stage;
        this.smoker = smoker;
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

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public String getRespiratorydisease() {
        return respiratorydisease;
    }

    public void setRespiratorydisease(String respiratorydisease) throws NotBoundException {
        if(respiratorydisease.equalsIgnoreCase("asthma")){
            this.respiratorydisease = "Asthma";
        } else if(respiratorydisease.equalsIgnoreCase("EPOC")){
            this.respiratorydisease = "EPOC";
        } else{
            throw new NotBoundException("Not valid disease. Introduce a valid disease: asthma or EPOC");
        }
    }

    public EPOC getEpoc() {
        return epoc;
    }

    public void setEpoc(EPOC epoc) {
        this.epoc = epoc;
    }

    public Asthma getAsthma() {
        return asthma;
    }

    public void setAsthma(Asthma asthma) {
        this.asthma = asthma;
    }

    public boolean isSymptoms_controlled() {
        return symptoms_controlled;
    }

    public void setSymptoms_controlled(boolean symptoms_controlled) {
        this.symptoms_controlled = symptoms_controlled;
    }

    public boolean isHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(boolean hospitalization) {
        this.hospitalization = hospitalization;
    }

    public boolean isInfluenza_vaccine() {
        return influenza_vaccine;
    }

    public void setInfluenza_vaccine(boolean influenza_vaccine) {
        this.influenza_vaccine = influenza_vaccine;
    }

    public boolean isPneumonia_vaccine() {
        return pneumonia_vaccine;
    }

    public void setPneumonia_vaccine(boolean pneumonia_vaccine) {
        this.pneumonia_vaccine = pneumonia_vaccine;
    }

    public Integer getPatientUserId() {
        return userId;
    }

    public void setPatientUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Treatment> getTreatment_List() {
        return treatment_list;
    }

    public void setTreatment_List(ArrayList<Treatment> treatment_list) {
        this.treatment_list = treatment_list;
    }
    
    public void addTreatment(Treatment treatment){
        this.treatment_list.add(treatment);
    }

    public List<String> getString_treatments() {
        return string_treatments;
    }

    public void setString_treatments(ArrayList<String> string_treatments) {
        for (int i = 0; i < this.treatment_list.size(); i++) {
                String t = this.treatment_list.get(i).getDrug();
                string_treatments.add(t);
        }
        this.string_treatments = string_treatments;
    }

    public Integer getTreatment_stage() {
        return treatment_stage;
    }

    public void setTreatment_stage(int treatment_stage) throws NotBoundException {
        if(treatment_stage==0){
            this.treatment_stage = 0;
        } else if(treatment_stage==1){
            this.treatment_stage = 1;
        } else if(treatment_stage==2){
            this.treatment_stage = 2;
        } else if(treatment_stage==3){
            this.treatment_stage = 3;
        } else if(treatment_stage==4){
            this.treatment_stage = 4;
        } else if(treatment_stage==5){
            this.treatment_stage = 5;
        } else if(treatment_stage==6){
            this.treatment_stage = 6;
        } else{
            throw new NotBoundException("Not valid stage. Introduce a valid stage [1-6]");
        }
    }
    
    public List<Comorbidity> getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(ArrayList<Comorbidity> comorbidity) {
        this.comorbidity = comorbidity;
    }

    public List<String> getString_comorbidities() {
        return string_comorbidities;
    }

    public void setString_comorbidities(ArrayList<String> string_comorbidities) {
        for (int i = 0; i < this.comorbidity.size(); i++) {
                String c = this.comorbidity.get(i).getComorbidityName();
                string_comorbidities.add(c);
        }
        this.string_comorbidities = string_comorbidities;
    }

    @Override
    public String toString() {
        return "Patient{" + "medical_card_number=" + medical_card_number + ", name=" + name + ", surname=" + surname + '}';
    }

}