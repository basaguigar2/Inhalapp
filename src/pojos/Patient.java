/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

import java.rmi.NotBoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gisel
 */
public class Patient {
   public Integer id;
   public Integer medical_card_number;
   public String name;
   public int age;
   public LocalDate calendar;
   public String gender;
   public boolean pregnancy;
   public boolean influenza_vaccine, pneumonia_vaccine;
   public Integer treat_stage;
   public boolean smoker;
   public boolean symptoms_controlled;
   public boolean hospitalization;
   public String respiratorydisease;
   public Integer userId;
   
   public List<Treatment> treatment_list = new ArrayList<>();
   public List<Comorbidity> comorbidity = new ArrayList<>();
   public EPOC epoc;
   public Asthma asthma;
   
   public List<String> string_comorbidities = new ArrayList<>();
   public List<String> string_treatments = new ArrayList<>();

    public Patient() {
    }

    public Patient(Integer medical_card_number, String name, int age, String gender, boolean pregnancy, boolean smoker, boolean hospitalization, String respiratorydisease) {
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.smoker = smoker;
        this.hospitalization = hospitalization;
        this.respiratorydisease = respiratorydisease;
    }

    public Patient(Integer id, Integer medical_card_number, String name, int age, String gender, boolean pregnancy, boolean influenza_vaccine, boolean pneumonia_vaccine, boolean smoker, boolean symptoms_controlled, boolean hospitalization, String respiratorydisease, Integer userId, EPOC epoc, Asthma asthma) {
        this.id = id;
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.influenza_vaccine = influenza_vaccine;
        this.pneumonia_vaccine = pneumonia_vaccine;
        this.smoker = smoker;
        this.symptoms_controlled = symptoms_controlled;
        this.hospitalization = hospitalization;
        this.respiratorydisease = respiratorydisease;
        this.userId = userId;
        this.epoc = epoc;
        this.asthma = asthma;
    }

   

    public Patient(Integer medical_card_number, String name, int age, String gender, boolean pregnancy, boolean influenza_vaccine, boolean pneumonia_vaccine, boolean smoker, boolean symptoms_controlled, boolean hospitalization, String respiratorydisease, EPOC epoc, Asthma asthma) {
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.influenza_vaccine = influenza_vaccine;
        this.pneumonia_vaccine = pneumonia_vaccine;
        this.smoker = smoker;
        this.symptoms_controlled = symptoms_controlled;
        this.hospitalization = hospitalization;
        this.respiratorydisease = respiratorydisease;
        this.epoc = epoc;
        this.asthma = asthma;
    }

    public Patient(Integer id, Integer medical_card_number, String name, int age, String gender, boolean pregnancy, boolean smoker, boolean symptoms_controlled, boolean hospitalization, String respiratorydisease) {
        this.id = id;
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.smoker = smoker;
        this.symptoms_controlled = symptoms_controlled;
        this.hospitalization = hospitalization;
        this.respiratorydisease = respiratorydisease;
    }

    public Patient(Integer id, Integer medical_card_number, String name, int age, String gender, boolean pregnancy, boolean influenza_vaccine, boolean pneumonia_vaccine, Integer treat_stage, boolean smoker, boolean symptoms_controlled, boolean hospitalization, String respiratorydisease) {
        this.id = id;
        this.medical_card_number = medical_card_number;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pregnancy = pregnancy;
        this.influenza_vaccine = influenza_vaccine;
        this.pneumonia_vaccine = pneumonia_vaccine;
        this.treat_stage = treat_stage;
        this.smoker = smoker;
        this.symptoms_controlled = symptoms_controlled;
        this.hospitalization = hospitalization;
        this.respiratorydisease = respiratorydisease;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getPatientAge() {
        return age;
    }

    public LocalDate getCalendar() {
        return calendar;
    }

    public void setCalendar(LocalDate calendar) {
        this.calendar = calendar;
    }

    public void setPatientAge(int age) {
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

    public void setTreatment_List(List<Treatment> treatment_list) {
        this.treatment_list = treatment_list;
        this.setString_treatments(treatment_list);
    }
    
    public void addTreatment(Treatment treatment){
        if(this.treatment_list.contains(treatment)==false && this.string_treatments.contains(treatment.getDrug())==false){
            this.treatment_list.add(treatment);
            this.string_treatments.add(treatment.getDrug());
        }
    }

    public List<String> getString_treatments() {
        return string_treatments;
    }

    public void setString_treatments(List<Treatment> string_treatments) {
        for (int i = 0; i < this.treatment_list.size(); i++) {
                String t = this.treatment_list.get(i).getDrug();
                this.string_treatments.add(t);
        }
    }

    public Integer getTreatment_stage() {
        return treat_stage;
    }

    public void setTreatment_stage(int treatment_stage) throws NotBoundException {
        if(treatment_stage==0){
            this.treat_stage = 0;
        } else if(treatment_stage==1){
            this.treat_stage = 1;
        } else if(treatment_stage==2){
            this.treat_stage = 2;
        } else if(treatment_stage==3){
            this.treat_stage = 3;
        } else if(treatment_stage==4){
            this.treat_stage = 4;
        } else if(treatment_stage==5){
            this.treat_stage = 5;
        } else if(treatment_stage==6){
            this.treat_stage = 6;
        } else{
            throw new NotBoundException("Not valid stage. Introduce a valid stage [1-6]");
        }
    }
    
    public List<Comorbidity> getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(List<Comorbidity> comorbidity) {
        this.comorbidity = comorbidity;
        this.setString_comorbidities(comorbidity);
    }
    
    public void addComorbidity(Comorbidity comorbidity){
        if(this.comorbidity.contains(comorbidity)==false && this.string_comorbidities.contains(comorbidity.getComorbidityName())==false){
            this.comorbidity.add(comorbidity);
            this.string_comorbidities.add(comorbidity.getComorbidityName());
        }
    }
    public List<String> getString_comorbidities() {
        return string_comorbidities;
    }

    public void setString_comorbidities(List<Comorbidity> string_comorbidities) {
        for (int i = 0; i < this.comorbidity.size(); i++) {
                String c = this.comorbidity.get(i).getComorbidityName();
                this.string_comorbidities.add(c);
        }
    }
    
    

    @Override
    public String toString() {
        return "Patient{" + "medical_card_number=" + medical_card_number + ", name=" + name + '}';
    }

}