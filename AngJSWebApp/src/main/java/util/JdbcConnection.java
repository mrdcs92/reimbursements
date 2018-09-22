package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcConnection {

	private static Connection conn;

	public static void main(String[] args) {
		conn = getConnection();
		System.out.println(conn);
	}

	public static Connection getConnection() {

		if (conn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				Properties props = new Properties();
				FileInputStream in;
				in = new FileInputStream("D:/MyRepo/AngJSWebApp/AngJSWebApp/src/main/resources/connection.properties");
				props.load(in);

				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");

				conn = DriverManager.getConnection(url, username, password);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}
		}
		return conn;
	}

}
