/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEANS;

import Foxconn.System.EJB.EJBemployeeLocal;
import Foxconn.System.ENTITY.Employee;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Zacarias_Mercado
 */
@Named(value = "Select")
@RequestScoped
public class Select implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private EJBemployeeLocal ejbEmployee;

    @Inject
    private Messages msg;

    @Inject
    private BEANmain bm;

    public Select() {
    }

    private String badgeSelected;
    private String selectionMessage;
    private String filterText;

    public void deselectItem() {
        setBadgeSelected(null);
    }

    public void filterList() {
        String filterValue = badgeSelected;

//        if (!bm.badgeListBEAN().isEmpty()) {
//            setBadgeSelected(bm.badgeListBEAN().get(0).getTagID());
//        }
        if (filterValue != null && !filterValue.isEmpty()) {
            for (Employee badgeItem : bm.badgeListBEAN()) {
                if (badgeItem.getTagID().toLowerCase().contains(filterValue.toLowerCase())) {
                    setBadgeSelected(badgeItem.getTagID());
                    break;
                }
            }
        }

    }

    // GETTERS & SETTERS
    public String getBadgeSelected() {
        return badgeSelected;
    }

    public void setBadgeSelected(String badgeSelected) {
        this.badgeSelected = badgeSelected;
    }

    public String getSelectionMessage() {
        return selectionMessage;
    }

    public void setSelectionMessage(String selectionMessage) {
        this.selectionMessage = selectionMessage;
    }

    public EJBemployeeLocal getEjbEmployee() {
        return ejbEmployee;
    }

    public void setEjbEmployee(EJBemployeeLocal ejbEmployee) {
        this.ejbEmployee = ejbEmployee;
    }

    public Messages getMsg() {
        return msg;
    }

    public void setMsg(Messages msg) {
        this.msg = msg;
    }

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }

}
