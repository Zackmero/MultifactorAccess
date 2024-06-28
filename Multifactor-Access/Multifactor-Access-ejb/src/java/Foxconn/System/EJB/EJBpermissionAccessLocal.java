/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Foxconn.System.EJB;

import Foxconn.System.ENTITY.PermissionAccess;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zacarias_Mercado
 */
@Local
public interface EJBpermissionAccessLocal {

    Alert ADD(PermissionAccess permission);

    Alert EDIT(PermissionAccess permission);

    Alert REMOVE(PermissionAccess permission);

    PermissionAccess searchByID(int id);

    List<PermissionAccess> permissionAccessList();
    
}
