/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

/**
 *
 * @author gisel
 */
public class Allergy {
    private Integer aid;
    private String aname;
    private String type;

    public Allergy() {
    }

    public Allergy(Integer aid, String name, String type) {
        this.aid = aid;
        this.aname = name;
        this.type = type;
    }

    public Allergy(String name, String type) {
        this.aname = name;
        this.type = type;
    }

    public Integer getAllergyId() {
        return aid;
    }

    public void setAllergyId(Integer aid) {
        this.aid = aid;
    }

    public String getAllergyName() {
        return aname;
    }

    public void setAllergyName(String name) {
        this.aname = name;
    }

    public String getAllergyType() {
        return type;
    }

    public void setAllergyType(String type) {
        if (type.equalsIgnoreCase("evironment")){
            this.type = "Environmental allergy";
        } else if (type.equalsIgnoreCase("drug")){
            this.type = "Drug allergy";
        } else {
            this.type = "Other";
        }
    }

    @Override
    public String toString() {
        return "Allergy{" + "name=" + aname + ", type=" + type + '}';
    }
    
}