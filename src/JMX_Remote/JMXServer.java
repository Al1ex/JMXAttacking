package JMX_Remote;

import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;

public class JMXServer {
    public static void main(String[] args) throws Exception {
        //创建MBeanServer
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            //注册一个端口，绑定url后，客户端就可以使用rmi通过url方式来连接JMXConnectorServer
            LocateRegistry.createRegistry(9999);
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://127.0.0.1:9999/jmxrmi");
            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
            System.out.println("....................begin rmi start.....");
            cs.start();
            System.out.println("....................rmi start.....");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}