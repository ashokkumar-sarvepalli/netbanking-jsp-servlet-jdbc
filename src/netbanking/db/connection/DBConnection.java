package netbanking.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static String url = "jdbc:mysql://localhost:3306/internetbanking";
	private static String userName = "root";
	private static String password = "admin";

	private DBConnection() {

	}

	private static Connection con = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
		} catch (Exception ex) {
			System.out.println("Problem in getting connection");
		}
	}

	public static Connection getConnection() {
		return con;
	}

}
