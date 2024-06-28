/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Foxconn.System.EJB;

import Warnings.Alert;
import Foxconn.System.DAO.DAOemployee;

import Foxconn.System.ENTITY.Employee;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Zacarias_Mercado
 */
@Stateless
public class EJBemployee implements EJBemployeeLocal {

    DAOemployee employeeDAO = new DAOemployee();

    @Override
    public Alert ADD(Employee employee) {
        employeeDAO.employeeADD(employee); //Conectarse con el DAO_Empleado metodo EmpleadoAgregar
        return Alert.OK;
    }

    @Override
    public Alert EDIT(Employee employee) {
        employeeDAO.employeeEDIT(employee); //Conectarse con el DAO_Empleado metodo EmpleadoEditar
        return Alert.OK;
    }

    @Override
    public Alert REMOVE(Employee employee) {
        employeeDAO.employeeREMOVE(employee); //Conectarse con el DAO_Empleado metodo EmpleadoElimnar
        return Alert.OK;
    }

    @Override
    public Alert searchByID(int id) {
        employeeDAO.employeeGetById(id); //Conectarse con el DAO_Empleado metodo EmpleadoGetPorID
        return Alert.OK;
    }

    @Override
    public Employee searchByTagId(String tag) {
        try {
            return employeeDAO.employeeGetByTagId(tag);
        } catch (Exception e) {
            System.out.println("Error EJB Employee: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Employee searchByEmployeeNumber(String user) {
        return employeeDAO.employeeGetByEmployeeNumber(user);
    }

    @Override
    public List<Employee> EmployeeList() {
        return employeeDAO.employeeListDAO();
    }

    @Override
    public boolean validateBadgeEJB(String tag) {
        return employeeDAO.validateBadgeDAO(tag);
    }

   

    
    
    
}
