/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.EJB;

import Foxconn.System.DAO.DAOgroupEmployee;
import Foxconn.System.ENTITY.GroupEmployee;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Stateless
public class EJBgroupEmployee implements EJBgroupEmployeeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    DAOgroupEmployee groupEmployeeDAO = new DAOgroupEmployee();

    // CALL METHOD TO ADD GROUP EMPLOYEE
    @Override
    public Alert ADD(GroupEmployee group) {
        groupEmployeeDAO.groupADD(group);
        return Alert.OK;
    }

    // CALL METHOD TO EDIT GROUP EMPLOYEE
    @Override
    public Alert EDIT(GroupEmployee group) {
        groupEmployeeDAO.groupEDIT(group);
        return Alert.OK;
    }

    // CALL METHOD TO DELETE GROUP EMPLOYEE
    @Override
    public Alert REMOVE(GroupEmployee group) {
        groupEmployeeDAO.groupREMOVE(group);
        return Alert.OK;
    }

    // CALL METHOD TO SEARCH GROUP BY ID
    @Override
    public Alert groupGetByID(int id) {
        groupEmployeeDAO.groupGetByID(id);
        return Alert.OK;
    }

    // CALL METHOD TO SEARCH GROUP BY GROUP
    @Override
    public GroupEmployee groupGetByGroup(String group) {
        return groupEmployeeDAO.groupGetByGroup(group);
    }

    // CALL METHOD TO GET GROUP LIST
    @Override
    public List<GroupEmployee> groupList() {
        return groupEmployeeDAO.groupGetAll();
    }

}
