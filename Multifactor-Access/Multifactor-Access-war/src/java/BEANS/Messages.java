/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEANS;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Zacarias_Mercado
 */
@Named(value = "Messages")
@RequestScoped
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;

    public Messages() {
    }

//    GENERAL METHOD TO CREATE MESSAGES
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void acceptForm(String employeeNumber) {
        addMessage(FacesMessage.SEVERITY_INFO, "Empleado aceptado! Se registro el empleado " + employeeNumber + " exitosamente", null);
    }

    public void tagIdExists(String tag) {
        addMessage(FacesMessage.SEVERITY_WARN, "TagID ya " + tag + " existe", null);
    }

    public void phoneNumberExists(String phone) {
        addMessage(FacesMessage.SEVERITY_WARN, "Numero de telefono " + phone + " ya existe", null);
    }

    public void employeeNumberExists(String en) {
        addMessage(FacesMessage.SEVERITY_WARN, "Numero de empleado " + en + " ya existe", null);
    }

    public void emailExists(String email) {
        addMessage(FacesMessage.SEVERITY_WARN, "Email " + email + " ya existe", null);
    }

    public void rfidExists(String rfid) {
        addMessage(FacesMessage.SEVERITY_WARN, "RFID " + rfid + " ya existe", null);
    }

    public void employeeNotExists() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe el empleado", null);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

}
