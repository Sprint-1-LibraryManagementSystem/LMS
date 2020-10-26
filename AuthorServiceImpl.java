package com.capgemini.lms.service;
import com.capgemini.lms.dao.IAuthorDao;
import java.util.List;
import com.capgemini.lms.entities.Author;

import com.capgemini.lms.entities.Author;
import com.capgemini.lms.exception.AuthorNotFoundException;
import com.capgemini.lms.utility.JpaUtility;


public class AuthorServiceImpl implements AuthorService
{
	AuthorService dao = new AuthorServiceImpl();

	@Override
	public int addAuthorDetails(Author author) 
	{
		return dao.addAuthorDetails(author);
	}

	@Override
	public int updateAuthorDetails(Author author) 
	{
		return dao.updateAuthorDetails(author);
	}

	@Override
	public int deleteAuthorDetails(int authorId) 
	{
		return dao.deleteAuthorDetails(authorId);
	}

	@Override
	public List<Author> viewAuthorsList() {
		
		return dao.viewAuthorsList();
	}

	@Override
	public Author viewAuthorById(int id) {
		
		return dao.viewAuthorById(id);
	}
}
