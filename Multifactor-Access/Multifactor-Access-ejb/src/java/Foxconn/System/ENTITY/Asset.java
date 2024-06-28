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
@Table(name = "tb_asset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asset.findAll", query = "SELECT a FROM Asset a")
    , @NamedQuery(name = "Asset.findById", query = "SELECT a FROM Asset a WHERE a.id = :id")
    , @NamedQuery(name = "Asset.findByAsset", query = "SELECT a FROM Asset a WHERE a.asset = :asset")})
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "asset")
    private String asset;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbAssetId")
    private List<PermissionAsset> permissionAssetList;

    public Asset() {
    }

    public Asset(Integer id) {
        this.id = id;
    }

    public Asset(Integer id, String asset) {
        this.id = id;
        this.asset = asset;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    @XmlTransient
    public List<PermissionAsset> getPermissionAssetList() {
        return permissionAssetList;
    }

    public void setPermissionAssetList(List<PermissionAsset> permissionAssetList) {
        this.permissionAssetList = permissionAssetList;
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
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Foxconn.System.ENTITY.Asset[ id=" + id + " ]";
    }
    
}
