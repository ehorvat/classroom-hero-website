package com.ego.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.ego.interfaces.JarInterface;
import com.ego.util.Queries;

public class JarEntity extends Entity implements JarInterface{
	
	int total;
	
	int progress;
	
	private PreparedStatement preparedStatement;
	
	Connection connection;
	
	public JarEntity(String name, int total) throws NamingException, SQLException, ServletException{
		super(name);
		this.total = total;
		this.progress = 0;
		this.connection = DBConnection.dbc();
	}
	
	
	public JarEntity(String name) throws NamingException, SQLException, ServletException{
		super(name);
		this.connection = DBConnection.dbc();
	}

	@Override
	public void createJar(HttpSession session) throws SQLException {
		
		int role = (Integer) session.getAttribute("role");
		System.out.println(role);
		
		preparedStatement = connection.prepareStatement(Queries.INSERT_JAR);

		preparedStatement.setInt(1, total);
		preparedStatement.setInt(2, progress);
		preparedStatement.setString(3, name);
		preparedStatement.setInt(4, role);
		preparedStatement.setInt(5, (Integer) session.getAttribute("uid"));
		preparedStatement.execute();
		preparedStatement.close();
		
	}

	@Override
	public void deleteJar(HttpSession session) throws SQLException {

		preparedStatement = connection.prepareStatement(Queries.DELETE_JAR);
		preparedStatement.setInt(1, (Integer) session.getAttribute("uid"));
		preparedStatement.execute();
		preparedStatement.close();
		
	}

	@Override
	public void changeJar(HttpSession session) throws SQLException {
		
		preparedStatement = connection.prepareStatement(Queries.UPDATE_JAR);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, total);
		preparedStatement.setInt(3, (Integer) session.getAttribute("uid"));

		preparedStatement.execute();
		preparedStatement.close();
		
	}


}
