/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Foxconn.System.DAO;

import Foxconn.System.ENTITY.Facial;
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
public class DAOfacial implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EntityManager em;

    public DAOfacial() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();

    }

    //Add new Facial by DAO
    public boolean FacialADD(Facial facial) {
        em.getTransaction().begin();
        em.persist(facial);
        em.getTransaction().commit();
        return true;
    }

    //Edit Facial by DAO
    public boolean FacialEDIT(Facial facial) {
        em.getTransaction().begin();
        em.merge(facial);
        em.getTransaction().commit();
        return true;
    }

    //Remove Facial by DAO
    public boolean FacialREMOVE(Facial facial) {
        em.getTransaction().begin();
        em.remove(facial);
        em.getTransaction().commit();
        return true;
    }

    //Get list with all user Facial
    public List<Facial> FacialListDAO() {
        Query q = em.createNamedQuery("Facial.findAll");
        return q.getResultList();
    }

    //Search by ID one Facial user
    public Facial FacialSearchByID(int id) {
        Query q = em.createNamedQuery("Facial.findById");
        q.setParameter("id", id);
        q.getResultList();
        if (q.getResultList() != null) {
            return (Facial) q.getResultList().get(0);
        } else {
            JOptionPane.showMessageDialog(null, this, "Facial GetID FAIL DAO", 0);
            return null;
        }
    }

}
