package com.tutorialspoint.stateless;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.LocalBinding;

@Stateless
@LocalBinding(jndiBinding="tutorialsPoint/librarySession")
public class LibrarySessionBean implements LibrarySessionBeanLocal {
	private List<String> bookShelf;
	
	public LibrarySessionBean() {
		bookShelf = new ArrayList<String>();
	}

	public void addBook(String bookName) {
		bookShelf.add(bookName);	}

	public List<String> getBooks() {
		return bookShelf;
	}

}
