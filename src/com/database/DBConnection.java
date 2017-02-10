package com.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost/iam-core-ref";
	String db_uname = "root";
	String db_pass = "";

	public Connection getConnetion() throws Exception{
			Class.forName(driver);
			return DriverManager.getConnection(url, db_uname, db_pass);
	}
}
