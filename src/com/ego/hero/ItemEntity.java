package com.ego.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.ego.interfaces.ItemInterface;
import com.ego.util.Queries;

public class ItemEntity extends Entity implements ItemInterface{
	
	int cost;
	String original;
	
	private static final int TEACHER_ROLE = 1;
	
	private static final int ADMIN_ROLE = 3;
	
	private PreparedStatement preparedStatement;
	
	Connection connection;

	public ItemEntity(int cost, String name) throws NamingException, SQLException, ServletException{
		super(name);
		this.cost = cost;
		connection = DBConnection.dbc();
	}

	public ItemEntity(int cost, String name, String original) throws NamingException, SQLException, ServletException{
		super(name);
		this.cost = cost;
		this.original = original;
		connection = DBConnection.dbc();
	}
	
	@Override
	public void createItem(HttpSession session) throws SQLException {
			
			int role = (Integer) session.getAttribute("role");
			System.out.println(role);
			
				
			//Insert teacher item
			preparedStatement = connection.prepareStatement(Queries.INSERT_ITEM);
			preparedStatement.setInt(1, cost);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, role);
			preparedStatement.setInt(4, (Integer) session.getAttribute("uid"));
			preparedStatement.execute();
			preparedStatement.close();	
		
		
				
	}

	@Override
	public void deleteItem(HttpSession session) throws SQLException {
		
	
			preparedStatement = connection.prepareStatement(Queries.DELETE_ITEM);
			preparedStatement.setInt(1, (Integer) session.getAttribute("uid"));
			preparedStatement.setString(2, name);

			preparedStatement.execute();
			preparedStatement.close();

		
	}

	@Override
	public void changeItem(HttpSession session) throws SQLException {

	
			preparedStatement = connection.prepareStatement(Queries.UPDATE_ITEM);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, cost);
			preparedStatement.setInt(3, (Integer) session.getAttribute("uid"));
			preparedStatement.setString(4, original);

			preparedStatement.execute();
	
	}

	
	
}
