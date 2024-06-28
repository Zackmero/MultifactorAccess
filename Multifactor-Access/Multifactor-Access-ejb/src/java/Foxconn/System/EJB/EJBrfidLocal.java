/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Foxconn.System.EJB;

import Foxconn.System.ENTITY.Rfid;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zacarias_Mercado
 */
@Local
public interface EJBrfidLocal {

    Alert ADD(Rfid rfid);

    Alert EDIT(Rfid rfid);

    Alert REMOVE(Rfid rfid);

    Alert searchByID(int id);

    List<Rfid> rfidList();
    
}
