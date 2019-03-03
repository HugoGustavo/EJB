package com.tutorialspoint.stateless;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.exception.NoBookAvaliableException;

@Stateless
@WebService(serviceName="LibraryService")
public class LibraryPersistenceBean implements LibraryPersistenceBeanRemote {

	@PersistenceContext(unitName="EjbComponentPU")
	private EntityManager entityManager;
	
    public LibraryPersistenceBean() {
    }

	public void addBook(Book book) {
		entityManager.persist(book);
	}

	@WebMethod(operationName="getBooks")
	public List<Book> getBooks() throws NoBookAvaliableException {
		String ejbQL = "From Book b where b.name like ?1";
		Query query = entityManager.createQuery(ejbQL);
		query.setParameter(1, "%test%");
		
		List<Book> books = query.getResultList();
		if ( books.size() == 0 )
			throw new NoBookAvaliableException();
				
		return books;
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("LibraryPersistenceBean.postConstruct: bean created");
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("LibraryPersistenceBean.preDestroy: bean is removed");
	}

}
