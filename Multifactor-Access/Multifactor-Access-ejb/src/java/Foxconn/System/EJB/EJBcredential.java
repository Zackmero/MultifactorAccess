/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.EJB;

import Foxconn.System.DAO.DAOcredential;
import Foxconn.System.DAO.DAOemployee;
import Foxconn.System.ENTITY.Credential;
import Foxconn.System.ENTITY.Employee;
import Warnings.Alert;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class EJBcredential implements EJBcredentialLocal {

    DAOcredential credentialDAO = new DAOcredential();

    DAOemployee employeeDAO = new DAOemployee();

    // CALL METHOD TO ADD CREDENTIAL
    @Override
    public Alert ADD(Credential credential) {
        credentialDAO.credentialADD(credential);
        return Alert.OK;
    }

    // CALL METHOD TO EDIT CREDENTIAL
    @Override
    public Alert EDIT(Credential credential) {
        credentialDAO.credentialEDIT(credential);
        return Alert.OK;
    }

    // CALL METHOD TO DELETE CREDENTIAL
    @Override
    public Alert REMOVE(Credential credential) {
        credentialDAO.credentialREMOVE(credential);
        return Alert.OK;
    }

    // CALL METHOD TO SEARCH CREDENTIAL BY ID
    @Override
    public Alert credentialGetByID(int id) {
        credentialDAO.credentialGetByID(id);
        return Alert.OK;
    }

    //CALL METHOD TO SEARCH CREDENTIAL BY USER
    @Override
    public boolean credentialGetByUser(String user, String password) {

        //FOUND CREDENTIAL
        Credential c = (Credential) credentialDAO.credentialGetByUser(user);
        String tag = c.getTbEmployeeId().getEmployeeNumber();
        String pass = c.getPassword();

        //Employee get by Credential employee id
        Employee e;
        e = employeeDAO.employeeGetById(c.getTbEmployeeId().getId());

        System.out.println("Credential EJB: " + tag + " and Password: " + pass);

        System.out.println("employee number found: " + e.getEmployeeNumber());
        System.out.println("credential pass found: " + c.getPassword());
        System.out.println("credential employee number found: " + c.getTbEmployeeId().getEmployeeNumber());
        boolean resultValidation = e.getEmployeeNumber().equals(user) && c.getTbEmployeeId().getEmployeeNumber().equals(user) && c.getPassword().equals(password);
        System.out.println("result validation: " + resultValidation);
        return resultValidation;

    }

    // CALL METHOD TO GET ALL CREDENTIALS
    @Override
    public List<Credential> credentialGetAll() {
        return credentialDAO.credentialGetAll();
    }

}
