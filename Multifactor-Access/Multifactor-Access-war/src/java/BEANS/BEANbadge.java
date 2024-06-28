/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package BEANS;

import Foxconn.System.EJB.EJBbadgeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

import javax.ejb.EJB;
import Foxconn.System.ENTITY.Badge;

/**
 *
 * @author Zacarias_Mercado
 */
@Named(value = "BEANbadge")
@SessionScoped
public class BEANbadge implements Serializable {

    @EJB
    private EJBbadgeLocal badgeLocal;
    Badge badge = new Badge();

    public BEANbadge() {
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

    //--------------------------------------CRUD BADGE------------------------------------
//      METODO AGREGAR GAFETE
    public String addBadge() {
        System.out.println("El ID en el BEAN es: " + badge.getId());
        badgeLocal.ADD(badge);  //Se agrega el permiso desde el BEAN
        badge = new Badge();
        System.out.println("Se agrego gafete en el BEAN");
        return tableViewIndex();
    }

    //      METODO EDITAR GAFETE
    public String editBadge() {
        badgeLocal.EDIT(badge);
        badge = new Badge();
        return tableViewIndex();
    }

    //      METODO BORRAR GAFETE    
    public String deleteBadge() {
        badgeLocal.REMOVE(badge);
        badge = new Badge();
        return tableViewIndex();
    }



//--------------------------------------GETTERS & SETTERS-----------------------
    public EJBbadgeLocal getBadgeLocal() {
        return badgeLocal;
    }

    public void setBadgeLocal(EJBbadgeLocal badgeLocal) {
        this.badgeLocal = badgeLocal;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

}
