package activemq;


import java.io.InputStream;
import java.util.Random;
import java.util.ResourceBundle;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.QueueSender;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
//import org.apache.log4j.Logger;


public class ActiveMQCall {
	
	
	
	//ResourceBundle bundle = null;
	//QueueSender queueSender;
//	String response = "";
	//InputStream inputStream;
	//final static Logger logger = Logger.getLogger(ActiveMQCall.class); 

	public String GetProducer(String queueName ,String correlationId,String jsonString) {
		
		Connection connection = null;
		ActiveMQSession session = null;
		Destination destination = null;
		MessageProducer producer = null;
		 String activeMQURL = null;
			String activeMQUsername = null;
			String activeMQPassword = null;
	//	MessageConsumer consumer = null;
		
		try {
		// bundle = ResourceBundle.getBundle("serverConfig");
		
			// bundle = ResourceBundle.getBundle("serverConfig");
			// activeMQURL=bundle.getString("activeMQ.URL");
			 activeMQURL="http://34.74.142.84:8161/admin/";
//			 activeMQUsername=bundle.getString("activeMQ.Username");
//			 activeMQPassword = bundle.getString("activeMQ.Password");
			 
			 activeMQUsername="admin";
			 activeMQPassword ="admin";
			 

		/*ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
		"tcp://" + prop.getProperty("activeMQ.URL") + ":61616");*/
	//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQUsername, activeMQPassword, activeMQURL);
	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQUsername, activeMQPassword, "tcp://34.74.142.84:61616");
     connection = connectionFactory.createConnection();
    session = (ActiveMQSession) connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
          queueName = queueName.toUpperCase();
            destination = session.createQueue(queueName);
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
          connection.start();
          TextMessage textMessage = session.createTextMessage();
  		System.out.println(" jsonString in producer of SFDC param :: "+jsonString);

          textMessage.setText(jsonString);
          textMessage.setJMSCorrelationID(correlationId);
			textMessage.setJMSRedelivered(true);
			producer.send(textMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
			connection.close();
			connection=null;
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
  		System.out.println("correlationId Producer :: "+correlationId);

		return correlationId;
	}
	
/*public String GetConsumer(String queueName) {
		String correlationId = "";
		try {
			
			// bundle = ResourceBundle.getBundle("serverConfig");
			 String activeMQURL ="";
				String activeMQUsername="";
				String activeMQPassword="";
				 //bundle = ResourceBundle.getBundle("serverConfig");
				// activeMQURL=bundle.getString("activeMQ.URL");
				// activeMQUsername=bundle.getString("activeMQ.Username");
				// activeMQPassword = bundle.getString("activeMQ.Password");
				 
				 
				// activeMQURL="http://34.74.142.84:8161/admin/";
				 activeMQUsername="admin";
				 activeMQPassword ="admin";


			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			"tcp://" + prop.getProperty("activeMQ.URL") + ":61616");
		//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQUsername, activeMQPassword, activeMQURL);
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQUsername, activeMQPassword, "tcp://34.74.142.84:61616");

	     connection = connectionFactory.createConnection();
	     connection.start();
	    session = (ActiveMQSession) connection.createSession(false,
			Session.AUTO_ACKNOWLEDGE);
	    queueName = queueName.toUpperCase();
		destination = session.createQueue(queueName);
		//destination = session.createTopic("Topic.File.Transport");
		consumer = session.createConsumer(destination);
	//	System.out.println("1");
			Message msg= consumer.receive();
			if (msg instanceof TextMessage) {
	            TextMessage textMessage = (TextMessage) msg;
	            System.out.println("Received message '" + textMessage.getText() + "'");
	        }
			
			correlationId = msg.getJMSCorrelationID();
	//		System.out.println("2");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return "false";
		}finally {
			try {
			connection.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
  		System.out.println("correlationId consumer :: "+correlationId);

		return correlationId;
		
	}
*/
public String createRandomString() {
	Random random = new Random(System.currentTimeMillis());
	long randomLong = random.nextLong();
	return Long.toHexString(randomLong);
}

/*public static void main(String args[]) {
	ActiveMQCall a = new ActiveMQCall();
	String id=a.GetConsumer("WORKINGTEST");
	System.out.println("id: "+id);
	
	//a.GetProducer("testing", "765", "4", "first message");
	  a.GetProducer( "queueName" ,  "646","{\r\n" + 
	  		"\"subject\":\"CLARKSONS MIDDLE EAST CPP TRACKERS - 02/05/19\",\r\n" + 
	  		"\"to\":\"scorpio@bizlem.com\",\r\n" + 
	  		"\"from\":\"Dean.Marshall@clarksons.com\",\r\n" + 
	  		"\"maildate\":\"Tue Oct 23 16:55:02 IST 2018\",\r\n" + 
	  		"\"mailbodyfilepath\":\"\",\r\n" + 
	  		"\"attachment\":[{\"tomcatfilepath\":\"\",\"filename\":\"\"\r\n" + 
	  		"},{\"tomcatfilepath\":\"\"}]\r\n" + 
	  		"}") ;

	//a.GetConsumer("IPMS.COCD.GENERATE");
	try {
	ResourceBundle	bundle = ResourceBundle.getBundle("serverConfig");
	 String activeMQURL ="";
		String activeMQUsername="";
		String activeMQPassword="";
		 bundle = ResourceBundle.getBundle("serverConfig");
		 activeMQURL=bundle.getString("activeMQ.URL");
		 activeMQUsername=bundle.getString("activeMQ.Username");
		 activeMQPassword = bundle.getString("activeMQ.Password");
System.out.println("activeMQURL"+activeMQURL);
System.out.println("activeMQUsername "+		 activeMQUsername);
System.out.println("activeMQPassword "+activeMQPassword);
	
ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQUsername, activeMQPassword, activeMQURL);
//connection = connectionFactory.createConnection();
System.out.println(connectionFactory);
Connection connection = connectionFactory.createConnection();
System.out.println(connection);
connection.start();
Session session =  connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
System.out.println(session);
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	}
}
*/

}
