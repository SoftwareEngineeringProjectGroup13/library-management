package dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
	public static Connection getConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://localhost;database=Library";
		String username = "sa";
		String password = "sa";
		Connection con = DriverManager.getConnection(connectionUrl, username, password);
		return con;
	}
}
