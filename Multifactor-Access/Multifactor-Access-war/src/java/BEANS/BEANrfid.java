/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package BEANS;

import Foxconn.System.EJB.EJBrfidLocal;
import Foxconn.System.ENTITY.Rfid;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Zacarias_Mercado
 */
@Named(value = "BEANrfid")
@RequestScoped
public class BEANrfid {

    @EJB
    private EJBrfidLocal rfidEJB;

    Rfid rfid = new Rfid();

    BEANmain bm = new BEANmain();

    private String tittle;
    private String imgTop;
    private String navegationBar;

    private boolean showAccess;
    private boolean showBadge;
    private boolean showRFID;

    private boolean showDeleteAccess;
    private boolean showDeleteBadge;
    private boolean showDeleteRFID;

    public BEANrfid() {
    }

//-------------------------------------LINKS------------------------------------
    public String tableViewIndex() {
        return "/Templates/TableView/TableViewIndex.xhtml";
    }

    public String formViewIndex() {
        return "/Templates/FormView/FormViewIndex.xhtml";
    }

    public String index() {
        return "/index.xhtml";
    }

//--------------------------------------RFID PrepareTo------------------------------------
    //      Preparar un nuevo registro
    public void prepareNewRFID() {
        tittle = "Nuevo RFID";
        imgTop = "img/chip_rfid.png";

        rfid = new Rfid();
    }

    //      Preparar para editar registro
    public void prepareEditRFID(Rfid r) {

        tittle = "Editar RFID";
        imgTop = "img/chip_rfid.png";

        rfid = r;
    }

    //      Preparar para eliminar registro
    public void prepareDeleteRFID(Rfid r) {

        tittle = "Eliminar rfid";
        imgTop = "img/chip_rfid.png";

        rfid = r;
    }
//--------------------------------------CRUD RFID------------------------------------
//      METHOD ADD RFID

    public String addRFID() {
        rfidEJB.ADD(rfid);
        rfid = new Rfid();
        return tableViewIndex();
    }

//      METHOD EDIT RFID   
    public String editRFID() {
        rfidEJB.EDIT(rfid);
        rfid = new Rfid();
        return tableViewIndex();
    }

//      METHOD REMOVE RFID   
    public String deleteRFID() {
        rfidEJB.REMOVE(rfid);
        rfid = new Rfid();
        return tableViewIndex();
    }

//      METHOD GET ALL RFID   
    public List<Rfid> rfidListBEAN() {
        tittle = "Identificacion por RFID";
        imgTop = "img/chip_rfid.png";

        navegationBar = "Lista RFID";

        showAccess = false;
        showBadge = false;
        showRFID = true;

        showDeleteAccess = false;
        showDeleteBadge = false;
        showDeleteRFID = false;

        return rfidEJB.rfidList();
    }

//--------------------------------------GETTERS & SETTERS-----------------------    
    public EJBrfidLocal getRfidEJB() {
        return rfidEJB;
    }

    public void setRfidEJB(EJBrfidLocal rfidEJB) {
        this.rfidEJB = rfidEJB;
    }

    public Rfid getRfid() {
        return rfid;
    }

    public void setRfid(Rfid rfid) {
        this.rfid = rfid;
    }

    public BEANmain getBm() {
        return bm;
    }

    public void setBm(BEANmain bm) {
        this.bm = bm;
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

    public String getNavegationBar() {
        return navegationBar;
    }

    public void setNavegationBar(String navegationBar) {
        this.navegationBar = navegationBar;
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
