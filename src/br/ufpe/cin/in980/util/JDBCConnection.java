package br.ufpe.cin.in980.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	private String driverName;
	private String password;
	private String username;
	private String url;
	private Connection connection;

	public JDBCConnection(String username, String password, String host,
			String schema) {
		this.driverName = "com.mysql.jdbc.Driver";
		this.password = password;
		this.username = username;
		this.url = "jdbc:mysql://" + host + "/" + schema;
	}

	public void createConnection() {
		try {
			Class.forName(this.driverName);
			this.connection = DriverManager.getConnection(this.url,
					this.username, this.password);
			this.connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void commitTransaction() {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
