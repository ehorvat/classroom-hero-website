package com.ego.interfaces;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.ego.exceptions.InvalidEmail;
import com.ego.exceptions.PasswordMisMatch;
import com.ego.users.User;

public interface RegistrationInterface {
	
	void registerUser(User user) throws SQLException, NoSuchAlgorithmException, PasswordMisMatch, InvalidEmail;
	
	void registerStudent() throws NoSuchAlgorithmException, SQLException;
	
	void registerTeacher() throws NoSuchAlgorithmException, SQLException, PasswordMisMatch;
	
	void registerLeague() throws SQLException;
	
	void addToPrivateLeague() throws SQLException, PasswordMisMatch;
	
	void addToPublicLeague() throws SQLException, PasswordMisMatch;
	
	void teacherLeagueMatch(int leagueId) throws SQLException;
	
	void initClass() throws SQLException;
	
	void initAchievements() throws SQLException;
	
	void studentClassMatch(User student,HttpSession session) throws SQLException;
	
	void prepareGeneralData(int schoolId) throws SQLException;

	void leaveLeague(int tid) throws SQLException;
	
	boolean checkEmail() throws SQLException;

}
