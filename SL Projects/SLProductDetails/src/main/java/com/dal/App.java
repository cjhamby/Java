package com.dal;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.dao.Product;
import com.utility.HibernateUtility;

/**
 * methods to access data in the database
 * 
 * @author cjham
 */
public class App {

	/**
	 * open the hibernate factory
	 */
	public static void openFactory() {
		try {
			HibernateUtility.createFactory();
			System.out.println("connected");
		} catch (Exception e) {
			System.out.println("error opening factory: " + e);
		}

	}

	/**
	 * close the hibernate factory
	 */
	public static void closeFactory() {
		try {
			HibernateUtility.closeFactory();
			System.out.println("factory closed");
		} catch (Exception e) {
			System.out.println("error closing factory: " + e);
		}
	}

	/**
	 * add a product to the database
	 * 
	 * @param p1 the product to add
	 */
	public static void addProduct(Product p1) {
		Session session = HibernateUtility.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(p1);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Add Product Failed");
			System.out.println(e);
		} finally {
			session.close();
		}
	}

	/**
	 * get the POJO Product
	 * 
	 * @param id the product to get
	 * @return the product object
	 */
	public static Product getProduct(int id) {
		System.out.println("getting product id: " + id);
		Product product = null;

		try (Session sess = HibernateUtility.getSession()) {
			sess.beginTransaction();
			product = sess.find(Product.class, id);

		} catch (Exception e) {
			System.out.println("Could not get product: " + e);
		}

		return product;
	}

	/**
	 * get details about a product in the database
	 * 
	 * @param id the product ID to look for
	 * @return details in string format
	 */
	public static String getDetailsAboutProduct(int id) {
		System.out.println("getting details for product id: " + id);
		String details = "";

		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			Product product = session.find(Product.class, id);
			tx.commit();
			if (product == null) {
				details = "product not found";
			} else {
				details = product.toString();
			}
		} catch (Exception e) {
			details = "could not get details about product";
			System.out.println("exception in get details: " + e);
		}
		return details;
	}
}
