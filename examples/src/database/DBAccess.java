package database;

import java.sql.*;
import oracle.*;

public class DBAccess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		foo();
	}
	
	public static void foo() {
		//OracleDataSource ods = new OracleDataSource();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(
			        "jdbc:oracle:thin:@localhost:1521:xe","system","oracle"
					);
			Statement state = con.createStatement();
			ResultSet rs=state.executeQuery("select * from student");
			con.close();
			System.out.println("Finished");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
			System.out.println(e);
		}
	}

}
