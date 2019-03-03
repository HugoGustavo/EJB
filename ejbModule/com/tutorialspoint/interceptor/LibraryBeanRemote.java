package com.tutorialspoint.interceptor;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LibraryBeanRemote {
	public void addBook(String bookName);
	public List<String> getBooks();
}
