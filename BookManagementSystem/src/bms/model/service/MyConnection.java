package bms.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bookdb","root","");
		return conn;
	}
}
