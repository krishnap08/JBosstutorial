import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MyMDBClient {

	public static void main(String[] args) throws NamingException, JMSException {
		System.out.println("Entering MyMDBClient");
		
		Context context = MyMDBClient.getInitialContext();
		Queue queue = (Queue)context.lookup("jms/queue/exampleQueue");
		QueueConnectionFactory qconFactory = (QueueConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
		QueueConnection qconn = qconFactory.createQueueConnection("test123","testuser");
		QueueSession qsession = qconn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		TextMessage txtMsg = qsession.createTextMessage("Message from MDBClient");
		QueueSender qsender = qsession.createSender(queue);
		qsender.send(txtMsg);
		
		System.out.println("Exiting MyMDBClient");
	}
	
	public static Context getInitialContext() throws NamingException{
		Properties properties = new Properties();
		
		properties.put(Context.
				 
				INITIAL_CONTEXT_FACTORY
				,
				 
				"org.jboss.naming.remote.client.InitialContextFactory"
				);
		/*properties.put(Context.
				URL_PKG_PREFIXES
				,
				 
				"org.jboss.naming:org.jboss.naming.remote.client"
				);*/
		properties.put(Context.
				PROVIDER_URL, "remote://localhost:4447"
				);
		properties.put( Context.SECURITY_PRINCIPAL, "test123" );
		properties.put( Context.SECURITY_CREDENTIALS, "testuser" );
				
				/*
		properties.setProperty("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
		properties.setProperty("java.naming.factory.url.pkgs","org.jboss.naming");
		properties.setProperty("java.naming.provider.url","127.0.0.1:1099");*/
		return new InitialContext(properties);
	}
}
