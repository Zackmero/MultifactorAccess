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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")
    , @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name")
    , @NamedQuery(name = "Employee.findByFirstLastName", query = "SELECT e FROM Employee e WHERE e.firstLastName = :firstLastName")
    , @NamedQuery(name = "Employee.findBySecondLastName", query = "SELECT e FROM Employee e WHERE e.secondLastName = :secondLastName")
    , @NamedQuery(name = "Employee.findByEmployeeNumber", query = "SELECT e FROM Employee e WHERE e.employeeNumber = :employeeNumber")
    , @NamedQuery(name = "Employee.findByTagID", query = "SELECT e FROM Employee e WHERE e.tagID = :tagID")
    , @NamedQuery(name = "Employee.findByPhoneNumber", query = "SELECT e FROM Employee e WHERE e.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
    , @NamedQuery(name = "Employee.findByDepartament", query = "SELECT e FROM Employee e WHERE e.departament = :departament")
    , @NamedQuery(name = "Employee.findByPosition", query = "SELECT e FROM Employee e WHERE e.position = :position")
    , @NamedQuery(name = "Employee.findByRfid", query = "SELECT e FROM Employee e WHERE e.rfid = :rfid")})
public class Employee implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbEmployeeId")
    private List<GroupEmployee> groupEmployeeList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbEmployeeId")
    private List<GroupEmployee> tbGroupList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbEmployeeId")
    private List<Credential> tbCredentialList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "firstLastName")
    private String firstLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "secondLastName")
    private String secondLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "employeeNumber")
    private String employeeNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "tagID")
    private String tagID;
    @Size(max = 12)
    @Column(name = "phoneNumber")
    private String phoneNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 70)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "departament")
    private String departament;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "position")
    private String position;
    @Size(max = 10)
    @Column(name = "rfid")
    private String rfid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbEmployeeId")
    private List<Facial> facialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbEmployeeId")
    private List<PermissionAsset> permissionAssetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbEmployeeId")
    private List<Badge> badgeList;
    @JoinColumn(name = "tb_role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role tbRoleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbEmployeeId")
    private List<Rfid> rfidList;

    @Lob
    private byte[] photo;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(Integer id, String name, String firstLastName, String secondLastName, String employeeNumber, String tagID, String departament, String position) {
        this.id = id;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.employeeNumber = employeeNumber;
        this.tagID = tagID;
        this.departament = departament;
        this.position = position;
    }

    public Employee(Integer id, String name, String firstLastName, String secondLastName, String employeeNumber, String tagID, String phoneNumber, String email, String departament, String position, String rfid, Role tbRoleId, byte[] photo) {
        this.id = id;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.employeeNumber = employeeNumber;
        this.tagID = tagID;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.departament = departament;
        this.position = position;
        this.rfid = rfid;
        this.tbRoleId = tbRoleId;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @XmlTransient
    public List<Facial> getFacialList() {
        return facialList;
    }

    public void setFacialList(List<Facial> facialList) {
        this.facialList = facialList;
    }

    @XmlTransient
    public List<PermissionAsset> getPermissionAssetList() {
        return permissionAssetList;
    }

    public void setPermissionAssetList(List<PermissionAsset> permissionAssetList) {
        this.permissionAssetList = permissionAssetList;
    }

    @XmlTransient
    public List<Badge> getBadgeList() {
        return badgeList;
    }

    public void setBadgeList(List<Badge> badgeList) {
        this.badgeList = badgeList;
    }

    public Role getTbRoleId() {
        return tbRoleId;
    }

    public void setTbRoleId(Role tbRoleId) {
        this.tbRoleId = tbRoleId;
    }

    @XmlTransient
    public List<Rfid> getRfidList() {
        return rfidList;
    }

    public void setRfidList(List<Rfid> rfidList) {
        this.rfidList = rfidList;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Foxconn.System.ENTITY.Employee[ id=" + id + " ]";
    }

    @XmlTransient
    public List<GroupEmployee> getTbGroupList() {
        return tbGroupList;
    }

    public void setTbGroupList(List<GroupEmployee> tbGroupList) {
        this.tbGroupList = tbGroupList;
    }

    @XmlTransient
    public List<Credential> getTbCredentialList() {
        return tbCredentialList;
    }

    public void setTbCredentialList(List<Credential> tbCredentialList) {
        this.tbCredentialList = tbCredentialList;
    }

    @XmlTransient
    public List<GroupEmployee> getGroupEmployeeList() {
        return groupEmployeeList;
    }

    public void setGroupEmployeeList(List<GroupEmployee> groupEmployeeList) {
        this.groupEmployeeList = groupEmployeeList;
    }

}
