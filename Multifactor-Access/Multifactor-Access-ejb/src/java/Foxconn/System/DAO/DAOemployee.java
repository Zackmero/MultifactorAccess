/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Foxconn.System.DAO;

import Foxconn.System.ENTITY.Employee;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Zacarias_Mercado
 */
public class DAOemployee implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EntityManager em;

    public DAOemployee() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();

    }

//    CRUD----------------------------------------------------------------------
    public boolean employeeADD(Employee employee) {

        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        return true;
    }

    public boolean employeeEDIT(Employee employee) {
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
        return true;
    }

    public boolean employeeREMOVE(Employee employee) {
        em.getTransaction().begin();
        em.remove(employee);
        em.getTransaction().commit();
        return true;
    }

//    PSQL----------------------------------------------------------------------
    //   GET ALL EMPLOYEES
    public List<Employee> employeeListDAO() {
        Query q = em.createNamedQuery("Employee.findAll");
        q.getResultList();
        return q.getResultList();
    }

//    SEARCH EMPLOYEE BY ID
    public Employee employeeGetById(int id) {
        Query q = em.createNamedQuery("Employee.findById");
        q.setParameter("id", id);
        q.getResultList();

        if (q.getResultList() != null) {
            return (Employee) q.getResultList().get(0);
        } else {
            JOptionPane.showMessageDialog(null, this, "EmpleadoGetID FAIL DAO", 0);
            return null;
        }
    }

//  SEARCH EMPLOYEE BY TAGID
    public Employee employeeGetByTagId(String tag) {
        Query q = em.createNamedQuery("Employee.findByTagID");
        q.setParameter("tagID", tag);
        q.getResultList();
        System.out.println(q.getResultList().get(0));
        if (q.getResultList().get(0) == null || q.getResultList().isEmpty() || q.getResultList().get(0).equals("")) {
            System.out.println("EmpleadoGetTagID FAIL DAO");
            return null;
        } else {
            return (Employee) q.getResultList().get(0);
        }
    }

    //  SEARCH EMPLOYEE BY EMPLOYEE NUMBER
    public Employee employeeGetByEmployeeNumber(String user) {
        Query q = em.createNamedQuery("Employee.findByEmployeeNumber");
        q.setParameter("employeeNumber", user);
        q.getResultList();
        System.out.println("Employee result dao: " + q.getResultList().get(0));
        if (q.getResultList().get(0) == null || q.getResultList().isEmpty() || q.getResultList().get(0).equals("")) {
            System.out.println("EmpleadoGetTagID FAIL DAO");
            return null;
        } else {
            return (Employee) q.getResultList().get(0);
        }
    }

    //VALIDATE EMPLOYEE BY USER BY TAG ID
    public boolean validateBadgeDAO(String tag) {
        Query q = em.createNamedQuery("Employee.findByTagID");
        q.setParameter("tagID", tag);
        q.getResultList();
        if (q.getResultList().get(0) == null || q.getResultList().isEmpty() || q.getResultList().get(0).equals("")) {
            System.out.println("EmpleadoGetTagID FAIL DAO");
            return false;
        } else {
            System.out.println("Empleado encontrado: " + q.getResultList().get(0));
            return true;
        }
    }
}
