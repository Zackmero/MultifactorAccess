/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Foxconn.System.DAO;

import Foxconn.System.ENTITY.Access;
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
public class DAOaccess implements Serializable {

    private static final long serialVersionUID = 1L;
    
    EntityManager em;

    public DAOaccess() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();
    }

//   -------------------------------------- CRUD --------------------------------
    // ADD a new access
    public boolean accessADD(Access access) {
        em.getTransaction().begin();
        em.persist(access);
        em.getTransaction().commit();
        return true;
    }

    // EDIT an access
    public boolean accessEDIT(Access access) {
        em.getTransaction().begin();
        em.merge(access);
        em.getTransaction().commit();
        return true;
    }

    // Delete an access
    public boolean accessREMOVE(Access access) {
        em.getTransaction().begin();
        em.remove(access);
        em.getTransaction().commit();
        return true;
    }

//----------------------------PSQL------------------------------------------
    //    Get all access
    public List<Access> accessGetAll() {
        Query q = em.createNamedQuery("Access.findAll");
        q.getResultList();
        return q.getResultList();
    }

    //SEARCH BY ID acess  
    public Access accessGetByID(int id) {
        Query q = em.createNamedQuery("Access.findByIdEmpleado");
        q.setParameter("id", id);
        q.getResultList();

        if (q.getResultList() != null) {
            return (Access) q.getResultList().get(0);
        } else {
            System.out.println("AccessGetID FAIL DAO");
            return null;
        }
    }

    // SEARCH ACCESS BY AREA
    public Access accessGetByArea(String area) {
        Query q = em.createNamedQuery("Access.findByArea");
        q.setParameter("area", area);
        q.getResultList();

        if (q.getResultList() != null) {
            return (Access) q.getResultList().get(0);
        } else {
            JOptionPane.showMessageDialog(null, this, "AccessGetArea FAIL DAO", 0);
            return null;
        }
    }

}
