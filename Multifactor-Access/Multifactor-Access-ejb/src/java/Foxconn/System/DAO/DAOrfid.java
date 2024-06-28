/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Foxconn.System.DAO;

import Foxconn.System.ENTITY.Rfid;
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
public class DAOrfid implements Serializable {

    private static final long serialVersionUID = 1L;

    EntityManager em;

    public DAOrfid() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();
    }

//   -------------------------------------- CRUD--------------------------------
    public boolean rfidADD(Rfid rfid) {
        em.getTransaction().begin();
        em.persist(rfid);
        em.getTransaction().commit();
        return true;
    }

    public boolean rfidEDIT(Rfid rfid) {
        em.getTransaction().begin();
        em.merge(rfid);
        em.getTransaction().commit();
        return true;
    }

    public boolean rfidREMOVE(Rfid rfid) {
        em.getTransaction().begin();
        em.remove(rfid);
        em.getTransaction().commit();
        return true;
    }

//----------------------------PSQL------------------------------------------
    //  Get all RFID
    public List<Rfid> rfidGetAll() {
        Query q = em.createNamedQuery("Rfid.findAll");
        q.getResultList();
        return q.getResultList();
    }

    public Rfid rfidGetByID(int id) {
        Query q = em.createNamedQuery("Rfid.findById");
        q.setParameter("id", id);
        q.getResultList();

        if (q.getResultList() != null) {
            return (Rfid) q.getResultList().get(0);
        } else {
            JOptionPane.showMessageDialog(null, this, "RfidGetByID FAIL DAO", 0);
            return null;
        }
    }

}
