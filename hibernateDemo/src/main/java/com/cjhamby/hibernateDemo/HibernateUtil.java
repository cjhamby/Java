package com.cjhamby.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
// the factory's main job is to provide "Sessions" as-needed
	static SessionFactory factory;
	
	static {
		
	// get configuration details from hibernate.cfg.xml 
		Configuration config = new Configuration().configure();
		
	// make sure the class has annotations for hibernate
	    config.addAnnotatedClass(com.cjhamby.hibernateDemo.Player.class);
	    
	/*
	 * the standard service registry manages services that are essential to hibernate
	 * I am not exactly sure why it is made here, though 
	 */
	    StandardServiceRegistryBuilder builder = 
	    		new StandardServiceRegistryBuilder().applySettings(config.getProperties());
	    
	// lastly, create the factory 
	    factory = config.buildSessionFactory(builder.build());
	}
	
	
// provide a new session
	public static Session getSession() {
		Session session = factory.openSession();
		return session;
	}
}
