package Foxconn.System.ENTITY;

import Foxconn.System.ENTITY.Access;
import Foxconn.System.ENTITY.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-04T15:28:27")
@StaticMetamodel(PermissionAccess.class)
public class PermissionAccess_ { 

    public static volatile SingularAttribute<PermissionAccess, Role> tbRoleId;
    public static volatile SingularAttribute<PermissionAccess, Access> tbAccessId;
    public static volatile SingularAttribute<PermissionAccess, Integer> id;

}