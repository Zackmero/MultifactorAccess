package Foxconn.System.ENTITY;

import Foxconn.System.ENTITY.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-04T15:28:27")
@StaticMetamodel(Credential.class)
public class Credential_ { 

    public static volatile SingularAttribute<Credential, String> password;
    public static volatile SingularAttribute<Credential, Employee> tbEmployeeId;
    public static volatile SingularAttribute<Credential, Integer> id;

}