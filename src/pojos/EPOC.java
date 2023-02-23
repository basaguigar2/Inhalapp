/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

/**
 *
 * @author gisel
 */
public class EPOC {
    public PulmonaryCondition condition;
    public boolean vaccine1,vaccine2, hospitalization, last_treatment; 
    public int mMRC,EOS,exacerb,FEV;
    public Treatment previous_treatment;

    public EPOC(PulmonaryCondition condition, boolean vaccine1, boolean vaccine2, boolean hospitalization, boolean last_treatment, int mMRC, int EOS, int exacerb, int FEV, Treatment previous_treatment) {
        this.condition = condition;
        this.vaccine1 = vaccine1;
        this.vaccine2 = vaccine2;
        this.hospitalization = hospitalization;
        this.last_treatment = last_treatment;
        this.mMRC = mMRC;
        this.EOS = EOS;
        this.exacerb = exacerb;
        this.FEV = FEV;
        this.previous_treatment = previous_treatment;
    }

    
    //Construcotr para hacer pruebas, no importante
    public EPOC(PulmonaryCondition condition) {
        this.condition = condition;
    }

    public PulmonaryCondition getCondition() {
        return condition;
    }

    public void setCondition(PulmonaryCondition condition) {
        this.condition = condition;
    }

 

    public boolean isHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(boolean hospitalization) {
        this.hospitalization = hospitalization;
    }

    public boolean isLast_treatment() {
        return last_treatment;
    }

    public void setLast_treatment(boolean last_treatment) {
        this.last_treatment = last_treatment;
    }

    public int getmMRC() {
        return mMRC;
    }

    public void setmMRC(int mMRC) {
        this.mMRC = mMRC;
    }

    public int getEOS() {
        return EOS;
    }

    public void setEOS(int EOS) {
        this.EOS = EOS;
    }

    public int getExacerb() {
        return exacerb;
    }

    public void setExacerb(int exacerb) {
        this.exacerb = exacerb;
    }

    public int getFEV() {
        return FEV;
    }

    public void setFEV(int FEV) {
        this.FEV = FEV;
    }

    public Treatment getPrevious_treatment() {
        return previous_treatment;
    }

    public void setPrevious_treatment(Treatment previous_treatment) {
        this.previous_treatment = previous_treatment;
    }
    
}