package com.tutorialspoint.security.required;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import com.tutorialspoint.entity.Book;

@Stateless
@DeclareRoles({"student", "library"})
public class LibrarySecurityBean implements LibrarySecurityBeanRemote {
	@RolesAllowed({"librarian"})
	public void delete(Book book) {
		
	}

	@PermitAll
	public void viewBook(Book book) {
		
	}

	@DenyAll
	public void deleteAll() {
		
	}

}
