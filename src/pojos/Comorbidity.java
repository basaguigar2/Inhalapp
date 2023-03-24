/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojos;

/**
 *
 * @author gisel
 */
public class Comorbidity {
    private Integer cid;
    private String cname;

    public Comorbidity() {
    }

    public Comorbidity(Integer cid, String name) {
        this.cid = cid;
        this.cname = name;
    }

    public Comorbidity(String name) {
        this.cname = name;
    }

    public Integer getComorbidityId() {
        return cid;
    }

    public void setComorbidityId(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setComorbidityName(String name) {
        this.cname = name;
    }

    @Override
    public String toString() {
        return "Comorbidity{" + "name=" + cname + '}';
    }

}