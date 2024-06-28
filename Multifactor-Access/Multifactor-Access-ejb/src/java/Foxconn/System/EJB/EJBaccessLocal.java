/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Foxconn.System.EJB;

import Foxconn.System.ENTITY.Access;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zacarias_Mercado
 */
@Local
public interface EJBaccessLocal {

    Alert ADD(Access access);
    Alert EDIT(Access access);
    Alert REMOVE(Access access);
    Alert searchForID(int id);
    List<Access> accessList();
    Access accessGetByArea(String area);
    
}
