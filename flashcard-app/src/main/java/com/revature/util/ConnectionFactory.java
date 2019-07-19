package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String url = "jdbc:oracle:thin:"
			+ "@jcol-sql-demo-db.chueiwozbnfz.us-east-1.rds.amazonaws.com"
			+ ":1521:ORCL";
	private static final String username = "jcol190624";
	private static final String password = "MyDBPass123$";
	
	public static Connection getConnection() throws SQLException {
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		return DriverManager.getConnection(url, username, password);
	}
	
}
