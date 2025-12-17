
	package com.inventory;
	import java.sql.*;


	public class DBConnection {
	public static Connection getConnection() {
	try {
	return DriverManager.getConnection(
	"jdbc:mysql://localhost:3306/inventory_db",
	"root",
	"Kannanlucky@04"
	);
	} catch (Exception e) {
	e.printStackTrace();
	return null;
	}
	}
	}

