/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.DAO;

import Foxconn.System.ENTITY.Role;
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
public class DAOrole implements Serializable {

    private static final long serialVersionUID = 1L;

    EntityManager em;

    public DAOrole() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();
    }

    //   -------------------------------------- CRUD--------------------------------
    public boolean roleADD(Role role) {
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
        return true;
    }

    public boolean roleEDIT(Role role) {
        em.getTransaction().begin();
        em.merge(role);
        em.getTransaction().commit();
        return true;
    }

    public boolean roleREMOVE(Role role) {
        em.getTransaction().begin();
        em.remove(role);
        em.getTransaction().commit();
        return true;
    }

    //----------------------------PSQL------------------------------------------
    //  Get all RFID
    public List<Role> roleGetAll() {
        Query q = em.createNamedQuery("Role.findAll");
        q.getResultList();
        return q.getResultList();
    }

    public Role roleGetByID(int id) {
        Query q = em.createNamedQuery("Role.findById");
        q.setParameter("id", id);
        q.getResultList();

        if (q.getResultList() != null) {
            return (Role) q.getResultList().get(0);
        } else {
            JOptionPane.showMessageDialog(null, this, "RoleGetByID FAIL DAO", 0);
            return null;
        }
    }

    public Role roleGetByRole(String role) {
        Query q = em.createNamedQuery("Role.findByRole");
        q.setParameter("role", role);
        q.getResultList();

        if (q.getResultList() != null) {
            return (Role) q.getResultList().get(0);
        } else {
            JOptionPane.showMessageDialog(null, this, "RoleGetByRole FAIL DAO", 0);
            return null;
        }
    }

}
