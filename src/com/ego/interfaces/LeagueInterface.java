package com.ego.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ego.entities.Jar;
import com.ego.users.StudentUser;
import com.ego.users.TeacherUser;

public interface LeagueInterface {


	
	///////////////////////////////////////////////////////////
	//
	// Admin (League) specific method to retrieve all teachers
	// in admin's league
	// 
	///////////////////////////////////////////////////////////

	//ArrayList<ArrayList<ArrayList<StudentUser>>> getTeacherStudentPairs() throws SQLException;
	
	ArrayList<StudentUser> getStudent(int cid) throws SQLException;
	
	Jar getSchoolJar() throws SQLException;

	////////////////////////////////////////////////////////
	
	
}
