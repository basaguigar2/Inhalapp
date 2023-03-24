/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc.interfaces;

import java.sql.SQLException;
import pojos.EPOC;

/**
 *
 * @author gisel
 */
public interface EpocManager {
    public void addEPOC(EPOC e) throws SQLException;
    public void addEPOC2(EPOC e) throws SQLException;
    public EPOC selectEPOC(Integer EPOC_id);
    public EPOC getEPOCFromPatient(Integer patientId);
    public int getLastId();
    public void editEPOC(Integer id,Integer eos,Integer fev,Integer exa, Boolean exacerbations);
}
