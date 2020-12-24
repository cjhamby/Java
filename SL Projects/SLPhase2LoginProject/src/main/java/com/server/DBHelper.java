package com.server;

import org.hibernate.Transaction;
import org.hibernate.Session;

import com.model.Account;
import com.utility.HibernateUtility;

public class DBHelper {
	
	/**
	 * add a new account to the database 
	 * @param uname
	 * @param password
	 * @return was the account added?
	 */
	public static boolean addAccount(String uname, String password) {
		
		// empty fields are not allowed
		if(uname.length() == 0 || password.length() == 0) {
			return false;
		}
		
		// create an account with the given credentials
		Account account = new Account(uname, password);
		
		// try to add it to the server
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(account);
			tx.commit();
			return true;
			
		} catch (Exception e) {
			System.out.println("add account --- " + e);
			return false;
		}
	}
	
	/**
	 * search the database for the account
	 * @param uname		account to search for
	 * @return			search results
	 */
	public static Account getAccount(String uname) {
		try (Session session = HibernateUtility.getSession();) {
			Account account = session.find(Account.class, uname);
			return account;
		} catch (Exception e) {
			System.out.println("get account --- " + e);
			return null;
		}
	}
}
