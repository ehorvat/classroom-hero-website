package com.ego.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.ServletException;

import com.ego.entities.Jar;
import com.ego.interfaces.AdministrativeInterface;
import com.ego.interfaces.TeacherInterface;
import com.ego.users.TeacherUser;
import com.ego.util.Queries;

/////////////////////////////////
//
// Persistence Layer
//
/////////////////////////////////

public class TeacherProfile extends Profile implements AdministrativeInterface, TeacherInterface {

	private TeacherUser teacher;

	private Connection conn;

	private PreparedStatement preparedStatement = null;
	
	
	public TeacherProfile()throws NamingException, SQLException, ServletException {
		conn = DBConnection.dbc();
	}

	public TeacherProfile(TeacherUser teacher)throws NamingException, SQLException, ServletException {
		System.out.println("creating teacher profile");
		this.teacher = teacher;
		conn = DBConnection.dbc();
	}
	
	

	public ArrayList getStudents() throws SQLException {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		ResultSet rs = null;

		preparedStatement = conn.prepareStatement(Queries.GET_STUDENTS);

		preparedStatement.setInt(1, teacher.getCid());
		rs = preparedStatement.executeQuery();

		while (rs.next()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fname", rs.getString(3).trim());
			map.put("lname", rs.getString(4).trim());
			map.put("current", rs.getInt(6));
			map.put("total", rs.getInt(5));
			list.add(map);
		}

		preparedStatement.close();

		return list;

	}

	@Override
	public ArrayList getItems() throws SQLException {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		preparedStatement = conn.prepareStatement(Queries.GET_ITEMS);
		preparedStatement.setInt(1, teacher.getUid());

		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cost", rs.getString(1).trim());
			map.put("name", rs.getString(2).trim());

			list.add(map);

			System.out.println(list.size());

		}
		preparedStatement.close();

		return list;
	}

	@Override
	public ArrayList getCategories() throws SQLException {

		ArrayList<String> list = new ArrayList<String>();

			preparedStatement = conn.prepareStatement(Queries.GET_CATEGORIES);
			preparedStatement.setInt(1, teacher.getUid());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1).trim());
			}
			preparedStatement.close();

		return list;
	}
	
	@Override
	public Jar getClassJar() throws SQLException {
		
		Jar jar = null;

			preparedStatement = conn.prepareStatement(Queries.GET_CLASS_JAR);
			preparedStatement.setInt(1, teacher.getUid());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				jar = new Jar();
				jar.setJarTotal(rs.getInt(1));
				jar.setJarProgress(rs.getInt(2));
				jar.setJarName(rs.getString(3));
			}
			
			preparedStatement.close();


		return jar;
	
	}

	@Override
	public ArrayList<ArrayList> initProfile() throws SQLException {

		ArrayList parent = new ArrayList<Object>();

		// Get student list
		ArrayList students = getStudents();

		// Get item list
		ArrayList items = getItems();

		// Get category list
		ArrayList categories = getCategories();
		
		parent.add(students);
		parent.add(items);
		parent.add(categories);

		return parent;
	}





}
