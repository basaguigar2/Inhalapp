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
    
    public static final Treatment CHRONIC_OXIGEN_THERAPY;
    public static final Treatment BULLECTOMY;
    public static final Treatment PULMONAR_VOLUME_REDUCTION;
    public static final Treatment BRONCHOSCOPIC_INTERVENTION;
    public static final Treatment PULMONAR_TRANSPLANT;
    public static final Treatment SABA_SAMA;
    public static final Treatment LABA_LAMA;
    public static final Treatment LAMA;
    public static final Treatment LABA;
    public static final Treatment IC;
    public static final Treatment AZIMOTRICINE;
    public static final Treatment ROFLUMILAST;
    public static final Treatment MUSC_ANTAGONISTS;
    public static final Treatment NO_MED;
    public static final Treatment GCI_LOW_C;
    public static final Treatment GCI_LOW_A;
    public static final Treatment GCI_MED_C;
    public static final Treatment GCI_MED_A;
    public static final Treatment GCI_HIGH_C;
    public static final Treatment GCI_HIGH_A;
    public static final Treatment ARLT_C;
    public static final Treatment ARLT_A;
    public static final Treatment BAAL;
    public static final Treatment GCO_C;
    public static final Treatment GCO_A;
    public static final Treatment BAAC_C;
    public static final Treatment BAAC_A;
    public static final Treatment BAAC_CRISIS;
    public static final Treatment SYSTEMIC_CORTICOIDS_C;
    public static final Treatment SYSTEMIC_CORTICOIDS_A;
    public static final Treatment SUPLEMENTARY_O2;
    public static final Treatment INFLUENZAV;
    public static final Treatment PNEUMONIAV;
    
    static{
    String therapy="Chronic home oxygen therapy";
    CHRONIC_OXIGEN_THERAPY= new Treatment(therapy);
    }
    static {
    String therapy = "You should get the influenza vaccine";
    INFLUENZAV = new Treatment(therapy);
    }
    static {
    String therapy = "You should get the pneumonia vaccine";
    PNEUMONIAV = new Treatment(therapy);
    }
    static{
    String therapy="Bullectomy";
    BULLECTOMY= new Treatment(therapy);
    }
    static{
    String therapy="Pulmonar volume reduction";
    PULMONAR_VOLUME_REDUCTION= new Treatment(therapy);
    }
    static{
    String therapy="Bronchoscopic intervention";
    BRONCHOSCOPIC_INTERVENTION= new Treatment(therapy);
    }
    static{
    String therapy="Pulmonar transplant";
    PULMONAR_TRANSPLANT= new Treatment(therapy);
    }
    static{
    String drug="SABA/SAMA";
    String dose="2-4 inhalations/day";
    SABA_SAMA= new Treatment(drug,dose);
    }
    static{
    String drug="LABA/LAMA";
    String dose="1-2 inhalations/day";
    LABA_LAMA= new Treatment(drug,dose);
    }
    static{
    String drug="LAMA";
    String dose="1-2 inhalations/day";
    LAMA= new Treatment(drug,dose);
    }
    static{
    String drug="LABA";
    String dose="1-2 inhalations/day";
    LABA= new Treatment(drug,dose);
    }
    static{
    String drug="IC";
    String dose="1-2 inhalations/day";
    IC= new Treatment(drug,dose);
    }
    static{
    String drug="Azimotricine";
    String dose="250 mg/day";
    AZIMOTRICINE= new Treatment(drug,dose);
    }
    static{
    String drug="Roflumilast";
    String dose="500 mcg/day";
    ROFLUMILAST= new Treatment(drug,dose);
    }
    static{
    String drug="Muscarinic receptor antagonists";
    String dose="18 mcg/day";
    MUSC_ANTAGONISTS= new Treatment(drug,dose);
    }
    static{
    String drug="No control medication";
    String dose="none";
    NO_MED= new Treatment(drug,dose);
    }
    static{
    String drug="GCI";
    String dose="0.1 mg/day";
    GCI_LOW_C= new Treatment(drug,dose);
    }
    static{
    String drug="GCI";
    String dose="0.2 mg/day";
    GCI_LOW_A= new Treatment(drug,dose);
    }
    static{
    String drug="GCI";
    String dose="0.2 mg/day";
    GCI_MED_C= new Treatment(drug,dose);
    }
    static{
    String drug="GCI";
    String dose="0.3- 0.4mg/day";
    GCI_MED_A= new Treatment(drug,dose);
    }
    static{
    String drug="GCI";
    String dose="0.4 mg/day";
    GCI_HIGH_C= new Treatment(drug,dose);
    }
    static{
    String drug="GCI";
    String dose="0.5 mg/day";
    GCI_HIGH_A= new Treatment(drug,dose);
    }
    static{
    String drug="ARLT";
    String dose="0.02 mg/day";
    ARLT_C= new Treatment(drug,dose);
    }
    static{
    String drug="ARLT";
    String dose="0.04 mg/day";
    ARLT_A= new Treatment(drug,dose);
    }
    static{
    String drug="BAAL";
    String dose="0.05 mg/day";
    BAAL= new Treatment(drug,dose);
    }
    static{
    String drug="GCO";
    String dose="0.5-2 mg/kg/day";
    GCO_C= new Treatment(drug,dose);
    }
    static{
    String drug="GCO";
    String dose="40-60 mg/kg/day";
    GCO_A= new Treatment(drug,dose);
    }
    static{
    String drug="BAAC";
    String dose="0.15-2.5 mg/kg on demand";
    BAAC_C= new Treatment(drug,dose);
    }
    static{
    String drug="BAAC";
    String dose="0.5-1 mg/kg on demand";
    BAAC_A= new Treatment(drug,dose);
    }
    static{
    String drug="BAAC";
    String dose="2-4 inhalations on demand during crisis";
    BAAC_CRISIS= new Treatment(drug,dose);
    }
    static{
    String drug="Sistemic corticoids";
    String dose="1 mg/kg/day";
    SYSTEMIC_CORTICOIDS_C= new Treatment(drug,dose);
    }
    static{
    String drug="Sistemic corticoids";
    String dose="3 mg/kg/day";
    SYSTEMIC_CORTICOIDS_A= new Treatment(drug,dose);
    }
    static{
    String drug="Suplementary O2";
    String dose="On demand";
    SUPLEMENTARY_O2= new Treatment(drug,dose);
    }

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
        return "Treatment{" + "drug=" + drug + ", dose=" + dose + ", therapy:" + therapy +"}";
    }
}