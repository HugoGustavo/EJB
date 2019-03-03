package com.tutorialspoint.callback;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.tutorialspoint.entity.Book;

public class BookCallbackListener {
	@PrePersist
	public void prePersist(Book book) {
		System.out.println("BookCallbackListener.prePersist: " + book.getId() );
	}
	
	@PostPersist
	public void postPerist(Book book) {
		System.out.println("BookCallbackListener.postPersist: " + book.getId());
	}
	
	@PreRemove
	public void preRemove(Book book) {
		System.out.println("BookCallbackListener.preRemove: " + book.getId());
	}
	
	@PostRemove
	public void postRemove(Book book) {
		System.out.println("BookCallbackListener.postRemove: " + book.getId());
	}
	
	
	@PreUpdate
	public void preUpdate(Book book) {
		System.out.println("BookCallbackListener.preUpdate: " + book.getId());
	}
	
	@PostUpdate
	public void postUpdate(Book book) {
		System.out.println("BookCallbackListener.postUpdate: " + book.getId());
	}
	
	@PostLoad
	public void postLoad(Book book) {
		System.out.println("BookCallbackListener.postLoad: " + book.getId());
	}	
}
