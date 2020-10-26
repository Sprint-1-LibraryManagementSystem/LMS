package com.capgemini.lms.entities;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author_table")
public class Author         // project class and entity
{
	@Id
	@GeneratedValue
	@Column(name = "author_id")
	private int authorId;
	private String firstName;
	private String lastName;
	private String email;
	private String contactno;
	private List<Books> books;
	
	public Author()
	{
		
	}
	
	public Author(String firstName, String lastName, String email, String contactno) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactno = contactno;
	}

	public Author(int authorId, String firstName, String lastName, String email, String contactno) {
		super();
		this.authorId = authorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactno = contactno;
	}


	public int getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactno() {
		return contactno;
	}
	
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", contactno=" + contactno + "]";
	}	
}
