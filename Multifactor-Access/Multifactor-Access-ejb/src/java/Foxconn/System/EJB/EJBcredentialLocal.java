/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.EJB;

import Foxconn.System.ENTITY.Credential;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Local;


@Local
public interface EJBcredentialLocal {

    Alert ADD(Credential credential);

    Alert EDIT(Credential credential);

    Alert REMOVE(Credential credential);

    Alert credentialGetByID(int id);

    List<Credential> credentialGetAll();

    boolean credentialGetByUser(String user, String password);

    

}
