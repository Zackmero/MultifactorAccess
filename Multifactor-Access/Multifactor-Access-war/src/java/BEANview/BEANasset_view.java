/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package BEANview;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DialogFrameworkOptions;

/**
 *
 * @author Zacarias_Mercado
 */
@Named(value = "BEANasset_view")
@RequestScoped
public class BEANasset_view {

    /**
     * Creates a new instance of BEANasset_view
     */
    public BEANasset_view() {
    }

    public void viewProductsCustomized() {
        DialogFrameworkOptions options = DialogFrameworkOptions.builder()
                .modal(true)
                .width("640")
                .height("340")
                .contentHeight("100%")
                .contentWidth("100%")
                .headerElement("customheader")
                .build();

        PrimeFaces.current().dialog().openDynamic("viewProducts", options, null);
    }

}
