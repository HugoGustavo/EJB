package com.tutorialspoint.stateful;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

@Stateful
public class LibraryStatefulSessionBean implements LibraryStatefulSessionBeanRemote {
	private List<String> bookShelf;
	
    public LibraryStatefulSessionBean() {
    	bookShelf = new ArrayList<String>();
    }
    
    public void addBook(String bookName) {
    	bookShelf.add(bookName);
    }
    
    public List<String> getBooks(){
    	return bookShelf;
    }
    
    @PostConstruct
    public void postConstruct() {
    	System.out.println("LibraryStatefulSessionBEean.postConstruct: bean created");
    }
    
    @PreDestroy
    public void postDestroy() {
    	System.out.println("LibraryStatefulSessionBean.postDestroy: bean removed");
    }
    
    @PostActivate
    public void postActivate() {
    	System.out.println("LibraryStatefulSessionBean.postActivate: bean activate");
    }
    
    @PrePassivate
    public void prePassive() {
    	System.out.println("LibraryStatefulSessionBean.prePassive: bean passivated");
    }

}
