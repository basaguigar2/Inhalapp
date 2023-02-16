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
public class Disease {
    private Integer disease_id;
    private String disease_name;
    private String stage;

    public Disease() {
    }

    public Disease(Integer disease_id, String name, String stage) {
        this.disease_id = disease_id;
        this.disease_name = name;
        this.stage = stage;
    }

    public Disease(String name, String stage) {
        this.disease_name = name;
        this.stage = stage;
    }

    public Integer getDiseaseId() {
        return disease_id;
    }

    public void setDiseaseId(Integer disease_id) {
        this.disease_id = disease_id;
    }

    public String getDiseaseName() {
        return disease_name;
    }

    public void setDiseaseName(String name) throws NotBoundException {
        if(name.equalsIgnoreCase("asthma")){
            this.disease_name = "Asthma";
        } else if(name.equalsIgnoreCase("EPOC")){
            this.disease_name = "EPOC";
        } else{
            throw new NotBoundException("Not valid disease. Introduce a valid disease: asthma or EPOC");
        }
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) throws NotBoundException {
        if (this.disease_name.equalsIgnoreCase("Asthma")){
            if(stage.equalsIgnoreCase("intermittent")){
                this.stage = "Intermittent";
            } else if(stage.equalsIgnoreCase("mild persistent")){
                this.stage = "Mild persistent";
            } else if(stage.equalsIgnoreCase("moderate persistent")){
                this.stage = "Moderate persistent";
            } else if(stage.equalsIgnoreCase("severe persistent")){
                this.stage = "Severe persistent";
            } else{
                throw new NotBoundException("Not valid stage. Introduce a valid stage: Intermitent, Mild persistent, Moderate persistent, or Severe persistent.");
            }
        } else if (this.disease_name.equalsIgnoreCase("EPOC")){
            if(stage.equalsIgnoreCase("stage 1")){
                this.stage = "Stage 1";
            } else if(stage.equalsIgnoreCase("stage 2")){
                this.stage = "Stage 2";
            } else if(stage.equalsIgnoreCase("stage 3")){
                this.stage = "Stage 3";
            } else if(stage.equalsIgnoreCase("stage 4")){
                this.stage = "Stage 4";
            } else{
                throw new NotBoundException("Not valid stage. Introduce a valid stage: Stage 1, Stage 2, Stage 3, or Stage 4.");
            }
        }
    }

    @Override
    public String toString() {
        return "Disease{" + "name=" + disease_name + ", stage=" + stage + '}';
    }
    
}