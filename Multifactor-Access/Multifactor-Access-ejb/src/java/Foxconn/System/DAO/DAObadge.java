package Foxconn.System.DAO;

import Foxconn.System.ENTITY.Badge;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Zacarias_Mercado
 */
public class DAObadge implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EntityManager em;

    public DAObadge() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();

    }

//------------------------------------CRUD--------------------------------------
    //Agregar nuevo Gafete por el DAO
    public boolean BadgeADD(Badge badge) {
        em.getTransaction().begin();
        em.persist(badge);
        em.getTransaction().commit();
        return true;
    }

    //Editar Gafete por el DAO
    public boolean BadgeEDIT(Badge badge) {
        em.getTransaction().begin();
        em.merge(badge);
        em.getTransaction().commit();
        return true;
    }

    //Eliminar Gafete por el DAO
    public boolean BadgeREMOVE(Badge badge) {
        em.getTransaction().begin();
        em.remove(badge);
        em.getTransaction().commit();
        return true;
    }

//------------------------------PSQL--------------------------------------------
    //Obtener lista de todos los gafete
    public List<Badge> BadgeListDAO() {

        Query q = em.createNamedQuery("Badge.findAll");

        return q.getResultList();
    }

    //Buscar por ID un gafete
    public Badge BadgeSearchByID(int id) {
        Query q = em.createNamedQuery("Badge.findById");
        q.setParameter("id", id);
        q.getResultList();

        if (q.getResultList() != null) {
            return (Badge) q.getResultList().get(0);
        } else {
            JOptionPane.showMessageDialog(null, this, "Gafete GetID FAIL DAO", 0);
            return null;
        }
    }

    //  METHOD TO VALIDATE BADGE
    public boolean badgeValidationDAO(String data) {
        // CREATE QUERY TO GET TAG ID OF EMPLOYEE BY TAGID IN THE DATABASE
        Query q = em.createNamedQuery("Employee.findByTagID");

        // SET PARAMETER TO GET DATA OF DATABASE
        q.setParameter("tagID", data);

        // GET RESULT OF CONSULT
        q.getResultList();

        // IF THE RESULT IS EMPTY, GIVE FALSE, OTHERWISE GIVE TRUE
        return !q.getResultList().isEmpty();
    }
}
