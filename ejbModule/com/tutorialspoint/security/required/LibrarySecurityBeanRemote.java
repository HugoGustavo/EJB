package com.tutorialspoint.security.required;

import javax.ejb.Remote;

import com.tutorialspoint.entity.Book;

@Remote
public interface LibrarySecurityBeanRemote {
	public void delete(Book book);
	public void viewBook(Book book);
	public void deleteAll();
}
