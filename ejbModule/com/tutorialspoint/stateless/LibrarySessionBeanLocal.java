package com.tutorialspoint.stateless;

import java.util.List;

import javax.ejb.Local;

@Local
public interface LibrarySessionBeanLocal {
	public void addBook(String bookName);
	public List<String> getBooks();
}
