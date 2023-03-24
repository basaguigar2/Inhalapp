package example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import jdbc.interfaces.DBManager;
import jdbc.sqlite.SQLiteManager;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import pojos.Comorbidity;
import pojos.EPOC;
import pojos.Patient;

public class Application {

    public static void main(String[] args) {
        try {
            KieServices ks = KieServices.Factory.get();
            KieContainer kc = ks.getKieClasspathContainer();
            KieSession ksession = kc.newKieSession("diagnosisKS");
            Patient p = new Patient();
            p.setRespiratorydisease("EPOC");
            EPOC e = new EPOC();
            e.setCondition_string("NONE");
            Comorbidity c = new Comorbidity("cardiovascular diseases");
            p.addComorbidity(c);
            c = new Comorbidity("vision disorders");
            p.addComorbidity(c);
            System.out.println(p.getString_comorbidities());
            p.setEpoc(e);
            ksession.insert(p);
            ksession.fireAllRules();
            ksession.dispose();
            
            System.out.println(p.getTreatment_List());
        } catch (NotBoundException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

