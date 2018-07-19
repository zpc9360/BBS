package com.bbs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DbUtil {

	private static DbUtil instance = new DbUtil();
	
	private static LinkedList<Connection> connections;
	
	private static String cloudDb = "rm-bp1nx7g1tf56oawx0no.mysql.rds.aliyuncs.com";

	static {
		try {
			Connection c;
			connections = new LinkedList<Connection>();
			Class.forName("com.mysql.jdbc.Driver");
			for (int i = 0; i < 31; i++) {
				c = DriverManager.getConnection("jdbc:mysql://10.0.60.4:3306/bbs?useSSL=false", "user",
						"123456");
				connections.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private DbUtil() {
	}

	public static DbUtil getInstance() {
		return instance;
	}

	public Connection getConnetion() {
		final Connection conn = connections.removeFirst();
		return conn;
	}

	public void closeConnection(Connection connection) {
		connections.add(connection);
	}
	
	public void destoryConnection() {
		for(Connection c : connections) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
