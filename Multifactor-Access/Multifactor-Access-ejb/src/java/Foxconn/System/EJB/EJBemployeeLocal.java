/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Foxconn.System.EJB;

import Warnings.Alert;
import Foxconn.System.ENTITY.Employee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zacarias_Mercado
 */
@Local
public interface EJBemployeeLocal {

    Alert ADD(Employee employee);

    Alert EDIT(Employee employee);

    Alert REMOVE(Employee employee);

    Alert searchByID(int id);

    List<Employee> EmployeeList();

    Employee searchByTagId(String tag);
    
    Employee searchByEmployeeNumber(String user);

    boolean validateBadgeEJB(String tag);


}
