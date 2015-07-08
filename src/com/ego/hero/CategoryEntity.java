package com.ego.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.ego.interfaces.CategoryInterface;
import com.ego.util.Queries;

public class CategoryEntity extends Entity implements CategoryInterface{
	
	private PreparedStatement preparedStatement;
	
	private Connection connection;
	
	private String original;
	
	public CategoryEntity(String name) throws NamingException, SQLException, ServletException{
		super(name);
		connection = DBConnection.dbc();
	}
	
	public CategoryEntity(String name, String original) throws NamingException, SQLException, ServletException{
		super(name);
		connection = DBConnection.dbc();
		this.original = original;
	}

	@Override
	public void createCategory(HttpSession session) throws SQLException {
		
		int role = (Integer) session.getAttribute("role");
		
		preparedStatement = connection.prepareStatement(Queries.INSERT_CATEGORY);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, role);
		preparedStatement.setInt(3, (Integer) session.getAttribute("uid"));
		preparedStatement.execute();
		preparedStatement.close();
		
	}

	@Override
	public void deleteCategory(HttpSession session) throws SQLException {
		
		preparedStatement = connection.prepareStatement(Queries.DELETE_CATEGORY);
		preparedStatement.setInt(1, (Integer) session.getAttribute("uid"));
		preparedStatement.setString(2, name);

		preparedStatement.execute();
		preparedStatement.execute();
		preparedStatement.close();
		
	}

	@Override
	public void changeCategory(HttpSession session) throws SQLException {
		
		preparedStatement = connection.prepareStatement(Queries.UPDATE_CATEGORY);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, (Integer) session.getAttribute("uid"));
		preparedStatement.setString(3, original);

		preparedStatement.execute();
		preparedStatement.close();
	}


	
}
