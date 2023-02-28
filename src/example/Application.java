package example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.EPOC;
import pojos.PulmonaryCondition;
import pojos.Treatment;


public class Application {
    
        public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        execute(ks, kc);
    }

    public static void execute(KieServices ks, KieContainer kc) {
        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        KieSession ksession = kc.newKieSession("diagnosisKS");

        // Once the session is created, the application can interact with it
        System.out.println(kc.getKieBaseNames());



        // To set up a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );
        // The application can insert facts into the session
        EPOC patient = new EPOC(PulmonaryCondition.SEVERE_CHRONIC_HYPOXEMIA);
        
        System.out.println(patient);
        
        Treatment t = new Treatment();
        // Inserta el objeto de paciente en la sesión de drools
        ksession.insert(patient);
        //System.out.println(t.getDrug());
        
      

        // and fire the rules
        ksession.fireAllRules();

        System.out.println(patient);

        

        // and then dispose the session
        ksession.dispose();
    }
}
