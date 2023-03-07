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
     
        Patient p = new Patient(1234, "Gisela", "Aragon", 70, "Female", FALSE, TRUE, FALSE, TRUE, "EPOC", 0);
        EPOC e = new EPOC("NONE");
        Treatment t = new Treatment("LABA", "1-2 inhalations/day");
        p.addTreatment(t);
        //Treatment t1 = new Treatment("LAMA", "1-2 inhalations/day");
        //p.addTreatment(t1);
        Treatment t2 = new Treatment("IC", "1-2 inhalations/day");
        p.addTreatment(t2);
        e.setCAT(25);
        e.setmMRC(2);
        e.setEosinophilia(false);
        e.setExacerbations(true);
        e.setEOS(200);
        e.setExa(1);
        e.setFEV(60);
        p.setEpoc(e);
        p.setInfluenza_vaccine(true);
        p.setPneumonia_vaccine(true);
        Comorbidity c = new Comorbidity("viral infection");
        p.addComorbidity(c);
        Comorbidity c1 = new Comorbidity("pulmonary tuberculosis");
        p.addComorbidity(c1);
        p.setSymptoms_controlled(false);
        
        // Inserta el objeto de paciente en la sesión de drools
        ksession.insert(p);
        //System.out.println(t.getDrug());

        // and fire the rules
        ksession.fireAllRules();

        System.out.println(p.getRespiratorydisease());
        System.out.println(p.getEpoc().getCondition_string());
        System.out.println(p.treatment_list.isEmpty());
        System.out.println(p.string_treatments);
        System.out.println(p.string_comorbidities);
        System.out.println(p.hospitalization);
        System.out.println(p.string_comorbidities.contains("cardiovascular diseases"));
        System.out.println(p.getEpoc().getCAT());
        System.out.println(p.getEpoc().getmMRC());
        System.out.println(p.getEpoc().EOS);
        System.out.println(p.getEpoc().exa);
        System.out.println(p.getEpoc().FEV);
        System.out.println(p.getEpoc().exacerbations);

        

        // and then dispose the session
        ksession.dispose();
    }
}
