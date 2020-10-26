package com.capgemini.lms.dao;

import java.util.List;

import javax.persistence.*;

import com.capgemini.lms.entities.UserAddress;
import com.capgemini.lms.entities.Users;
import com.capgemini.lms.exception.AddressNotFoundException;
import com.capgemini.lms.exception.UserNotFoundException;

public class UserAddressDaoImpl implements UserAddressDao{
	
	EntityManagerFactory factory=Persistence.createEntityManagerFactory( "Lib_sprint" );
	EntityManager manager = factory.createEntityManager( );

	@Override
	public int addUserAddress(UserAddress address) {
		
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(address);
		transaction.commit();
		return address.getAddressId();
	}

	@Override
	public int updateUserAddressDetails(UserAddress address) throws AddressNotFoundException {
		EntityTransaction transaction = manager.getTransaction();
		UserAddress ad=manager.find(UserAddress.class, address.getAddressId());
		if(ad==null) {
			throw new AddressNotFoundException("No such address present for update");
		}
		else {
			ad.setAddress1(address.getAddress1());
			ad.setAddress2(address.getAddress2());
			ad.setAddressId(address.getAddressId());
			ad.setCity(address.getCity());
			ad.setState(address.getState());
			ad.setPincode(address.getPincode());
			transaction.begin();
			manager.persist(ad);
			System.out.println(ad);
			transaction.commit();
		}
		return address.getAddressId();
	}

	@Override
	public int deleteUserAddress(int addressId) throws AddressNotFoundException {
		EntityTransaction transaction = manager.getTransaction();
		UserAddress ad=manager.find(UserAddress.class, addressId);
		if(ad==null) {
			throw new AddressNotFoundException("No address present with id "+addressId);
		}
		else {
			transaction.begin();
			manager.remove(ad);
			transaction.commit();
		}
		return addressId;
	}

	@Override
	public UserAddress viewAddressByUserId(int userid) throws AddressNotFoundException {
		
		Users user=manager.find(Users.class, userid);
		if(user==null) {
			throw new AddressNotFoundException("No user present with id "+userid);
		}
		return user.getUseraddress();
	}

	@Override
	public List<UserAddress> viewUserAddressList(){		
		List<UserAddress> l;
		String selectQuery = "select e from UserAddress e";
		Query query = manager.createQuery(selectQuery);
		l = query.getResultList();
		return l;
	}
}
