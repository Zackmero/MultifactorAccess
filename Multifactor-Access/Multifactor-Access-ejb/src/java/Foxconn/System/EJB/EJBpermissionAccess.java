/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Foxconn.System.EJB;


import Foxconn.System.DAO.DAOpermissionAccess;
import Foxconn.System.ENTITY.PermissionAccess;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Zacarias_Mercado
 */
@Stateless
public class EJBpermissionAccess implements EJBpermissionAccessLocal {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    DAOpermissionAccess permissionDAO = new DAOpermissionAccess();

    @Override
    public Alert ADD(PermissionAccess permission) {
        permissionDAO.permissionAccessADD(permission);
        return Alert.OK;
    }

    @Override
    public Alert EDIT(PermissionAccess permission) {
        permissionDAO.permissionAccessEDIT(permission);
        return Alert.OK;
    }

    @Override
    public Alert REMOVE(PermissionAccess permission) {
        permissionDAO.permissionAccessREMOVE(permission);
        return Alert.OK;
    }

    @Override
    public PermissionAccess searchByID(int id) {
        return permissionDAO.permissionAccessGetByID(id);
    }

    @Override
    public List<PermissionAccess> permissionAccessList() {
        return permissionDAO.permissionAccessGetAll();
    }

}
