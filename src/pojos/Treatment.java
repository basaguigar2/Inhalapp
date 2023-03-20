/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

/**
 *
 * @author gisel
 */
public class Treatment {
    private Integer tid;
    private String drug;
    private String dose;//in miligrams
    private String therapy;

    public Treatment() {
    }

    public Treatment(Integer tid, String drug, String dose, String therapy) {
        this.tid = tid;
        this.drug = drug;
        this.dose = dose;
        this.therapy = therapy;
    }

    public Treatment(Integer tid, String drug, String dose) {
        this.tid = tid;
        this.drug = drug;
        this.dose = dose;
    }

    public Treatment(String drug, String dose) {
        this.drug = drug;
        this.dose = dose;
    }

    public Treatment(String therapy) {
        this.therapy = therapy;
    }

    public Integer getTreatmentId() {
        return tid;
    }

    public void setTreatmentId(Integer tid) {
        this.tid = tid;
    }

    public String getDrug() {
        if (drug == null){
            drug = "None";
        }
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getDose() {
        if (dose == null){
            dose = "None";
        }
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTherapy() {
        if (therapy == null){
            therapy = "None";
        }
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }
    
    @Override
    public String toString() {
        return "Treatment{" + "drug=" + drug + ", dose=" + dose + '}';
    }
}