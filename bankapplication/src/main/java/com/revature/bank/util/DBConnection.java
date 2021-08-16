package com.revature.bank.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getDBConnection() {
		Connection connection = null;
		try {
			// 1.loading the driver-type 4 driver
			Class.forName("org.postgresql.Driver");
			//System.out.println("Driver is loaded successfully");
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
			//System.out.println("connected successfully");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return connection;
	}
}