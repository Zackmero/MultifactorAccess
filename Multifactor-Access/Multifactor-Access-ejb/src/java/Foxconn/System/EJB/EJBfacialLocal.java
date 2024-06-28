/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Foxconn.System.EJB;

import Foxconn.System.ENTITY.Facial;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zacarias_Mercado
 */
@Local
public interface EJBfacialLocal {

    Alert ADD(Facial badge);

    Alert EDIT(Facial badge);

    Alert REMOVE(Facial badge);

    Alert searchForID(int id);

    List<Facial> FacialList();
}
