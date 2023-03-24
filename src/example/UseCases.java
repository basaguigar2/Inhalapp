/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example;
import jdbc.interfaces.AsthmaManager;
import jdbc.interfaces.ComorbidityManager;
import jdbc.interfaces.DBManager;
import jdbc.interfaces.EpocManager;
import jdbc.interfaces.PatientManager;
import jdbc.interfaces.TreatmentManager;
import jdbc.sqlite.SQLiteManager;
import jdbc.sqlite.SQLiteUserManager;
import java.rmi.NotBoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.Asthma;
import pojos.Comorbidity;
import pojos.EPOC;
import pojos.Patient;
import pojos.Treatment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class UseCases {
    
private static DBManager dbManager = new SQLiteManager();
  

        public static void main(String[] args) throws NotBoundException{
            
        dbManager.connect();
        dbManager.createTables();
        
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        execute(ks, kc);
        } 

    public static void execute(KieServices ks, KieContainer kc) throws NotBoundException {
        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        KieSession ksession = kc.newKieSession("diagnosisKS");
        
        //Use cases:
        
        //1. We receive a new patient, that has been never treated before. His name is Alvaro, is 25 years old and has EPOC with eosinoplhilia. 
        //He does not present any comorbidity of risk, he does not smoke and he is not vaccinated with the influenza vaccine or the pneumonia vaccine.
        //Regarding the symptomatological indicators, he presents a socre of 14 in CAT and 2 in MRC.
        //Hospitalization=true?
        //Expected LABA + IC + Influenza vaccine
        Patient p1 = new Patient (1, "Sujeto 1", 25, "MALE", false,false, false, "EPOC");
        p1.setSymptoms_controlled(false);
        
        List <Treatment> treatments1 = new ArrayList<>();
        Treatment t1 = new Treatment("LABA", "1-2 inhalations/day");
        Treatment t2 = new Treatment("LAMA", "1-2 inhalations/day");
        treatments1.add(t1);
        treatments1.add(t2);
        p1.setTreatment_List(treatments1);
        
        EPOC e1 = new EPOC();
        e1.setCondition_string("NONE");
        e1.setDisnea(false);
        e1.setEOS(200);
        e1.setExa(2);
        
        p1.setEpoc(e1);
        
        //2. We receive a patient, that suffers from COPD and severe chronic hypoxemia. The patient is a female of 40 years old, she is not pregnant,
        // she does not smoke either, and is not hospitalized.
        //Expected: Chronic home oxygen therapy
        Patient p2 = new Patient (2, "Sujeto 2", 40, "FEMALE", false,false, false, "EPOC");
        
        EPOC e2 = new EPOC();
        e2.setCondition_string("SEVERE_CHRONIC_HYPOXEMIA");
        
        p2.setEpoc(e2);
        
        //3. We receive a patient that suffers from COPD. The patient is a male of 60 years old that haas receive LABA/LAMA as previous
        // treatment, but his/her symptoms have not been controlled. The patient also suffers from dyspnea.
        //Expected: Change device and investigate other causes.
        Patient p3 = new Patient (3, "Sujeto 3", 60, "MALE", false,false, false, "EPOC");
        p3.setSymptoms_controlled(false);
        
        List <Treatment> treatments = new ArrayList<>();
        Treatment t3 = new Treatment("IC", "1-2 inhalations/day");
        treatments.add(t1);
        treatments.add(t2);
        treatments.add(t3);
        p3.setTreatment_List(treatments);
        
        EPOC e3 = new EPOC();
        e3.setCondition_string("NONE");
        e3.setDisnea(true);
        p3.setEpoc(e3);
        
        //4. We receive a patient that suffers from COPD, but no other pulmonary contidion, neither dyspnea. The patient is a 50 year old female
        // The patient has received LABA/LAMA as previous treatments, but the symptoms are not controlled yet. The patient suffers from
        // a cardio vascular disease. The eosinophilic count of the patient is under 100 and the forced expiratory volume is under 50.
        //Expected: Roflumilast or LABA/LAMA + influenza vaccine
        Patient p4 = new Patient (4, "Sujeto 4", 50, "FEMALE", false,true, false, "EPOC");
        p4.setSymptoms_controlled(false);
        
        //The list of treatments is the same as the previous use case
        p4.setTreatment_List(treatments);
        
        EPOC e4 = new EPOC();
        e4.setCondition_string("NONE");
        e4.setDisnea(false);
        e4.setEOS(90);
        e4.setFEV(20);
        p4.setEpoc(e4);

        //5. We receive a patient that suffers from mild persistent (stage 2) asthma. The patient is a 20 year old male that has 
        // been treated before and his symptoms are controlled.
        //Expected: No medication, in case of crisis 2-4 inhalations of BAAC on demand.
        Patient p5 = new Patient (5, "Sujeto 5", 20, "MALE", false,false, false, "Asthma");
        p5.setSymptoms_controlled(true);
        
        //The list of treatments is the same as the previous use case, as the type of treatment is not relevant in thisi case
        p5.setTreatment_List(treatments);
                
        Asthma a1 = new Asthma();
        a1.setStage_string("MILD PERSISTENT");
        p5.setTreatment_stage(2);
        p5.setAsthma(a1);
        
        //6. We receive a patient that suffers from severe persistent (stage 4)asthma. The patient is a 4-year-old female
        //that has received previous treatment and her symptoms are controlled. The patient does not suffer from viral
        // infection neither pulmonary tuberculosis.
        //Expected: GCI medium dosis, in case of crisis 2-4 inhalations of BAAC on demand.
        Patient p6 = new Patient (6, "Sujeto 6", 4, "FEMALE", false,false, false, "Asthma");
        p6.setSymptoms_controlled(true);
        
        //The list of treatments is the same as the previous use case, as the type of treatment is not relevant in thisi case
        p6.setTreatment_List(treatments);
        
        List <Comorbidity> comorbidities = new ArrayList<>();
        Comorbidity c1 = new Comorbidity("cardiovascular diseases");
        comorbidities.add(c1);
        p6.setComorbidity(comorbidities);
                
        Asthma a2 = new Asthma();
        a2.setStage_string("SEVERE PERSISTENT");
        p6.setTreatment_stage(4);
        p6.setAsthma(a2);
        
        //7. We receive a patient that suffers from asthma. The patient is a 3-year-old male that has not
        //been previously treated. The patient also suffers from a viral infection. He does not suffers from
        // any cardiovascular disease. The patient presents daytime symptoms more than 2 times/week (3),
        // and nocturnal symptoms more than 2 times/month (1). The patient also needs to use
        // rescue medication more than 2times/week (3), he presents some daily limitations (1). He presents exacerbations
        // less than one time a year (1). His results with the tests are: pulmonar function = 90.
        //Expected: 0.02 mg/day ARLT, update the stage of the asthma to mild persistent
        Patient p7 = new Patient (7, "Sujeto 7", 3, "MALE", false,false, false, "Asthma");
        p7.setSymptoms_controlled(true);
        
        List <Comorbidity> comorbidities2 = new ArrayList<>();
        Comorbidity c2 = new Comorbidity("viral infection");
        comorbidities2.add(c2);
        p7.setComorbidity(comorbidities2);
                
        Asthma a3 = new Asthma();
        a3.setStage_string("NONE");
        a3.setDayTimeSymptoms_w(3);
        a3.setnocturnalSymptoms_w(1);
        a3.setrescueMedication_w(3);
        a3.setlimitations(1);
        a3.setexarcebations_y(1);
        a3.setpulmonar_function(90);
        p7.setAsthma(a3);
        
        //8. We receive a patient that suffers an asthmatic crisis. The patient is a 35-year-old female that
        //has not receive previous treatment. The results from the tests are: pulmonar score = 8,
        // PEF = 50, and oxygen saturation = 90
        //Expected: 0.5-1 mg of BAAC on demand, combined with 3 mg/kg/day of Sistemic Corticoidsis and Suplementary O2 on demand.
        Patient p8 = new Patient (8, "Sujeto 8", 35, "FEMALE", false,false, false, "Asthma");
        
        Asthma a4 = new Asthma();
        a4.setStage_string("CRISIS");
        a4.setPS(8);
        a4.setPEF(50);
        a4.setSAT_O2(90);
        p8.setAsthma(a4);
        
        
        ksession.insert(p1);
        ksession.insert(p2);
        ksession.insert(p3);
        ksession.insert(p4);
        ksession.insert(p5);
        ksession.insert(p6);
        ksession.insert(p7);
        ksession.insert(p8);
        
        // and fire the rules
        ksession.fireAllRules();
        

        // and then dispose the session
        ksession.dispose();
    }

}