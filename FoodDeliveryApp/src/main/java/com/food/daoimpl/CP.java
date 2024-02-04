package com.food.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class CP {
		
	static Connection con;
	private static String user = "root";
	private static String password = "root";
	private static String url = "jdbc:mysql://localhost:3306/foodapp";
	
	public static Connection createC() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 

			if(con==null) {
				con= DriverManager.getConnection(url, user, password);
				return con;
			}else {
				return con;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return con;
		
	}
	
}
