package com.cjhamby.hibernateDemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * demonstrates creating a hibernate session and crud operations
 * in this example, hibernate automatically creates the table,
 * based on the Player object fields
 * 
 * hibernate.cfg.xml contains a few ways to play with the program
 */
public class App 
{
    public static void main( String[] args )
    {
    	//testH2Connection();
        addDemo(new Player(10, "Chris", 30, "The Cary Scaries"));
        addDemo(new Player(12, "Christopher", 31, "Gov. Cooper's Troopers"));
        readDemo();
        deleteDemo();
        readDemo();
    }
    
    public static void testH2Connection() {
    	new MyConnection().closeConnection();
    }
    
    public static void addDemo(Player p1) {
    	System.out.println("Adding to table");
    	Session session = HibernateUtil.getSession();
    	Transaction transaction = session.beginTransaction();
        session.save(p1);
        transaction.commit();
        session.close();
    }
    
    public static void readDemo() {
    	System.out.println("Reading from Player Table");
    	Session session = HibernateUtil.getSession();
    	List<Player> data = session.createQuery("from Player").list();
    	for(Player p: data) {
    		System.out.println(p);
    	}
    	session.close();
    }
    
    public static void deleteDemo() {
    	Session session = HibernateUtil.getSession();
    	Transaction transaction = session.beginTransaction();
    	System.out.println("Deleting Christopher from table");
    	
    // since playerID is the hibernate ID, use it to refer to players
    	Player temp = (Player)session.get(Player.class, 12);
    	session.delete(temp);
    	transaction.commit();
    	session.close();
    }
    
    
}
