package com.tutorialspoint.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.tutorialspoint.callback.BookCallbackListener;

@Entity
@Table(name="books")
@EntityListeners(BookCallbackListener.class)
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", length=50)
	private String name;
	
	@Lob
	@Basic(fetch= FetchType.EAGER)
	@Column(name="image")
	private byte[] image;
	
	@Column(name="xml")
	private String xml;

	@ManyToMany(
		cascade= {CascadeType.PERSIST, CascadeType.MERGE},
		fetch=FetchType.EAGER
	)
	@JoinTable(
		name="book_author",
		joinColumns = {@JoinColumn(name="book_id", referencedColumnName="id")},
		inverseJoinColumns= {@JoinColumn(name="author_id", referencedColumnName="id")}
	)
	Set<Author> authors;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(
			name="name",
			column=@Column(name="PUBLISHER")
		),
		@AttributeOverride(
			name="address",
			column=@Column(name="PUBLISHER_ADDRESS")
		)
	})
	private Publisher publisher;
		
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}
	
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	public Set<Author> getAuthors(){
		return authors;
	}
}
