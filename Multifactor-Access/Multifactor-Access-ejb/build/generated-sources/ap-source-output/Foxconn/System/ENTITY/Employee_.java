package Foxconn.System.ENTITY;

import Foxconn.System.ENTITY.Badge;
import Foxconn.System.ENTITY.Credential;
import Foxconn.System.ENTITY.Facial;
import Foxconn.System.ENTITY.GroupEmployee;
import Foxconn.System.ENTITY.PermissionAsset;
import Foxconn.System.ENTITY.Rfid;
import Foxconn.System.ENTITY.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-04T15:28:27")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> secondLastName;
    public static volatile ListAttribute<Employee, PermissionAsset> permissionAssetList;
    public static volatile SingularAttribute<Employee, String> tagID;
    public static volatile ListAttribute<Employee, Facial> facialList;
    public static volatile ListAttribute<Employee, GroupEmployee> tbGroupList;
    public static volatile ListAttribute<Employee, GroupEmployee> groupEmployeeList;
    public static volatile SingularAttribute<Employee, byte[]> photo;
    public static volatile ListAttribute<Employee, Badge> badgeList;
    public static volatile SingularAttribute<Employee, String> employeeNumber;
    public static volatile SingularAttribute<Employee, String> firstLastName;
    public static volatile SingularAttribute<Employee, String> phoneNumber;
    public static volatile SingularAttribute<Employee, String> name;
    public static volatile SingularAttribute<Employee, Role> tbRoleId;
    public static volatile SingularAttribute<Employee, String> rfid;
    public static volatile SingularAttribute<Employee, Integer> id;
    public static volatile SingularAttribute<Employee, String> position;
    public static volatile SingularAttribute<Employee, String> email;
    public static volatile ListAttribute<Employee, Rfid> rfidList;
    public static volatile ListAttribute<Employee, Credential> tbCredentialList;
    public static volatile SingularAttribute<Employee, String> departament;

}