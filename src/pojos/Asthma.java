/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

import java.rmi.NotBoundException;

/**
 *
 * @author gisel
 */
public class Asthma {
    public Integer asthma_id;
    public AsthmaStage stage;
    public String stage_string;
    public int PS, PEF,SAT_O2;
    public int dayTimeSymptoms_w, rescueMedication_w, nocturnalSymptoms_w, limitations, pulmonar_function, exarcebations_y;
    
    public Asthma(){
    }
    
    public Asthma (String stage, int PS, int PEF, int SAT_O2, int dayTimeSymptoms_w, int rescueMedication_w, int nocturnalSymptoms_w, int limitations, int pulmonar_function, int exarcebations_y) {
        this.stage_string=stage;
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

    public Asthma(Integer asthma_id, String stage, int PS, int PEF, int SAT_O2, int dayTimeSymptoms_w, int rescueMedication_w, int nocturnalSymptoms_w, int limitations, int pulmonar_function, int exarcebations_y) {
        this.asthma_id = asthma_id;
        this.stage_string = stage;
        this.PS = PS;
        this.PEF = PEF;
        this.SAT_O2 = SAT_O2;
        this.dayTimeSymptoms_w = dayTimeSymptoms_w;
        this.rescueMedication_w = rescueMedication_w;
        this.nocturnalSymptoms_w = nocturnalSymptoms_w;
        this.limitations = limitations;
        this.pulmonar_function = pulmonar_function;
        this.exarcebations_y = exarcebations_y;
    }

    public Integer getAsthma_id() {
        return asthma_id;
    }

    public void setAsthma_id(Integer asthma_id) {
        this.asthma_id = asthma_id;
    }
    
    public AsthmaStage getStage() {
        return stage;
    }

    public void setStage(AsthmaStage stage) {
        this.stage = stage;
    }

    public String getStage_string()  {
        
        return stage_string;
    }

    public void setStage_string(String stage_string) throws NotBoundException {
        if(stage_string.equalsIgnoreCase("Intermittent")){
            setStage(this.stage.INTERMITTENT);
            this.stage_string = "Intermittent";
        } else if(stage_string.equalsIgnoreCase("Mild persistent")){
            setStage(this.stage.MILD_PERSISTENT);
            this.stage_string = "Mild persistent";
        } else if(stage_string.equalsIgnoreCase("Moderate persistent")){
            setStage(this.stage.MODERATE_PERSISTENT);
            this.stage_string = "Moderate persistent";
        } else if(stage_string.equalsIgnoreCase("Severe persistent")){
            setStage(this.stage.SEVERE_PERSISTENT);
            this.stage_string = "Severe persistent";
        } else if(stage_string.equalsIgnoreCase("NONE")){
            setStage(this.stage.NONE);
            this.stage_string = "NONE";
        }else if(stage_string.equalsIgnoreCase("CRISIS")){
            setStage(this.stage.CRISIS);
            this.stage_string = "CRISIS";
        }  else {
            throw new NotBoundException("Not valid stge. Introduce a valid condition: Intermittent, Mild persistent, Moderate persistent, Severe persistent or NONE");
        }
    }
    
    public int getPS() {
        return PS;
    }

    public void setPS(int PS) {
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

    public int getDayTimeSymptoms_w() {
        return dayTimeSymptoms_w;
    }

    public void setDayTimeSymptoms_w(int dayTimeSymptoms_w) {
        this.dayTimeSymptoms_w = dayTimeSymptoms_w;
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