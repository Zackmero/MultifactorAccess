package BEANview;

import Foxconn.System.EJB.EJBcredentialLocal;
import Foxconn.System.ENTITY.Credential;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "BEANcredentialView")
@RequestScoped
public class BEANcredentialView {

    //--------------------
    // INSTANCES   |
    //--------------------
    @EJB
    private EJBcredentialLocal ejbCredential;

    public BEANcredentialView() {
    }

    //--------------------
    // TABLE VIEW   |
    //--------------------
    public List<Credential> credentialListBEAN() {
        return ejbCredential.credentialGetAll();
    }

    //---------------------------------
    // GETTERS & SETTERS   |
    //---------------------------------
    public EJBcredentialLocal getEjbCredential() {
        return ejbCredential;
    }

    public void setEjbCredential(EJBcredentialLocal ejbCredential) {
        this.ejbCredential = ejbCredential;
    }

}
