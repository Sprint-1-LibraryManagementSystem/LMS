package com.capgemini.lms.ui;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capgemini.lms.entities.*;
import com.capgemini.lms.exception.*;
import com.capgemini.lms.service.*;

public class Main {

	public static void main(String[] args) throws ParseException {


		Scanner scanner = new Scanner(System.in);
		UserService service1 = new UserServiceImpl();
		UserAddressService service2 = new UserAddressServiceImpl();

		while(true) {
			System.out.println("Choose a option :");
			System.out.println("1. Register as a new user");
			System.out.println("2. View all the registered users ");
			System.out.println("3. Delete User Account");
			System.out.println("4. Update all User details");
			System.out.println("5. Update User address details");
			System.out.println("6. Delete User Address");
			System.out.println("7. View address by User Id");
			System.out.println("8. View all Address of the users");

			int option=scanner.nextInt();

			switch(option) {
			case 1:
			{
				System.out.println("Enter the details for Registration:");
				System.out.println("Enter first_name:");
				String fname = scanner.next();
				System.out.println("Enter last_name:");
				String lname = scanner.next();
				System.out.println("Enter Mobile_no:");
				String mobile = scanner.next();
				System.out.println("Enter Email:");
				String email = scanner.next();
				System.out.println("create a Password:");
				String pass = scanner.next();
				System.out.println("Enter date of birth in (dd/mm/yy) format :");
				SimpleDateFormat DateFor = new SimpleDateFormat("dd/mm/yyyy");
				Date date = DateFor.parse(scanner.next());
				System.out.println("Enter a subscription date in (dd/mm/yy) format :");
				Date subdate = DateFor.parse(scanner.next());
				System.out.println("Enter a subscription expirey date in (dd/mm/yy) format :");
				Date expdate = DateFor.parse(scanner.next());
				System.out.println("Enter a subscription status:");
				String sub=scanner.next();
				System.out.println("=========Enter Address Details=======:");
				System.out.println("Enter Address 1:");
				String address1 = scanner.next();
				System.out.println("Enter Address 2:");
				String address2 = scanner.next();
				System.out.println("Enter city:");
				String city = scanner.next();
				System.out.println("Enter state:");
				String state = scanner.next();
				System.out.println("Enter pincode:");
				int pin = scanner.nextInt();

				UserAddress o=new UserAddress(address1,address2,city,state,pin);
				Users user = new Users(pass, fname, lname,mobile,email,date,subdate,expdate,sub,o);

				int id = service1.register(user);
				System.out.println("User registration succesfully done with the id: " + id);
				break;
			}
			
			case 2:
			{
				List<Users> l=service1.viewAllUsers();
				for(Users u:l) {
					System.out.println(u);
				}
				break;
			}
			
			case 3:
			{
				System.out.println("Enter your User id for deleting your account :");
				int id =scanner.nextInt();
				try {
					service1.deleteUser(id);
					System.out.println("User Account succesfully deleted with the id: " + id);
				}
				catch(UserNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			
			case 4:

				break;
				
			case 5:
			{
				System.out.println("======Enter details for Address updation=====");
				System.out.println("Enter address id:");
				int id=scanner.nextInt();
				//5scanner.next();
				System.out.println("Enter Address 1:");
				String address1 = scanner.next();
				System.out.println("Enter Address 2:");
				String address2 = scanner.next();
				System.out.println("Enter city:");
				String city = scanner.next();
				System.out.println("Enter state:");
				String state = scanner.next();
				System.out.println("Enter pincode:");
				int pin = scanner.nextInt();

				UserAddress obj=new UserAddress(id,address1,address2,city,state,pin);
				try {
					int x=service2.updateUserAddressDetails(obj);
					System.out.println("User Adress updated successfully for address id: " + x);
				}
				catch( AddressNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			
			case 6:
			{
				System.out.println("Enter Address id for address deletion");
				int id=scanner.nextInt();
				try {
					int x=service2.deleteUserAddress(id);;
					System.out.println("User Adress updated successfully for address id: " + x);
				}
				catch( AddressNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			
			case 7:
			{
				System.out.println("Enter user id:");
				int id=scanner.nextInt();
				try {
					UserAddress ad=service2.viewAddressByUserId(id);
					System.out.println(ad);
				}
				catch(AddressNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			}
			
			case 8:{
				List<UserAddress> l=service2.viewUserAddressList();
				for(UserAddress u:l) {
					System.out.println(u);
				}
				break;
			}
			
			}
			System.out.println("want to continue performing database operations:(y/n)");
			if(scanner.next().equals("n")){
				break;
			}
		}
	}
}
