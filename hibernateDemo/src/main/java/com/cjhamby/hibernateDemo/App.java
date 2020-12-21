package com.cjhamby.hibernateDemo;

import java.util.List;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
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
    	// HibernateUtil is a static factory class, no need to instantiate
    	HibernateUtil.createFactory();
        addDemo(new Player("Chris", 30, "The Cary Scaries"));
        addDemo(new Player("Christopher", 31, "Gov. Cooper's Troopers"));
        readDemo();
        deleteDemo(30);
        readDemo();
    }
    
    
    /**
     * Add a player to the database table
     * HibernateUtil factory knows how to convert object to table
     * 
     * @param p1 player object to add
     */
    public static void addDemo(Player p1) {
    	System.out.println("\nAdding "
    				+ p1.getPlayerName() + " (#" + p1.getPlayerNum() + ") to table");
    	Session session = HibernateUtil.getSession();
    	Transaction transaction = session.beginTransaction();
    	
    	try {
    		session.save(p1);
        	transaction.commit();
        }catch(Exception e) {
        	System.out.println(e);
        	System.out.println("that player already exists");
        } finally {
        	session.close();
        }
    }
    
    /**
     * get all player objects from the table 
     */
    public static void readDemo() {
    	System.out.println("\nReading from Player Table");
    	Session session = HibernateUtil.getSession();
    	List<Player> data = (List<Player>)(session.createQuery("from Player").list());
    	for(Player p: data) {
    		System.out.println(p);
    	}
    	session.close();
    }
    
    
    /**
     * delete a player from the database,
     * using the value annotated as "id" in the Player class
     * 
     * @param playerId id of the player to delete
     */
    public static void deleteDemo(int playerId) {
    	Session session = HibernateUtil.getSession();
    	Transaction transaction = session.beginTransaction();
    	System.out.println("\nDeleting player id #" + playerId + " from table");
    	
    // since playerID is the hibernate ID, use it to refer to players
    	try {
    		Player temp = (Player)session.get(Player.class, playerId);
    		session.delete(temp);
    		transaction.commit();
    		System.out.println(temp.getPlayerName() + " was removed successfully");
    	}catch(Exception e) {
    		System.out.println("could not delete");
    	}finally {
    		session.close();
    	}
    }
    
    
}
