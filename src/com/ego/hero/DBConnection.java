package com.ego.hero;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;

import com.ego.util.DBConnect;

public class DBConnection {
	
	static DBConnect connection;
	int test;
	
	public static Connection dbc() throws NamingException, SQLException,ServletException {
		try {
			connection = new DBConnect("classroomhero", null);

			return connection.connect();
		}

		catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
