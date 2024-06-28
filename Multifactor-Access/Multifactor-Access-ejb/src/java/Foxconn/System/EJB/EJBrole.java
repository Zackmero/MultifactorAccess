/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.EJB;

import Foxconn.System.DAO.DAOrole;
import Foxconn.System.ENTITY.Role;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Zacarias_Mercado
 */
@Stateless
public class EJBrole implements EJBroleLocal {

    DAOrole rd = new DAOrole();

    @Override
    public Alert ADD(Role role) {
        rd.roleADD(role);
        return Alert.OK;
    }

    @Override
    public Alert EDIT(Role role) {
        rd.roleEDIT(role);
        return Alert.OK;
    }

    @Override
    public Alert REMOVE(Role role) {
        rd.roleREMOVE(role);
        return Alert.OK;
    }

    @Override
    public Alert searchByID(int id) {
        rd.roleGetByID(id);
        return Alert.OK;
    }

    @Override
    public Role searchByRole(String role) {
        return rd.roleGetByRole(role);
    }

    @Override
    public List<Role> roleList() {
        return rd.roleGetAll();
    }

}
