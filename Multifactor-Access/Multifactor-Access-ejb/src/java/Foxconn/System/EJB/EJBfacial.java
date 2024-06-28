/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Foxconn.System.EJB;

import Foxconn.System.DAO.DAOfacial;
import Foxconn.System.ENTITY.Facial;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Zacarias_Mercado
 */
@Stateless
public class EJBfacial implements EJBfacialLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    DAOfacial facialDAO = new DAOfacial();

    @Override
    public Alert ADD(Facial facial) {
        //System.out.println("Se agregará un gafete EJB");
        facialDAO.FacialADD(facial);
        return Alert.OK;
    }

    @Override
    public Alert EDIT(Facial facial) {
        facialDAO.FacialEDIT(facial); //Conectarse con el DAO_Empleado metodo EmpleadoEditar
        return Alert.OK;
    }

    @Override
    public Alert REMOVE(Facial facial) {
        facialDAO.FacialREMOVE(facial); //Conectarse con el DAO_Empleado metodo EmpleadoElimnar
        return Alert.OK;
    }

    @Override
    public Alert searchForID(int id) {
        facialDAO.FacialSearchByID(id); //Conectarse con el DAO_Empleado metodo EmpleadoGetPorID
        return Alert.OK;
    }

    @Override
    public List<Facial> FacialList() {
        System.out.println("Metodo GafeteGetTodos EJB");
        return facialDAO.FacialListDAO();
    }
}
