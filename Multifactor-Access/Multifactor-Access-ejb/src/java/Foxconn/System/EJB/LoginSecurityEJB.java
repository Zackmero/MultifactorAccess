package Foxconn.System.EJB;

import Foxconn.System.DAO.DAOemployee;
import javax.annotation.security.DeclareRoles;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
@DeclareRoles({"admin", "user"})
public class LoginSecurityEJB {

    DAOemployee employeeDAO = new DAOemployee();
    
    public String success() {
        System.out.println("entro al ejb del login");
        return "Bienvenido al sistema de seguridad!";
    }

  
}
