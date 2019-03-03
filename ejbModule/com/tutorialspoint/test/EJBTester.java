package com.tutorialspoint.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.tutorialspoint.entity.Author;
import com.tutorialspoint.entity.Book;
import com.tutorialspoint.entity.Publisher;
import com.tutorialspoint.exception.NoBookAvaliableException;
import com.tutorialspoint.stateless.LibraryPersistenceBeanRemote;
import com.tutorialspoint.timer.TimerSessionBeanRemote;

public class EJBTester {
	private static final String PROVIDER_URL 				= "jnp://localhost:1099";
	private static final String JNP_INTERFACES 				= "org.jboss.naming:org.jnp.interfaces";
	private static final String INITIAL_CONTEXT_FACTORY 	= "org.jnp.interfaces.NamingContextFactory";
	private static final String LOOKUP_BEAN_REMOTE_STRING	= "LibraryPersistenceBean/remote";
	private static final String LOOKUP_BEAN_QUEUE_STRING	= "/queue/BookQueue";
     
	private static InitialContext initialContext;
	private static LibraryPersistenceBeanRemote libraryPersistenceBeanRemote;
	private static QueueSession session;
	private static QueueSender queueSender;
	
	private static BufferedReader consoleReader;
	
	public static void main(String[] args) throws NamingException {
		try {
			init();
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void init() throws NamingException, JMSException {
		configInitialContext();
		//configTimerService();
		configBeanRemote();
		configMessageDriven();
		configConsoleReader();
	}
	
	private static void execute() {
		while(true) {
			showTitle();
			addBook();
			showBooks();
		}
	}
	
	private static void configBeanRemote() throws NamingException {
		libraryPersistenceBeanRemote = (LibraryPersistenceBeanRemote) initialContext.lookup(LOOKUP_BEAN_REMOTE_STRING);
	}
		
	private static void configMessageDriven() throws NamingException, JMSException {
		Queue queue = (Queue) initialContext.lookup(LOOKUP_BEAN_QUEUE_STRING);
		QueueConnectionFactory factory = (QueueConnectionFactory) initialContext.lookup("ConnectionFactory");
		QueueConnection connection = factory.createQueueConnection();
		session = connection.createQueueSession(false,  QueueSession.AUTO_ACKNOWLEDGE);
		queueSender = session.createSender(queue);
	}
	
	private static void configTimerService() throws NamingException {
		TimerSessionBeanRemote timerSesionBeanRemote = (TimerSessionBeanRemote) initialContext.lookup("TimerSessionBean/remote");
		System.out.println("[" + (new Date()).toString() + "]" + " timer created");
		timerSesionBeanRemote.createTimer(2000);
	}
	
	private static void configConsoleReader() {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private static void configInitialContext() throws NamingException {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		properties.put(Context.URL_PKG_PREFIXES, JNP_INTERFACES);
		properties.put(Context.PROVIDER_URL, PROVIDER_URL);
		initialContext = new InitialContext(properties);
		
	}
		
	private static void showTitle() {
		System.out.println("*************************");
		System.out.println("  Welcome to Book Store  ");
		System.out.println("*************************");
	}

	
	private static void addBook() {
		try {
			System.out.print("Enter book name: ");
			String bookName = consoleReader.readLine();
			byte[] imageBytes = {
				0x32, 0x32, 0x32, 0x32, 0x32,
				0x32, 0x32, 0x32, 0x32, 0x32,
	            0x32, 0x32, 0x32, 0x32, 0x32,
	            0x32, 0x32, 0x32, 0x32, 0x32,
	            0x32, 0x32, 0x32, 0x32
			};
			String xml = "<book><name>" + bookName + "</name></book>";
			Book book = new Book();
			book.setName(bookName);
			book.setImage(imageBytes);
			book.setXml(xml);
			
			System.out.println("Enter publisher name: ");
			String publisherName = consoleReader.readLine();			
			System.out.println("Enter publisher address: ");
			String publisherAddress = consoleReader.readLine();			
			Publisher publisher = new Publisher();
			publisher.setName(publisherName);
			publisher.setAddress(publisherAddress);			
			book.setPublisher(publisher);
			
			System.out.print("Enter author name: ");
			String authorName = consoleReader.readLine();
			Author author = new Author();
			author.setName(authorName);
			Set<Author> authors = new HashSet<Author>();
			authors.add(author);
			book.setAuthors(authors);

			libraryPersistenceBeanRemote.addBook(book);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void showBooks() {
		try {
			List<Book> books = libraryPersistenceBeanRemote.getBooks();
			for (Book book : books) {
				System.out.println("Book name        : " + book.getName());
				System.out.println("Book xml         : " + book.getXml());
				System.out.println("Publisher name   : " + book.getPublisher().getName());
				System.out.println("Publisher address: " + book.getPublisher().getAddress());
				for ( Author author : book.getAuthors() )
					System.out.println("Author           : " + author.getName());
				System.out.println();
			}
				
		} catch (NoBookAvaliableException e) {
			System.out.println("Library is empty!");
		}
	}
	
}
