package com.jms.tut;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: myMDB
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true"),
				@ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(
				propertyName = "destination", propertyValue = "queue/exampleQueue")
		}, mappedName="exampleQueue")
public class MyMDB implements MessageListener {

   
    public void onMessage(Message message) {
    	TextMessage msgstr = (TextMessage)message;
       System.out.println("Message Recieved: "+msgstr.toString());
        
    }

}
