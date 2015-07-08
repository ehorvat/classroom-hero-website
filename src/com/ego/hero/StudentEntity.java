package com.ego.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.ego.interfaces.StudentInterface;
import com.ego.users.StudentUser;
import com.ego.util.Queries;

public class StudentEntity extends Entity implements StudentInterface{
	
	int totalCoins;
	String fname;
	String lname;
	String original;
	int sid;
	
	private PreparedStatement preparedStatement;
	
	private Connection connection;
	
	public StudentEntity(String fname, String lname, int totalCoins) throws NamingException, SQLException, ServletException{
		this.fname = fname;
		this.lname = lname;
		this.totalCoins = totalCoins;
		this.connection = DBConnection.dbc();
	}
	
	public StudentEntity(String fname, String lname, int totalCoins, String original) throws NamingException, SQLException, ServletException{
		this.fname = fname;
		this.lname = lname;
		this.totalCoins = totalCoins;
		this.connection = DBConnection.dbc();
		this.original = original;
	}

	public StudentEntity(String fname, String lname, String original) throws NamingException, SQLException, ServletException{
		this.fname = fname;
		this.lname = lname;
		this.original = original;
		this.connection = DBConnection.dbc();
	}
	
	public StudentEntity(String fname, String lname) throws NamingException, SQLException, ServletException{
		
		super(fname, lname);
		
		this.fname = fname;
		this.lname = lname;
		this.connection = DBConnection.dbc();
	}


	@Override
	public void createStudent(HttpSession session) throws SQLException {

		preparedStatement = connection.prepareStatement(Queries.INSERT_STUDENT_USER);
		preparedStatement.setString(1, fname);
		preparedStatement.setString(2, lname);
		preparedStatement.setInt(3, 2);
		//preparedStatement.setString(4, pm.encodePassword(student.getPass()));

		// Store timestamp
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(
				new java.util.Date().getTime());
		preparedStatement.setTimestamp(4, sqlDate);
		preparedStatement.setInt(5, (Integer) session.getAttribute("schoolId"));
		ResultSet rs = preparedStatement.executeQuery();
		System.out.println("before");
		if(rs.next()){
			System.out.println("here");
			sid = rs.getInt(1);
			System.out.println(sid);
		}
		preparedStatement.close();
		
		/*ArrayList<StudentUser> students = getStudents(session);
		int sid = 0;

		for (int i = 0; i < students.size(); i++) {
			StudentUser student = students.get(i);
			if ((student.getFname().trim() + " " + student.getLname().trim()).equals(original)) {
				sid = student.getUid();
			}
		}*/
		
		preparedStatement = connection.prepareStatement(Queries.INSERT_STUDENT);
		preparedStatement.setInt(1, sid);
		preparedStatement.execute();
		preparedStatement.close();
		
		
		preparedStatement = connection.prepareStatement(Queries.STUDENT_CLASS_MATCH);
		System.out.println("Adding studentClassMatch");
		System.out.println(session.getAttribute("uid"));
		preparedStatement.setInt(1, (Integer)session.getAttribute("cid"));
		preparedStatement.setInt(2, sid);
		preparedStatement.execute();
		preparedStatement.close();

		
	}

	@Override
	public void deleteStudent(HttpSession session) throws SQLException {
		
		ArrayList<StudentUser> students = getStudents(session);
		

		for (int i = 0; i < students.size(); i++) {
			StudentUser student = students.get(i);
			System.out.println(student.getFname().trim() + " " + student.getLname().trim());
			if ((student.getFname().trim() + " " + student.getLname().trim()).equals(name.trim())) {
				System.out.println("name match");
				sid = student.getUid();
			}
		}
		
		preparedStatement = connection.prepareStatement(Queries.DELETE_STUDENT);
		preparedStatement.setInt(1, sid);

		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public void changeStudent(HttpSession session) throws SQLException {
		
		ArrayList<StudentUser> students = getStudents(session);
		int sid = 0;

		for (int i = 0; i < students.size(); i++) {
			StudentUser student = students.get(i);
			if ((student.getFname().trim() + " " + student.getLname().trim()).equals(original)) {
				sid = student.getUid();
			}
		}

		preparedStatement = connection.prepareStatement(Queries.UPDATE_STUDENT);
		preparedStatement.setString(1, fname);
		preparedStatement.setString(2, lname);
		preparedStatement.setInt(3, sid);

		preparedStatement.execute();
		preparedStatement.close();

	}
	
	
	public ArrayList<StudentUser> getStudents(HttpSession session) throws SQLException {

		String findDetails = "Select DISTINCT \"cid\", \"uid\", \"fname\", \"lname\", \"totalCoins\" from \"User\""
				+ "INNER JOIN \"Student_User\" ON uid = \"Student_User\".sid "
				+ "NATURAL JOIN \"Student_Class_Match\" WHERE \"cid\"=?";

		ArrayList<StudentUser> list = new ArrayList<StudentUser>();
		
			ResultSet rs = null;

			preparedStatement = connection.prepareStatement(findDetails);

			preparedStatement.setInt(1, (Integer) session.getAttribute("cid"));
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				StudentUser student = new StudentUser();
				student.setUid(rs.getInt(2));
				student.setFname(rs.getString(3));
				student.setLname(rs.getString(4));
				list.add(student);
			}
			System.out.println(list.size());
			preparedStatement.close();


		return list;
	}


}
