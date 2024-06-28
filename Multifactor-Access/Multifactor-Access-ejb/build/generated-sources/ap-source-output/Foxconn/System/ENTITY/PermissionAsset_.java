package Foxconn.System.ENTITY;

import Foxconn.System.ENTITY.Asset;
import Foxconn.System.ENTITY.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-04T15:28:27")
@StaticMetamodel(PermissionAsset.class)
public class PermissionAsset_ { 

    public static volatile SingularAttribute<PermissionAsset, Asset> tbAssetId;
    public static volatile SingularAttribute<PermissionAsset, Employee> tbEmployeeId;
    public static volatile SingularAttribute<PermissionAsset, Integer> id;

}