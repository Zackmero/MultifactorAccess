/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Foxconn.System.EJB;

import Foxconn.System.DAO.DAOrfid;
import Foxconn.System.ENTITY.Rfid;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Zacarias_Mercado
 */
@Stateless
public class EJBrfid implements EJBrfidLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    DAOrfid rfidDAO = new DAOrfid();

    @Override
    public Alert ADD(Rfid rfid) {
        rfidDAO.rfidADD(rfid);
        return Alert.OK;
    }

    @Override
    public Alert EDIT(Rfid rfid) {
        rfidDAO.rfidEDIT(rfid);
        return Alert.OK;
    }

    @Override
    public Alert REMOVE(Rfid rfid) {
        rfidDAO.rfidREMOVE(rfid);
        return Alert.OK;
    }

    @Override
    public Alert searchByID(int id) {
        rfidDAO.rfidGetByID(id);
        return Alert.OK;
    }

    @Override
    public List<Rfid> rfidList() {
        return rfidDAO.rfidGetAll();
    }

}
