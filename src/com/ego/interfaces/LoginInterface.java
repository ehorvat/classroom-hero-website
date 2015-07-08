package com.ego.interfaces;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import com.ego.exceptions.PasswordMisMatch;
import com.ego.users.User;

public interface LoginInterface {
	
	void auth() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, PasswordMisMatch;
		
	void getTeacherData(int uid) throws SQLException, NoSuchAlgorithmException;
	
	void getAdminData(int uid) throws SQLException;
	
	User getUser();
	
	
}
