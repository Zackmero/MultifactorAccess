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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zacarias_Mercado
 */
@Entity
@Table(name = "tb_rfid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rfid.findAll", query = "SELECT r FROM Rfid r")
    , @NamedQuery(name = "Rfid.findById", query = "SELECT r FROM Rfid r WHERE r.id = :id")})
public class Rfid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "tb_employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee tbEmployeeId;

    public Rfid() {
    }

    public Rfid(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Rfid)) {
            return false;
        }
        Rfid other = (Rfid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Foxconn.System.ENTITY.Rfid[ id=" + id + " ]";
    }
    
}
