package Foxconn.System.DAO;

import Foxconn.System.ENTITY.GroupEmployee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DAOgroupEmployee {

    private static final long serialVersionUID = 1L;

    EntityManager em;

    public DAOgroupEmployee() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();
    }

    //   -------------------------------------- CRUD --------------------------------
    // ADD a new group
    public boolean groupADD(GroupEmployee group) {
        em.getTransaction().begin();
        em.persist(group);
        em.getTransaction().commit();
        return true;
    }

    // EDIT an group
    public boolean groupEDIT(GroupEmployee group) {
        em.getTransaction().begin();
        em.merge(group);
        em.getTransaction().commit();
        return true;
    }

    // Delete an group
    public boolean groupREMOVE(GroupEmployee group) {
        em.getTransaction().begin();
        em.remove(group);
        em.getTransaction().commit();
        return true;
    }

//----------------------------PSQL------------------------------------------
    //    Get all group
    public List<GroupEmployee> groupGetAll() {
        Query q = em.createNamedQuery("GroupEmployee.findAll");
        q.getResultList();
        return q.getResultList();
    }

    //SEARCH BY ID GROUP EMPLOYEE  
    public GroupEmployee groupGetByID(int id) {
        Query q = em.createNamedQuery("GroupEmployee.findByIdEmpleado");
        q.setParameter("id", id);
        q.getResultList();

        if (q.getResultList() != null) {
            return (GroupEmployee) q.getResultList().get(0);
        } else {
            System.out.println("GroupEmployeeGetID FAIL DAO");
            return null;
        }
    }

    // SEARCH GROUP BY GROUP
    public GroupEmployee groupGetByGroup(String group) {
        Query q = em.createNamedQuery("GroupEmployee.findByGroupName");
        q.setParameter("groupName", group);
        q.getResultList();

        if (q.getResultList() != null) {
            return (GroupEmployee) q.getResultList().get(0);
        } else {
            System.out.println("GroupEmployeeGetGroupName FAIL DAO");
            return null;
        }
    }

}
