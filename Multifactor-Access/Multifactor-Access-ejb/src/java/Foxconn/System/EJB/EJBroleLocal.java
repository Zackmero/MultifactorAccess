/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.EJB;

import Foxconn.System.ENTITY.Role;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zacarias_Mercado
 */
@Local
public interface EJBroleLocal {

    Alert ADD(Role role);

    Alert EDIT(Role role);

    Alert REMOVE(Role role);

    Alert searchByID(int id);

    Role searchByRole(String role);

    List<Role> roleList();

}
