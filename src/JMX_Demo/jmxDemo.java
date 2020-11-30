package JMX_Demo;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;

public class jmxDemo {
    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName mbsname = new ObjectName("test:name=HelloWorld");
        HelloWorld mbean = new HelloWorld();
        mbs.registerMBean(mbean, mbsname);

        // 创建一个rmi registry
        Registry registry = LocateRegistry.createRegistry(1099);
        // 构造JMXServiceURL，绑定创建的rmi
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        // 构造JMXConnectorServer，关联mbserver
        JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, mbs);
        jmxConnectorServer.start();
        System.out.println("JMXConnectorServer is ready...");

        System.out.println("press any key to exit.");
        System.in.read();
    }
}