/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Foxconn.System.EJB;

import Foxconn.System.DAO.DAOaccess;
import Foxconn.System.ENTITY.Access;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Zacarias_Mercado
 */

@Stateless
public class EJBaccess implements EJBaccessLocal {

    
    DAOaccess accessDAO = new DAOaccess();

    // CALL METHOD TO ADD ACCESS
    @Override
    public Alert ADD(Access access) {
        accessDAO.accessADD(access);
        return Alert.OK;
    }

    // CALL METHOD TO EDIT ACCESS
    @Override
    public Alert EDIT(Access access) {
        accessDAO.accessEDIT(access);
        return Alert.OK;
    }

    // CALL METHOD TO DELETE ACCESS
    @Override
    public Alert REMOVE(Access access) {
        accessDAO.accessREMOVE(access);
        return Alert.OK;
    }

    // CALL METHOD TO SEARCH ACCESS BY ID
    @Override
    public Alert searchForID(int id) {
        accessDAO.accessGetByID(id);
        return Alert.OK;
    }

    // CALL METHOD TO SEARCH ACCESS BY AREA
    @Override
    public Access accessGetByArea(String area) {
        System.out.println("Metodo AccessGetByArea EJB");
        return accessDAO.accessGetByArea(area);
    }

    // CALL METHOD TO GET ACCESS LIST
    @Override
    public List<Access> accessList() {
        //System.out.println("Metodo AcessGetList EJB");
        return accessDAO.accessGetAll();
    }

}
