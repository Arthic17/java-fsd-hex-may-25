package com.ecom.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtility {
		   private String url="jdbc:mysql://localhost:3306/fsd_hex_may_25";
     	   private String userDB="root";
		   private String passDB="mysql";
		   private String driver="com.mysql.cj.jdbc.Driver";
		   private Connection con;
		   public Connection connect() {
			   try {
				   Class.forName(driver);
			   }catch(ClassNotFoundException e) {
				   System.out.println(e.getMessage());
			   }
			   try {
				   
			   con=DriverManager.getConnection(url,userDB,passDB);
			   }catch(SQLException e) {
				   System.out.println(e.getMessage());
			   }
			   return con;
		   }
		   public void close() {
			   try {
				   if(!con.isClosed())
					   con.close();
			   }catch(SQLException e) {
				   System.out.println(e.getMessage());
			   }
		   }
}

