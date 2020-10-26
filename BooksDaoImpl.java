package com.capgemini.lms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


import com.capgemini.lms.entities.Books;
import com.capgemini.lms.utility.*;

import com.capgemini.lms.exception.*;


public class BooksDaoImpl implements BooksDao
{
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	public int addBook(Books book)
	{
		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	

		transaction.begin();
		manager.persist(book);
		transaction.commit();
		manager.close();
		factory.close();
		return book.getBookid();
	}
	public int updateBookDetails(Books book)
	{
		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();

	
			transaction.begin();
			manager.merge(book);
			if(book==null)
			{
				throw new BookNotFoundException("No book present with the given id to update");
				
			}
			transaction.commit();
	
		return book.getBookid();
	}
		
	public int removeBook(int bookid) 
	{
		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		Books book = manager.find(Books.class, bookid);
		if (book == null) {
			throw new BookNotFoundException("No book present with the given id to remove");
		} else {
			transaction.begin();
			manager.remove(book);
			transaction.commit();
		}
		return bookid;
	
	}
	public List<Books> searchBookByTitle(String keyword)
	{
		
			
			factory = JpaUtility.getFactory();
			manager = factory.createEntityManager();

			
			
			String selectQuery = "select e from Books e where e.title=:keyword2";
			Query query = manager.createQuery(selectQuery);
			query.setParameter("keyword2", keyword);
			List<Books> list = query.getResultList();
			boolean ans=list.isEmpty();
			if(ans==true)
			{
				throw new BookNotFoundException("No records found with given Title");
			}
			return list;

	
	
	}
	public List<Books> searchBookBySubject(String keyword)
	{

		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();

		
		
		String selectQuery = "select e from Books e where e.subject=:keyword2";
		Query query = manager.createQuery(selectQuery);
		query.setParameter("keyword2", keyword);
		List<Books> list = query.getResultList();
		boolean ans=list.isEmpty();
		if(ans==true)
		{
			throw new BookNotFoundException("No records found with the given subject");
		}
		return list;

		
	}
	public List<Books> viewAllBooks()
	{
		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();

		String selectQuery = "select e from Books e";
		Query query = manager.createQuery(selectQuery);
        
		List<Books> list = query.getResultList();
		return list;
	
		
		
		
	
		
	}
	
	

}
