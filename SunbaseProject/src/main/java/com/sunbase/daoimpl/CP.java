package com.sunbase.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CP {
		
	static Connection con;
	private static String user = "root";
	private static String password = "root";
	private static String url = "jdbc:mysql://localhost:3306/sunbase";
	
	public static Connection createC() throws SQLException {
		
		if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                throw new SQLException("Error creating connection: " + e.getMessage());
            }
        }
        return con;
		
	}
	
}
