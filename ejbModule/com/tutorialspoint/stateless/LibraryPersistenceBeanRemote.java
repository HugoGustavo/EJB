package com.tutorialspoint.stateless;

import java.util.List;

import javax.ejb.Remote;

import com.tutorialspoint.entity.Book;
import com.tutorialspoint.exception.NoBookAvaliableException;

@Remote
public interface LibraryPersistenceBeanRemote {
	public void addBook(Book book);
	public List<Book> getBooks() throws NoBookAvaliableException;
}
