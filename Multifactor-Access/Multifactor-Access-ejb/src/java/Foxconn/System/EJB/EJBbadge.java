/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Foxconn.System.EJB;

import Warnings.Alert;
import Foxconn.System.DAO.DAObadge;

import Foxconn.System.ENTITY.Badge;
import Foxconn.System.ENTITY.Employee;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Zacarias_Mercado
 */
@Named
@Stateless
public class EJBbadge implements EJBbadgeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    DAObadge badgeDAO = new DAObadge();

    @Override
    public Alert ADD(Badge badge) {
        //System.out.println("Se agregará un gafete EJB");
        badgeDAO.BadgeADD(badge);
        return Alert.OK;
    }

    @Override
    public Alert EDIT(Badge badge) {
        badgeDAO.BadgeEDIT(badge); //Conectarse con el DAO_Empleado metodo EmpleadoEditar
        return Alert.OK;
    }

    @Override
    public Alert REMOVE(Badge badge) {
        badgeDAO.BadgeREMOVE(badge); //Conectarse con el DAO_Empleado metodo EmpleadoElimnar
        return Alert.OK;
    }

    @Override
    public Integer searchForID(int id) {
        Badge b = badgeDAO.BadgeSearchByID(id); //Conectarse con el DAO_Empleado metodo EmpleadoGetPorID
        if (b.getId() != null) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public List<Badge> BadgeList() {
        return badgeDAO.BadgeListDAO();
    }

    @Override
    public boolean validateBadgeEJB(String tagId) {
        return badgeDAO.badgeValidationDAO(tagId);
    }

}
