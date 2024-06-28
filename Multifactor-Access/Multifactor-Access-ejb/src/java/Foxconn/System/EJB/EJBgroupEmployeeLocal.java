/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.EJB;

import Foxconn.System.ENTITY.GroupEmployee;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Local
public interface EJBgroupEmployeeLocal {

    Alert ADD(GroupEmployee group);

    Alert EDIT(GroupEmployee group);

    Alert REMOVE(GroupEmployee group);

    Alert groupGetByID(int id);

    GroupEmployee groupGetByGroup(String group);

    List<GroupEmployee> groupList();
    
}
