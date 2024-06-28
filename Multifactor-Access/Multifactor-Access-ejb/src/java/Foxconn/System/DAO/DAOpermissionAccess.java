/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Foxconn.System.DAO;

import Foxconn.System.ENTITY.Access;
import Foxconn.System.ENTITY.PermissionAccess;
import Foxconn.System.ENTITY.Role;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Zacarias_Mercado
 */
public class DAOpermissionAccess implements Serializable {

    private static final long serialVersionUID = 1L;

    EntityManager em;

    public DAOpermissionAccess() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();
    }

    //-------------------------------------- CRUD--------------------------------
    // ADD a new permission
    public boolean permissionAccessADD(PermissionAccess permission) {
        em.getTransaction().begin();
        em.persist(permission);
        em.getTransaction().commit();
        return true;
    }

    // EDIT a permission
    public boolean permissionAccessEDIT(PermissionAccess permission) {
        em.getTransaction().begin();
        em.merge(permission);
        em.getTransaction().commit();
        return true;
    }

    // DELETE a permission
    public boolean permissionAccessREMOVE(PermissionAccess permission) {
        em.getTransaction().begin();
        em.remove(permission);
        em.getTransaction().commit();
        return true;
    }

    // GET all permissions
    public List<PermissionAccess> permissionAccessGetAll() {
        Query q = em.createNamedQuery("PermissionAccess.findAll");
        q.getResultList();
        return q.getResultList();
    }

    // GET ALL ACCESS BY ROLE
    public List<String> getAllAccessByRole(Role rol) {

        Query q = em.createNamedQuery("PermissionAccess.findAll");
        List<PermissionAccess> listPermissionAccess = q.getResultList();
        List<String> resultListAccessByRole = new ArrayList<>();

        for (int i = 0; i < listPermissionAccess.size() - 1; i++) {

            if (Objects.equals(rol.getId(), listPermissionAccess.get(i).getTbRoleId().getId())) {
                resultListAccessByRole.add(listPermissionAccess.get(i).getTbAccessId().getArea());
            }
        }
        System.out.println(resultListAccessByRole);
        return resultListAccessByRole;
    }

    //SEARCH BY ID permission
    public PermissionAccess permissionAccessGetByID(int id) {
        Query q = em.createNamedQuery("PermissionAccess.findById");
        q.setParameter("id", id);
        q.getResultList();

        if (q.getResultList() != null) {
            return (PermissionAccess) q.getResultList().get(0);
        } else {
            JOptionPane.showMessageDialog(null, this, "PermisionAccessGetID FAIL DAO", 0);
            return null;
        }

    }
}
