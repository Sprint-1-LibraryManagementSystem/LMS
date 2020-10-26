package com.capgemini.lms.dao;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import com.capgemini.lms.entities.Author;
import com.capgemini.lms.exception.AuthorNotFoundException;
import com.capgemini.lms.utility.JpaUtility;



public class AuthorDaoImpl implements AuthorDao
{
	
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;

	@Override
	public int addAuthorDetails(Author author) 
	{
		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		
	    manager.persist(author);
	    transaction.commit();
        manager.close();
        return author.getAuthorId(); 
	}

	@Override
	public int updateAuthorDetails(Author author) throws AuthorNotFoundException 
	{
		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		
		transaction.begin();
		manager.merge(author);
		if (author == null) {
			throw new AuthorNotFoundException("No record present with the given id to update");
		} 
			transaction.commit();
		return author.getAuthorId();
	}

	@Override
	public int deleteAuthorDetails(int authorId) throws AuthorNotFoundException 
	{
		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		
		Author author = manager.find(Author.class, authorId);
		if(author == null) 
		{
			throw new AuthorNotFoundException("No record present with the given id to update");
		}
		else {
			transaction.begin();
			manager.remove(authorId);
			transaction.commit();
		}
    
		return authorId;
	}

	@Override
	public List<Author> viewAuthorsList() 
	{ 
	    factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	    
		String selectQuery = "select e from Author e";
		Query query = manager.createQuery(selectQuery);
		List<Author> list = query.getResultList();
	    return list;

    }

	@Override
	public Author viewAuthorById(int id) throws AuthorNotFoundException
	{
		factory = JpaUtility.getFactory();
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		
		Author author = null;
		transaction.begin();
		author = manager.find(Author.class, id);
		transaction.commit();
		return author;
	}

}
