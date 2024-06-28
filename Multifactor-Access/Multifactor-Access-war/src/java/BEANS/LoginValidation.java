package BEANS;

import Foxconn.System.EJB.EJBcredentialLocal;
import Foxconn.System.EJB.EJBemployeeLocal;
import Foxconn.System.ENTITY.Employee;
import javax.ejb.EJB;
import javax.inject.Named;
import BEANS.Application.Validations;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "LoginValidation")
@SessionScoped
public class LoginValidation implements Serializable {

    private static final long serialVersionUID = 1L;

    //------------------------------------
    //           Constructor               |
    //------------------------------------
    public LoginValidation() {
    }

    //------------------------------------
    //      Variables Generales      |
    //------------------------------------
    Employee currentEmployeeSession;

    //-----------------------
    //      Instancias      |
    //-----------------------
    @EJB
    private EJBcredentialLocal credentialEJB;

    @EJB
    private EJBemployeeLocal employeeEJB;

    Validations v = new Validations();
    BEANmain bm = new BEANmain();

    public boolean validateLogin(String user, String password) {
        System.out.println("User: " + user + " Pass: " + password + "  in LoginValidation");
        return credentialEJB.credentialGetByUser(user, password);
    }

    public void redirectLogin(String user, String password) {

        //System.out.println("User: " + user + "\n Pass: " + password + "LoginValidation");
        if (validateLogin(user, password) == true && v.isResultEmployeeForm() == true) {

            Employee e = employeeEJB.searchByEmployeeNumber(user);

            v.userEmployee(user, e);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpSession session = (HttpSession) externalContext.getSession(true);
            session.setAttribute("userEmployee", e);
        }
    }

    public String redirectLoginToIndex(String user, String password, Employee e) {

        if (e.getTbRoleId().getRole().equals("Administrador")) {
            currentEmployeeSession = e;
            return "/index.xhtml";
        } else if (!e.getTbRoleId().getRole().equals("Administrador")) {
            currentEmployeeSession = e;
            return "/indexUser.xhtml";
        } else {
            return "/login.xhtml";
        }
    }

//--------------------------------
//      Getters & Setters      |
//--------------------------------
    public EJBcredentialLocal getCredentialEJB() {
        return credentialEJB;
    }

    public void setCredentialEJB(EJBcredentialLocal credentialEJB) {
        this.credentialEJB = credentialEJB;
    }

    public EJBemployeeLocal getEmployeeEJB() {
        return employeeEJB;
    }

    public void setEmployeeEJB(EJBemployeeLocal employeeEJB) {
        this.employeeEJB = employeeEJB;
    }

    public Validations getV() {
        return v;
    }

    public void setV(Validations v) {
        this.v = v;
    }

    public BEANmain getBm() {
        return bm;
    }

    public void setBm(BEANmain bm) {
        this.bm = bm;
    }

    public Employee getCurrentEmployeeSession() {
        return currentEmployeeSession;
    }

    public void setCurrentEmployeeSession(Employee currentEmployeeSession) {
        this.currentEmployeeSession = currentEmployeeSession;
    }

}
