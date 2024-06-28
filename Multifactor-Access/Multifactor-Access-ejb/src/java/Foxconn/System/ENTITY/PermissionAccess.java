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
@Table(name = "tb_permission_access")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermissionAccess.findAll", query = "SELECT p FROM PermissionAccess p")
    , @NamedQuery(name = "PermissionAccess.findById", query = "SELECT p FROM PermissionAccess p WHERE p.id = :id")})
public class PermissionAccess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "tb_access_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Access tbAccessId;
    @JoinColumn(name = "tb_role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role tbRoleId;

    public PermissionAccess() {
    }

    public PermissionAccess(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Access getTbAccessId() {
        return tbAccessId;
    }

    public void setTbAccessId(Access tbAccessId) {
        this.tbAccessId = tbAccessId;
    }

    public Role getTbRoleId() {
        return tbRoleId;
    }

    public void setTbRoleId(Role tbRoleId) {
        this.tbRoleId = tbRoleId;
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
        if (!(object instanceof PermissionAccess)) {
            return false;
        }
        PermissionAccess other = (PermissionAccess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Foxconn.System.ENTITY.PermissionAccess[ id=" + id + " ]";
    }
    
}
