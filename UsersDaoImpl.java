package com.capgemini.lms.dao;

import java.util.*;

import javax.persistence.*;

import com.capgemini.lms.entities.Users;
import com.capgemini.lms.exception.UserNotFoundException;


public class UsersDaoImpl implements UsersDao{
	
	EntityManagerFactory factory=Persistence.createEntityManagerFactory( "Lib_sprint" );
	EntityManager manager = factory.createEntityManager( );
	
	@Override
	public int register(Users user) {
		
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(user);
		transaction.commit();
		return user.getUserid();
	}

	@Override
	public Users loginValidate(String username, String password) throws UserNotFoundException {
		
		return null;
	}

	@Override
	public void cancelSubscription(int userid) throws UserNotFoundException {
		
		EntityTransaction transaction = manager.getTransaction();
		Users user=manager.find(Users.class, userid);
		if(user==null) {
			throw new UserNotFoundException("No user present with id "+userid);
		}
		else {
			transaction.begin();
			user.setSubscription_status("NO");
			transaction.commit();
		}
		
	}

	@Override
	public int payThePenalty(int userid, double amount) throws UserNotFoundException {
		
		return 0;
	}

	@Override
	public List<Users> viewAllUsers() {
		
		List<Users> l;
		String selectQuery = "select e from Users e";
		Query query = manager.createQuery(selectQuery);
		l = query.getResultList();
		return l;
		
	}

	@Override
	public int updateUserDetails(Users user) throws UserNotFoundException {
		
		int id=user.getUserid();
		Users u1=manager.find(Users.class, user.getUserid());
		EntityTransaction transaction = manager.getTransaction();
		if(user==null){
			throw new UserNotFoundException("No user present with id "+id);
		}
		else{
			transaction.begin();
			u1.setPassword(user.getPassword());
			u1.setFirstName(user.getFirstName());
			
			transaction.commit();
		}
		return user.getUserid();
	}

	@Override
	public int deleteUser(int id) throws UserNotFoundException {
		
		EntityTransaction transaction = manager.getTransaction();
		Users user=manager.find(Users.class, id);
		if(user==null) {
			throw new UserNotFoundException("No user present with id "+id);
		}
		else {
			transaction.begin();
			manager.remove(user);
			transaction.commit();
		}
		return user.getUserid();
	}
}

