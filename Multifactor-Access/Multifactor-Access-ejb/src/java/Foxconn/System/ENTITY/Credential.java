/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.ENTITY;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Entity
@Table(name = "tb_credential")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
    , @NamedQuery(name = "Credential.findById", query = "SELECT c FROM Credential c WHERE c.id = :id")
    , @NamedQuery(name = "Credential.findByUser", query = "SELECT c FROM Credential c WHERE c.tbEmployeeId.tagID = :tagID")
    , @NamedQuery(name = "Credential.findByPassword", query = "SELECT c FROM Credential c WHERE c.password = :password")

})

public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "password")
    private String password;

    @JoinColumn(name = "tb_employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee tbEmployeeId;

    public Credential() {
    }

    public Credential(Integer id) {
        this.id = id;
    }

    public Credential(Integer id, Employee employee, String password) {
        this.id = id;
        this.tbEmployeeId = employee;
        this.password = password;
    }

    public Credential(Integer id, String password) {
        this.id = id;
        this.password = password;
    }
    
    public Credential(String user, String password) {
        this.tbEmployeeId.setTagID(user);
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getTbEmployeeId() {
        return tbEmployeeId;
    }

    public void setTbEmployeeId(Employee tbEmployeeId) {
        this.tbEmployeeId = tbEmployeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Foxconn.System.ENTITY.Credential[ id=" + id + " ]";
    }

}
