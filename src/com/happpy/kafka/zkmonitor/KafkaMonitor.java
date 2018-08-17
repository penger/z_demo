package com.happpy.kafka.zkmonitor;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

//选举leader的速率，单位：

//节点运行状态                      
//请求队列大小，单位：个                kafka.network:type=RequestChannel,name=RequestQueueSize
//消息进入速率（消息数/秒              kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec
//流入数据速率（字节/秒）              kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec
//流出数据速率（字节/秒）              kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec


public class KafkaMonitor {
	
	    private static final String queuesize = "kafka.network:type=RequestChannel,name=RequestQueueSize";  
	    private static final String megin = "kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec";  
	    private static final String datain = "kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec";  
	    private static final String dateout = "kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec";  
	    public String extractMonitorData(String jmx) {  
	        //TODO 通过调用API获得IP以及参数  
	    	KafkaMonitor monitorDataPoint = new KafkaMonitor();  
//	        String jmxURL = "service:jmx:rmi:///jndi/rmi://192.168.40.242:9999/jmxrmi";  
	        String jmxURL = jmx;  
//	        String jmxURL = "service:jmx:rmi:///jndi/rmi://node3:9096/jmxrmi";  
	        try {  
	            MBeanServerConnection jmxConnection = monitorDataPoint.getMBeanServerConnection(jmxURL);  
	            
	            ObjectName requestQueueSize= new ObjectName(queuesize);  
	            ObjectName messagesInPerSec = new ObjectName(megin);  
	            ObjectName bytesInPerSec = new ObjectName(datain);  
	            ObjectName bytesOutPerSec = new ObjectName(dateout);  

	            int qqq = (int) jmxConnection.getAttribute(requestQueueSize, "Value");  
	            Long min = (Long) jmxConnection.getAttribute(messagesInPerSec, "Count");  
	            Long bin = (Long) jmxConnection.getAttribute(bytesInPerSec, "Count");  
	            Long bout = (Long) jmxConnection.getAttribute(bytesOutPerSec, "Count");  
	      
	            System.out.println("RequestQueueSize="+qqq);  
	            System.out.println("MessagesInPerSec="+min);  
	            System.out.println("BytesInPerSec="+bin);  
	            System.out.println("BytesOutPerSec="+bout);  
	            
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (MalformedObjectNameException e) {  
	            e.printStackTrace();  
	        } catch (AttributeNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (MBeanException e) {  
	            e.printStackTrace();  
	        } catch (ReflectionException e) {  
	            e.printStackTrace();  
	        } catch (InstanceNotFoundException e) {  
	            e.printStackTrace();  
	        }  
	        return monitorDataPoint.toString();  
	    }  
	    public static void main(String[] args) {  
	    	
	        new KafkaMonitor().extractMonitorData(args[0]);  
	    }  
	    /** 
	     * 获得MBeanServer 的连接 
	     * 
	     * @param jmxUrl 
	     * @return 
	     * @throws IOException 
	     */  
	    public MBeanServerConnection getMBeanServerConnection(String jmxUrl) throws IOException {  
	        JMXServiceURL url = new JMXServiceURL(jmxUrl);  
	        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);  
	        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();  
	        return mbsc;  
	    } 

}
