package BEANS.SESSION;

import Foxconn.System.ENTITY.Employee;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "SessionBeanMethods")
@SessionScoped
public class SessionBeanMethods implements Serializable {

    Employee empoyeeFound;

    public void saveUser(Employee e) {
        setEmpoyeeFound(e);
    }

    public SessionBeanMethods() {
    }

    public Employee getEmpoyeeFound() {
        return empoyeeFound;
    }

    public void setEmpoyeeFound(Employee empoyeeFound) {
        this.empoyeeFound = empoyeeFound;
    }

}
