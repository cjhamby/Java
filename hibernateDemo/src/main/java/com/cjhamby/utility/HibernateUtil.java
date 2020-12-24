package com.cjhamby.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * A class to encapsulate the Hibernate factory
 * 
 * @author cjham
 *
 */
public class HibernateUtil {

// the factory's main job is to provide "Sessions" as-needed
	static SessionFactory factory;

	static {

		// this gets configuration details from hibernate.cfg.xml
		Configuration config = new Configuration().configure();

		// tell hibernate about the class, using annotations
		config.addAnnotatedClass(com.cjhamby.hibernateDemo.Player.class);

		/*
		 * the standard service registry manages services that are essential to
		 * hibernate
		 * I am not exactly sure why it is made here, though
		 */
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());

		// lastly, create the factory
		factory = config.buildSessionFactory(builder.build());
	}

	// provide a new session
	public static Session getSession() {
		return factory.openSession();
	}

	/*
	 * this causes the factory to instantiate the factory is actually created by the
	 * static block above the static block itself is called by the first reference
	 * to the class (ie this method)
	 */
	public static boolean createFactory() {
		return factory.isOpen();
	}
}
