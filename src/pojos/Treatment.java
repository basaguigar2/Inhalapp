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
    private Integer dose; //in miligrams
    private Integer duration; //in weeks

    public Treatment() {
    }

    public Treatment(Integer tid, String drug, Integer dose, Integer duration) {
        this.tid = tid;
        this.drug = drug;
        this.dose = dose;
        this.duration = duration;
    }

    public Treatment(String drug, Integer dose, Integer duration) {
        this.drug = drug;
        this.dose = dose;
        this.duration = duration;
    }

    public Integer getTreatmentId() {
        return tid;
    }

    public void setTreatmentId(Integer tid) {
        this.tid = tid;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return "Treatment{" + "drug=" + drug + ", dose=" + dose + ", duration=" + duration + '}';
    }
}