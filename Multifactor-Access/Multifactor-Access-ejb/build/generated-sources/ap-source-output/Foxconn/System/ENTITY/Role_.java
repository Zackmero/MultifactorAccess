package Foxconn.System.ENTITY;

import Foxconn.System.ENTITY.Employee;
import Foxconn.System.ENTITY.PermissionAccess;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-04T15:28:27")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, String> role;
    public static volatile ListAttribute<Role, Employee> employeeList;
    public static volatile SingularAttribute<Role, Integer> id;
    public static volatile ListAttribute<Role, PermissionAccess> permissionAccessList;

}