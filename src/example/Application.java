package example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.rmi.NotBoundException;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.*;


public class Application {
    
        public static void main(String[] args) throws NotBoundException {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        execute(ks, kc);
        
    }

    public static void execute(KieServices ks, KieContainer kc) throws NotBoundException {
        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        KieSession ksession = kc.newKieSession("diagnosisKS");

        // Once the session is created, the application can interact with it



        // To set up a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );
        // The application can insert facts into the session
     
        /*Patient p = new Patient(1234,"Gisela",4,"Female", FALSE, FALSE, FALSE,FALSE,FALSE,FALSE,"Asthma", 6);
        Asthma a = new Asthma();
        Treatment t = new Treatment("LABA", "1-2 inhalations/day");
        p.addTreatment(t);
        
       
        a.setPS(5);
        a.setPEF(70);
        a.setSAT_O2(93);
        
        a.setStage_string("NONE");
        p.setAsthma(a);
        
        p.setInfluenza_vaccine(true);
        p.setPneumonia_vaccine(true);
        Comorbidity c = new Comorbidity("viral infection");
        //p.addComorbidity(c);
        Comorbidity c1 = new Comorbidity("cardiovascular disease");
        //p.addComorbidity(c1);
        p.setSymptoms_controlled(false);
        *//*
        Patient p = new Patient(1234,"Maria",4,"Female", FALSE, FALSE, TRUE,"EPOC");
        Comorbidity c = new Comorbidity(3,"pulmonary tuberculosis");
        Comorbidity s = new Comorbidity(4,"viral infection");
        p.addComorbidity(c);
        p.addComorbidity(s);
        EPOC epoc = new EPOC(); 
        epoc.setmMRC(2);
        epoc.setCAT(21);
        epoc.setExa(3);
        epoc.setEOS(400);
        epoc.setEosinophilia(false);
        p.setEpoc(epoc);
        epoc.setCondition_string("none");*/
        
       Patient p = new Patient(1234,"Marta",6,"Female", FALSE, FALSE, FALSE,"Asthma");
       //p.setTreatment_stage(2);
       p.setSymptoms_controlled(true);
       Asthma a = new Asthma();
       a.setDayTimeSymptoms_w(1);
       a.setrescueMedication_w(1);
       a.setnocturnalSymptoms_w(0);
       a.setlimitations(0);
       a.setpulmonar_function(85);
       a.setexarcebations_y(0);
       p.setAsthma(a);
       a.setStage_string("NONE");
       
        // Inserta el objeto de paciente en la sesión de drools
        ksession.insert(p);
        //System.out.println(t.getDrug());

        // and fire the rules
        ksession.fireAllRules();

        /*System.out.println(p.getRespiratorydisease());
        System.out.println(p.treatment_list.isEmpty());
        System.out.println(p.string_treatments);
        System.out.println(p.string_comorbidities);
        System.out.println(p.hospitalization);
        System.out.println(p.treat_stage);
        System.out.println(p.getAsthma().dayTimeSymptoms_w);
        System.out.println(p.getAsthma().rescueMedication_w);
        System.out.println(p.getAsthma().nocturnalSymptoms_w);
        System.out.println(p.getAsthma().limitations);
        System.out.println(p.getAsthma().pulmonar_function);
        System.out.println(p.getAsthma().exarcebations_y);
        System.out.println(p.getAsthma().PS);
        System.out.println(p.getAsthma().PEF);
        System.out.println(p.getAsthma().SAT_O2);
        System.out.println(p.getTreatment_List());
        System.out.println(p.getComorbidity());
        System.out.println(epoc.getmMRC());
        System.out.println(epoc.getCAT());*/
        System.out.println(p.getTreatment_List());
        System.out.println(a.getStage_string());
        // and then dispose the session
        ksession.dispose();

    }
}
