package com.tutorialspoint.stateful;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LibraryStatefulSessionBeanRemote {
	 public void addBook(String bookName);
	 public List<String> getBooks();
}
