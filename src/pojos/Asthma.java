/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

/**
 *
 * @author gisel
 */
public class Asthma {
    public AsthmaStage stage;
    public int PS, PEF,SAT_O2;
    public int dayTimeSymptoms_w, rescueMedication_w, nocturnalSymptoms_w, limitations, pulmonar_function, exarcebations_y;
    
    public Asthma (AsthmaStage stage, int PS, int PEF, int SAT_O2, int dayTimeSymptoms_w, int rescueMedication_w, int nocturnalSymptoms_w, int limitations, int pulmonar_function, int exarcebations_y) {
        this.stage=stage;
        this.PS=PS;
        this.PEF=PEF;
        this.SAT_O2=SAT_O2;
        this.dayTimeSymptoms_w=dayTimeSymptoms_w;
        this.rescueMedication_w=rescueMedication_w;
        this.nocturnalSymptoms_w=nocturnalSymptoms_w;
        this.limitations=limitations;
        this.pulmonar_function=pulmonar_function;
        this.exarcebations_y=exarcebations_y;
    }
    
    public Asthma(){}
    
    public AsthmaStage getStage() {
        return stage;
    }

    public void setStage(AsthmaStage stage) {
        this.stage = stage;
    }
    
    public int getPS() {
        return PS;
    }

    public void PS(int PS) {
        this.PS = PS;
    }
    
     public int getPEF() {
        return PEF;
    }

    public void setPEF(int PEF) {
        this.PEF = PEF;
    }
    
     public int getSAT_O2() {
        return SAT_O2;
    }

    public void setSAT_O2(int SAT_O2) {
        this.SAT_O2 = SAT_O2;
    }
    
     public int getnocturnalSymptoms_w() {
        return nocturnalSymptoms_w;
    }

    public void setnocturnalSymptoms_w(int nocturnalSymptoms_w) {
        this.nocturnalSymptoms_w = nocturnalSymptoms_w;
    }
    
    public int getrescueMedication_w() {
        return rescueMedication_w;
    }

    public void setrescueMedication_w(int rescueMedication_w) {
        this.rescueMedication_w = rescueMedication_w;
    }
    
    public int getlimitations() {
        return limitations;
    }

    public void setlimitations(int limitations) {
        this.limitations = limitations;
    }
    
     public int getpulmonar_function() {
        return pulmonar_function;
    }

    public void setpulmonar_function(int pulmonar_function) {
        this.pulmonar_function = pulmonar_function;
    }
    
      public int getexarcebations_y() {
        return exarcebations_y;
    }

    public void setexarcebations_y(int exarcebations_y) {
        this.exarcebations_y = exarcebations_y;
    }
}