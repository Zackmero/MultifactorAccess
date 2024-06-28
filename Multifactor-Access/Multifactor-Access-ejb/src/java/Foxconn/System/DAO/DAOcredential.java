package Foxconn.System.DAO;

import Foxconn.System.ENTITY.Credential;
import Foxconn.System.ENTITY.Employee;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DAOcredential implements Serializable {

    private static final long serialVersionUID = 1L;

    EntityManager em;

    public DAOcredential() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multifactor-Access-ejbPU");
        em = emf.createEntityManager();
    }

    //   -------------------------------------- CRUD --------------------------------
    // ADD a new credencial
    public boolean credentialADD(Credential credential) {
        em.getTransaction().begin();
        em.persist(credential);
        em.getTransaction().commit();
        return true;
    }

    // EDIT a credencial
    public boolean credentialEDIT(Credential credential) {
        em.getTransaction().begin();
        em.merge(credential);
        em.getTransaction().commit();
        return true;
    }

    // Delete a credential
    public boolean credentialREMOVE(Credential credential) {
        em.getTransaction().begin();
        em.remove(credential);
        em.getTransaction().commit();
        return true;
    }

    //----------------------------PSQL------------------------------------------
    //    Get all credentials
    public List<Credential> credentialGetAll() {
        Query q = em.createNamedQuery("Credential.findAll");
        q.getResultList();
        return q.getResultList();
    }

    //SEARCH BY ID CREDENTIAL
    public Credential credentialGetByID(int id) {
        Query q = em.createNamedQuery("Credential.findById");
        q.setParameter("id", id);
        q.getResultList();

        if (q.getResultList() != null) {
            return (Credential) q.getResultList().get(0);
        } else {
            System.out.println("AccessGetID FAIL DAO");
            return null;
        }
    }

    //SEARCH USER BY CREDENTIAL
    public Credential credentialGetByUser(String user) {
        //Query tagUser = em.createNativeQuery("SELECT e.tagID FROM tb_credential c join tb_employee e ON c.tb_employee_id = e.id;"); //Return TagID from Employee

        Query findEmployee = em.createQuery("SELECT e FROM Employee e WHERE e.employeeNumber = :user");
        findEmployee.setParameter("user", user);
        findEmployee.getResultList();

        List<Employee> empFirstResult = findEmployee.getResultList();

        if (findEmployee.getResultList() != null) {

            int idEmployee = empFirstResult.get(0).getId();

            return credentialGetByID(idEmployee);
        } else {
            System.out.println("Error, credential not found");
            return null;
        }

//        Query findUser = em.createNamedQuery("Credential.findByUser");  //Return User search by TagID
//        tagUser.setParameter("id", id)
//                
//        String tagUserResult = tagUser.getResultList().get(0).toString();
//        findUser.setParameter("tagID", tagUserResult);
//        findUser.getResultList();
//
//        System.out.println(tagUserResult);  //Print username tag result of consult
//
//        if (tagUser.getResultList() != null) {
//            return (Credential) findUser.getResultList().get(0);
//        } else {
//            System.out.println("Query to get user from credential fail");
//            return null;
//        }
    }
    //SEARCH CREDENTIALS BY PASSWORD

    public List<Credential> credentialListByPassword(String pass) {
        Query q = em.createNamedQuery("Credential.findByPassword");
        q.setParameter("password", pass);
        return q.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
