package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection conn = null;
	static {
		// step 1
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection makeConnection() {
		// step 2
		String connectionUrl = "jdbc:oracle:thin:@database-1.ckocq7g7ox5w.us-east-2.rds.amazonaws.com:1521:orcl"; // protocol, ipaddress, port
		String userName = "BMS";
		String password = "bms2024";
		try {
			if(conn == null) {
				conn = DriverManager.getConnection(connectionUrl, userName, password);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection() {
		try {
			// step 5
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
