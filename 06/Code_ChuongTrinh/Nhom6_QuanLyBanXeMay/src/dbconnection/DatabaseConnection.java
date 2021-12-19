package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static String userName = "";
	public static String password = "";
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLBX";
		
		Connection conn = DriverManager.getConnection(url, userName, password);
		
		return conn;
	}
}
