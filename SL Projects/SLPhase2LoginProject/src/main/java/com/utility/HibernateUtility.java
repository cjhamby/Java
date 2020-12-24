package com.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {

	// the factory's main job is to provide "Sessions" as-needed
	static SessionFactory factory;
	static {

		// this gets configuration details from hibernate.cfg.xml
		Configuration config = new Configuration().configure();
		config.addAnnotatedClass(com.model.Account.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties())
				.build();

		try {
			factory = config.buildSessionFactory(registry);
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			System.out.println("caught error in hibernate utility: " + e);
		}
	}

	public static boolean isCreated() {
		return factory.isOpen();
	}
	
	public static Session getSession() {
		return factory.openSession();
	}

	public static void closeFactory() {
		factory.close();
	}

	/*
	 * references this class to instantiate the factory object
	 */
	public static boolean createFactory() {
		return factory.isOpen();
	}
}
