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
     /*
        Patient p = new Patient(1234, "Gisela", "Aragon", 4, "Female", 
                FALSE, FALSE, FALSE, FALSE, "Asthma", 6);
        Asthma a = new Asthma();
        Treatment t = new Treatment("LABA", "1-2 inhalations/day");
        p.addTreatment(t);
        
        p.setAsthma(a);
        a.setDayTimeSymptoms_w(9);
        a.setrescueMedication_w(9);
        a.setnocturnalSymptoms_w(7);
        a.setlimitations(3);
        a.setpulmonar_function(50);
        a.setexarcebations_y(3);
        
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
        
        // Inserta el objeto de paciente en la sesión de drools
        ksession.insert(p);
        //System.out.println(t.getDrug());

        // and fire the rules
        ksession.fireAllRules();

        System.out.println(p.getRespiratorydisease());
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

        

        // and then dispose the session
        ksession.dispose();
*/
    }
}
