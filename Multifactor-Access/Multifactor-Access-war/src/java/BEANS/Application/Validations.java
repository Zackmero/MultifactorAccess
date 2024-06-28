package BEANS.Application;

import Foxconn.System.ENTITY.Employee;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

@Named(value = "Validations")
@ApplicationScoped
public class Validations {

    public Validations() {
    }

    //------------------------------------
    //      Variables Generales      |
    //------------------------------------
    private boolean resultEmployeeForm;     // validate to show/hide modal form Register Employee

    private String userBean;
    private Employee employeeBean;

    //-----------------------
    //      Instancias      |
    //-----------------------
    //-----------------------
    //      Methods        |
    //-----------------------
    public void userEmployee(String user, Employee e) {
        userBean=user;
        employeeBean=e;
    }

    //--------------------------------
    //      Getters & Setters      |
    //--------------------------------
    public String getUserBean() {
        return userBean;
    }

    public void setUserBean(String user) {
        this.userBean = user;
    }

    public boolean isResultEmployeeForm() {
        return resultEmployeeForm;
    }

    public void setResultEmployeeForm(boolean resultEmployeeForm) {
        this.resultEmployeeForm = resultEmployeeForm;
    }

    public Employee getEmployeeBean() {
        return employeeBean;
    }

    public void setEmployeeBean(Employee employeeBean) {
        this.employeeBean = employeeBean;
    }

}
