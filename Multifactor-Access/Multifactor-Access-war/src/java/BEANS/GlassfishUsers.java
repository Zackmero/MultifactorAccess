/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEANS;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

@Named(value = "GlassfishUsers")
@RequestScoped
public class GlassfishUsers {

    public GlassfishUsers() {
    }

    public void addUser(String userName, String password, String groupName) {

        try {

            //  Connect to server
            String host = "localhost";
            int port = 4848;
            String urlString = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
            JMXServiceURL serviceURL = new JMXServiceURL(urlString);

            try (
                    // Connect to JMX server
                    JMXConnector connector = JMXConnectorFactory.connect(serviceURL, null)) {
                connector.connect();
                
                // Get MBeanServer from JMX connector
                MBeanServer mbeanServer = (MBeanServer) connector.getMBeanServerConnection();
                
                //  Create an objectName to AMX MBean
                ObjectName amxObjectName = new ObjectName("amx-support:type=boot-amx");
                
                // Get AMX MBean
                Object amxBean = mbeanServer.getAttribute(amxObjectName, "BootAMXBean");
                
                // Invoke method to add user to group
                Object[] params = {userName, password, groupName};
                String[] signature = {"java.lang.String", "java.lang.String", "java.lang.String"};
                mbeanServer.invoke(amxObjectName, "addUserToGroup", params, signature);

                // close session
                connector.close();
            }

        } catch (IOException | AttributeNotFoundException | InstanceNotFoundException | MBeanException | MalformedObjectNameException | ReflectionException e) {
            System.out.println("Error Glassfish bean: " + e.getMessage());
        }
    }

}
