/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package BEANS;

import Foxconn.System.EJB.EJBemployeeLocal;
import Foxconn.System.ENTITY.Employee;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Zacarias_Mercado
 */
@Named(value = "BEANemployee")
@SessionScoped
public class BEANemployee implements Serializable {

    @EJB
    private EJBemployeeLocal EJBemployee;
    private Employee employee = new Employee();
    
    
    public BEANemployee() {
    }
    
//-------------------------------------LINKS------------------------------------



    
//--------------------------------------CRUD------------------------------------

    
       public List<Employee> employeeListBEAN() {
       
        return EJBemployee.EmployeeList();
        
    }
    
    
    
    
//------------------------GETTERS & SETTERS------------------------------------   
    
    
    public EJBemployeeLocal geteJBemployee() {
        return EJBemployee;
    }

    public void seteJBemployee(EJBemployeeLocal EJBemployee) {
        this.EJBemployee = EJBemployee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EJBemployeeLocal getEJBemployee() {
        return EJBemployee;
    }

    public void setEJBemployee(EJBemployeeLocal EJBemployee) {
        this.EJBemployee = EJBemployee;
    }
    
    
    
    
}
