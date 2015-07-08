package com.ego.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

public interface StudentInterface {
	
	////////////////////////////////////////////////////////
	//
	// Methods for teachers to add/delete/change students
	// 
	////////////////////////////////////////////////////////
	
	void createStudent(HttpSession session) throws SQLException;
	
	void deleteStudent(HttpSession session) throws SQLException;
	
	void changeStudent(HttpSession session) throws SQLException;
	
	////////////////////////////////////////////////////////
}
