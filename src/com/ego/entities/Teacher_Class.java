package com.ego.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ego.util.DBConnect;

public class Teacher_Class {
	int grade;
	int cid;
	int tid;

	boolean success = false;
	
	DBConnect connection;
	Connection conn;
	PreparedStatement preparedStatement = null;
	
	public Teacher_Class(int tid, int grade){
		this.grade = grade;
		this.tid = tid;
	}
	public boolean initClass() throws SQLException{
		//Establish database connection
		connection = new DBConnect("postgres", null);
		conn = connection.connect();
		
		String teacherSql = "INSERT INTO \"Class\"(\"grade\" , \"tid\", \"schoolName\") VALUES(?,?,?)";
		
	
			preparedStatement = conn.prepareStatement(teacherSql);
			preparedStatement.setInt(1, grade);
			preparedStatement.setInt(2, tid);
			preparedStatement.setString(3, "Strawberry");
			
			preparedStatement.execute();
			success = true;
			System.out.println("Successfully created class with id: " + tid);
	
			if (preparedStatement != null) {
				
					preparedStatement.close();
				
			}
 
			if (conn != null) {
				connection.disconnect();
			}
		
		return success;
	}
	public int getGrade(){
		return grade;
	}

	public int getTid(){
		return tid;
	}
}
