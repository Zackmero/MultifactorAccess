/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.ENTITY;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zacarias_Mercado
 */
@Entity
@Table(name = "tb_access")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Access.findAll", query = "SELECT a FROM Access a")
    , @NamedQuery(name = "Access.findById", query = "SELECT a FROM Access a WHERE a.id = :id")
    , @NamedQuery(name = "Access.findByArea", query = "SELECT a FROM Access a WHERE a.area = :area")})
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "area")
    private String area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbAccessId")
    private List<PermissionAccess> permissionAccessList;

    public Access() {
    }

    public Access(Integer id) {
        this.id = id;
    }

    public Access(Integer id, String area) {
        this.id = id;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @XmlTransient
    public List<PermissionAccess> getPermissionAccessList() {
        return permissionAccessList;
    }

    public void setPermissionAccessList(List<PermissionAccess> permissionAccessList) {
        this.permissionAccessList = permissionAccessList;
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
        if (!(object instanceof Access)) {
            return false;
        }
        Access other = (Access) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Foxconn.System.ENTITY.Access[ id=" + id + " ]";
    }
    
}
