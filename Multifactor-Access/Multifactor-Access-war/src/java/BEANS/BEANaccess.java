/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package BEANS;

import Foxconn.System.EJB.EJBaccessLocal;
import Foxconn.System.ENTITY.Access;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Zacarias_Mercado
 */
@Named(value = "BEANaccess")
@RequestScoped
public class BEANaccess {

    /**
     * Creates a new instance of BEANaccess
     */
    public BEANaccess() {
    }

    @EJB
    private EJBaccessLocal accessEJB;
    private boolean showAccess = false;
    private boolean showBadge = false;
    private boolean showRFID = false;

    private boolean showDeleteAccess = false;
    private boolean showDeleteBadge = false;
    private boolean showDeleteRFID = false;

    Access access = new Access();

    private String tittle = "Nuevo Acceso";
    private String imgTop;

    //-------------------------------------LINKS------------------------------------
    public String badgeIndex() {
        return "/Templates/Badge/BadgeIndex.xhtml";
    }

    public String badgeForm() {
        return "/Templates/Badge/BadgeForm.xhtml";
    }

    public String facialIndex() {
        return "/Templates/Facial/FacialIndex.xhtml";
    }

    public String facialForm() {
        return "/Templates/Facial/FacialForm.xhtml";
    }

    public String rfidIndex() {
        return "/Templates/RFID/RfidIndex.xhtml";
    }

    public String rfidForm() {
        return "/Templates/RFID/RfidForm.xhtml";
    }

    public String index() {
        return "/index.xhtml";
    }

    //--------------------------------------PrepareTo------------------------------------
    //      Preparar un nuevo gafete
    public void prepareNewAccess() {
        tittle = "Nuevo Acceso";

        showAccess = true;
        showBadge = false;
        showRFID = false;

        showDeleteAccess = false;
        showDeleteBadge = false;
        showDeleteRFID = false;

        access = new Access();
    }

    //      Preparar para editar un Gafete
    public void accessPrepareEdit(Access a) {
        System.out.println("El id en preparar a Editar es: " + a);
        access = a;
    }

    //      Preparar para eliminar un Gafete
    public void accessPrepareRemove(Access a) {
        System.out.println("El id en preparar a eliminar es: " + a);
        access = a;
    }

    //--------------------------------------CRUD------------------------------------
    //      METHOD ADD ACCESS
    public String add() {
        accessEJB.ADD(access);  //Se agrega el permiso desde el BEAN
        access = new Access();
        return badgeIndex();
    }

    //      METHOD EDIT ACCESS
    public String edit() {
        accessEJB.EDIT(access);
        access = new Access();
        return badgeIndex();
    }

    //      METHOD DELETE ACCESS
    public String delete() {
        accessEJB.REMOVE(access);
        access = new Access();
        return badgeIndex();
    }

    //      METHOD GET ALL ACCESS
    public List<Access> accessListBEAN() {
        return accessEJB.accessList();
    }

//--------------------------------------GETTERS & SETTERS-----------------------    
    public EJBaccessLocal getAccessEJB() {
        return accessEJB;
    }

    public void setAccessEJB(EJBaccessLocal accessEJB) {
        this.accessEJB = accessEJB;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getImgTop() {
        return imgTop;
    }

    public void setImgTop(String imgTop) {
        this.imgTop = imgTop;
    }

    public boolean isShowAccess() {
        return showAccess;
    }

    public void setShowAccess(boolean showAccess) {
        this.showAccess = showAccess;
    }

    public boolean isShowBadge() {
        return showBadge;
    }

    public void setShowBadge(boolean showBadge) {
        this.showBadge = showBadge;
    }

    public boolean isShowRFID() {
        return showRFID;
    }

    public void setShowRFID(boolean showRFID) {
        this.showRFID = showRFID;
    }

    public boolean isShowDeleteAccess() {
        return showDeleteAccess;
    }

    public void setShowDeleteAccess(boolean showDeleteAccess) {
        this.showDeleteAccess = showDeleteAccess;
    }

    public boolean isShowDeleteBadge() {
        return showDeleteBadge;
    }

    public void setShowDeleteBadge(boolean showDeleteBadge) {
        this.showDeleteBadge = showDeleteBadge;
    }

    public boolean isShowDeleteRFID() {
        return showDeleteRFID;
    }

    public void setShowDeleteRFID(boolean showDeleteRFID) {
        this.showDeleteRFID = showDeleteRFID;
    }

    
}
