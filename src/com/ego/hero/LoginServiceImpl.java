package com.ego.hero;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;

import com.ego.exceptions.PasswordMisMatch;
import com.ego.interfaces.LoginInterface;
import com.ego.users.League;
import com.ego.users.TeacherUser;
import com.ego.users.User;
import com.ego.util.PasswordManager;
import com.ego.util.Queries;

public class LoginServiceImpl implements LoginInterface{
	
	private int type; 
	
	private User user;
	
	private String username;
	
	private String password;
	
	private Connection conn;
	
	private PreparedStatement preparedStatement = null;
	
	private ResultSet rs;
	
	private static final int TEACHER_ROLE = 1;
	
	private static final int STUDENT_ROLE = 2;
	
	private static final int ADMIN_ROLE = 3;
	
	/////////////////
	//
	// ctor
	//
	/////////////////
	
	public LoginServiceImpl(String username, String password) throws NamingException, SQLException, ServletException, NoSuchAlgorithmException, InvalidKeySpecException, PasswordMisMatch{
		this.username = username;
		this.password = password;
		conn = DBConnection.dbc();
		auth();
	}

	public void auth() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, PasswordMisMatch {
			//Find user by email
			preparedStatement = conn.prepareStatement(Queries.VERIFY_EMAIL);
			preparedStatement.setString(1, username);
			rs = preparedStatement.executeQuery();
			if(rs.next()){
				//User found. Verify password.		
				preparedStatement = conn.prepareStatement(Queries.VERIFY_PASSWORD);
				preparedStatement.setInt(1, rs.getInt(2));
				rs = preparedStatement.executeQuery();
					
				if(rs.next()){
					
					String hashedPass = rs.getString(2);
					PasswordManager decoder = new PasswordManager();
					boolean success = decoder.validatePassword(password, hashedPass);
					if(!success){
						throw new PasswordMisMatch("Invalid Credentials");
					}else{
						int role = rs.getInt(3);
						
						if(role == 1){
							
							//Teacher is logging in. Retrieve teacher-specific information
							int uid = rs.getInt(1);
							getTeacherData(uid);
							
						}else if(role ==3){
							//Admin  is logging in. Retrieve admin-specific information
							int uid = rs.getInt(1);
							getAdminData(uid);
						}
						
					}
					
				}	
			}else{
				throw new PasswordMisMatch("Invalid Credentials");
			}
	}


	@Override
	public void getTeacherData(int uid) throws SQLException, NoSuchAlgorithmException {
		System.out.println("getting teacher data");
		int cid = getClassId(uid);
		
		preparedStatement = conn.prepareStatement(Queries.GET_TEACHER_DATA);
		preparedStatement.setInt(1, uid);
		rs = preparedStatement.executeQuery();
		if(rs.next()){
			
			//Instantiate user of type teacher
			user = new TeacherUser(uid, rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9), cid, rs.getInt(10));
			
		}
	}

	@Override
	public void getAdminData(int uid) throws SQLException {
		preparedStatement = conn.prepareStatement(Queries.GET_ADMIN_DATA);
		preparedStatement.setInt(1, uid);
		rs = preparedStatement.executeQuery();
		if(rs.next()){
			
			//Instantiate user of type admin (league)
			user = new League(uid, rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6), rs.getString(8), rs.getInt(9));
			
		}
	}
	
	private int getClassId(int uid) throws SQLException {
		
		int cid = 0;
		System.out.println("in get class id" + uid);
		//Get class id using user id
		preparedStatement = conn.prepareStatement(Queries.GET_CLASS);
		preparedStatement.setInt(1, uid);
		rs = preparedStatement.executeQuery();
		if(rs.next()){
			//Teacher has a class. Get class ID from result set
			
			cid = rs.getInt(1);
			System.out.println("class id: " + cid);
		}
		
		return cid;
	}

	@Override
	public User getUser() {
		System.out.println("getting user");
		return this.user;
	}
	
	public int getType(){
		return this.type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
}
