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
public class EPOC {
    public Integer EPOC_id;
    public PulmonaryCondition condition;
    public String condition_string;
    public boolean exacerbations, disnea,eosinophilia; 
    public int mMRC,EOS,FEV,CAT,exa;

    public EPOC() {
    }

    public EPOC(String condition_string) {
        this.condition_string = condition_string;
    }

    public EPOC(String condition_string, boolean eosinophilia, int mMRC, int CAT, int exa) {
        this.condition_string = condition_string;
        this.eosinophilia = eosinophilia;
        this.mMRC = mMRC;
        this.CAT = CAT;
        this.exa = exa;
    }

    public EPOC(String condition, int mMRC, int EOS, int CAT, int exa, boolean exacerb, boolean eosinophilia, int FEV) {
        this.condition_string = condition;
        this.mMRC = mMRC;
        this.EOS = EOS;
        this.CAT = CAT;
        this.exa = exa;
        this.exacerbations = exacerb;
        this.eosinophilia = eosinophilia;
        this.FEV = FEV;
    }
    
    public EPOC(String condition, int mMRC, int EOS, int CAT, int exa, boolean exacerb, boolean eosinophilia, int FEV, Integer id) {
        this.condition_string = condition;
        this.mMRC = mMRC;
        this.EOS = EOS;
        this.CAT = CAT;
        this.exa = exa;
        this.exacerbations = exacerb;
        this.eosinophilia = eosinophilia;
        this.FEV = FEV;
        this.EPOC_id = id;
    }

    public EPOC(PulmonaryCondition condition) {
        this.condition = condition;
    }

    public Integer getEPOC_id() {
        return EPOC_id;
    }

    public void setEPOC_id(Integer EPOC_id) {
        this.EPOC_id = EPOC_id;
    }
    
    public PulmonaryCondition getCondition() {
        return condition;
    }
    
    public void setCondition(PulmonaryCondition condition) {
        this.condition = condition;
    }

    public String getCondition_string() {
        return condition_string;
    }

    public void setCondition_string(String condition_string) throws NotBoundException {
        if(condition_string.equalsIgnoreCase("SEVERE_CHRONIC_HYPOXEMIA")){
            setCondition(this.condition.SEVERE_CHRONIC_HYPOXEMIA);
            this.condition_string = "Severe chronic hypoxemia";
        } else if(condition_string.equalsIgnoreCase("Bulla")){
            setCondition(this.condition.BULLA);
            this.condition_string = "Bulla";
        } else if(condition_string.equalsIgnoreCase("SEVERE_HETEROGENEOUS_EMPHYSEMA_IN_UPPER_LOBES")){
            setCondition(this.condition.SEVERE_HETEROGENEOUS_EMPHYSEMA_IN_UPPER_LOBES);
            this.condition_string = "Severe heterogeneous emphysema in upper lobes";
        } else if(condition_string.equalsIgnoreCase("ADVANCED_EMPHYSEMA")){
            setCondition(this.condition.ADVANCED_EMPHYSEMA);
            this.condition_string = "Advanced emphysema";
        } else if(condition_string.equalsIgnoreCase("SEVERE_COPD")){
            setCondition(this.condition.SEVERE_COPD);
            this.condition_string = "Severe COPD";
        } else if(condition_string.equalsIgnoreCase("NONE")){
            setCondition(this.condition.NONE);
            this.condition_string = "NONE";
        } else if (this.condition_string == null){
            setCondition(this.condition.NONE);
            this.condition_string = "NONE";
        } else {
            throw new NotBoundException("Not valid condition. Introduce a valid condition: Severe chronic hypoxemia, Bulla, Severe heterogeneous emphysema in upper lobes, Advanced emphysema, Severe COPD, or NONE");
        }
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

    public boolean isExacerbations() {
        return exacerbations;
    }

    public void setExacerbations(boolean exacerb) {
        this.exacerbations = exacerb;
    }

    public boolean isEosinophilia() {
        return eosinophilia;
    }

    public void setEosinophilia(boolean eosinophilia) {
        this.eosinophilia = eosinophilia;
    }

    public int getFEV() {
        return FEV;
    }

    public void setFEV(int FEV) {
        this.FEV = FEV;
    }

    public int getCAT() {
        return CAT;
    }

    public void setCAT(int CAT) {
        this.CAT = CAT;
    }

    public int getExa() {
        return exa;
    }

    public void setExa(int exa) {
        this.exa = exa;
    }

    public boolean isDisnea() {
        return disnea;
    }

    public void setDisnea(boolean disnea) {
        this.disnea = disnea;
    }

    @Override
    public String toString() {
        return "EPOC{" + "condition=" + condition_string + ", eosinophilia=" + eosinophilia + ", mMRC=" + mMRC + ", CAT=" + CAT + ", exa=" + exa + '}';
    }
    
    

    
    
    
}