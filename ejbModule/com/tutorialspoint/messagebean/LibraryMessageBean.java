package com.tutorialspoint.messagebean;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.stateless.LibraryPersistenceBeanRemote;

@MessageDriven(
	name="BookMessageHandler",
	activationConfig={
		@ActivationConfigProperty (
			propertyName="destinationType",
			propertyValue="javax.jms.Queue"
		),
		@ActivationConfigProperty(
			propertyName="destination",
			propertyValue="/queue/BookQueue"
		)
	}
)
public class LibraryMessageBean implements MessageListener {
	@Resource
	private MessageDrivenContext messageDrivenContext;
	
	@EJB
	LibraryPersistenceBeanRemote libraryPersistenceBeanRemote;
	
	public void onMessage(Message message) {
		
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Book book = (Book) objectMessage.getObject();
			libraryPersistenceBeanRemote.addBook(book);
		} catch(JMSException ex) {
			messageDrivenContext.setRollbackOnly();
		}

	}

}
