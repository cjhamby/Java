package com.cjhamby.hibernateDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * a test to show that importing H2 is possible with Maven
 * 
 * @author cjham
 *
 */
public class MyConnection {
	
	private Connection con = null;
	
	public MyConnection() {
		try {
		// load the driver 
			Class.forName("org.h2.Driver");
			
		// establish connection
			con = DriverManager.getConnection("jdbc:h2:~/test","sa","");
			System.out.println("connected");
			
		} catch(Exception e) {
			System.out.println(e);
		} 
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public boolean closeConnection() {
		try {
			con.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
