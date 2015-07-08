package com.ego.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ego.users.StudentUser;
import com.ego.users.TeacherUser;

public class AuthService {

	int uid;
	DBConnect connection;
	Connection conn;
	PreparedStatement preparedStatement = null;
	ResultSet rs;

	
	public AuthService(){
		//Establish database connection

		connection = new DBConnect("postgres", null);
		conn = connection.connect();
	}
	

	
	//////////////////////////
	//						//
	// Authenticate Teacher	//
	//						//
	//////////////////////////
	public boolean auth(String username, String password, int type, Connection connection2) throws SQLException{
		boolean isValid = false;
		if(type == 1){
			
			String lookUp = "Select \"uid\", \"tid\", \"email\", \"hash\" from \"User\", \"Teacher_User\" where \"email\"=? AND \"uid\" = \"tid\"";
			try {
				preparedStatement = conn.prepareStatement(lookUp);
				preparedStatement.setString(1, username);
				rs = preparedStatement.executeQuery();
			
				if(rs.next()){
					uid = rs.getInt(1);
					String hashedpw = rs.getString(4);
					System.out.println("Querying password: " + password);
					PasswordManager decoder = new PasswordManager();
					boolean valid = decoder.validatePassword(password, hashedpw);
					if(valid){
						isValid = true;
						return isValid;
					}else{
						System.out.println("Error occured in validation");
					}
				}else{
					System.out.println("Error when querying");
					return false;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
		}else if(type == 2){
			String[] splited = username.split("\\s+");
			if(splited.length != 2){
				return false;
			}
			String lookUp = "Select \"uid\", \"sid\", \"fname\", \"lname\", \"hash\"" +
							"from \"User\", \"Student_User\"" +
							"where \"fname\"=? AND \"lname\"=? AND \"uid\" = \"sid\"";
			try {
				preparedStatement = conn.prepareStatement(lookUp);
				preparedStatement.setString(1, splited[0].trim());
				preparedStatement.setString(2, splited[1].trim());
				rs = preparedStatement.executeQuery();
			
				if(rs.next()){
					uid = rs.getInt(1);
					String hashedpw = rs.getString(5);
					System.out.println("Querying password: " + hashedpw);
					PasswordManager decoder = new PasswordManager();
					boolean valid = decoder.validatePassword(password, hashedpw);
					if(valid){
						isValid = true;
						return isValid;
					}else{
						System.out.println("Error occured in validation");
					}
				}else{
					System.out.println("Error when querying");
					isValid = false;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			
		}
		return isValid;
	}
	//////////////////////////
	//						//
	// Init class memebers	//
	//						//
	//////////////////////////
	public TeacherUser initUserClass(){
		TeacherUser teacher = new TeacherUser();
		teacher.setUid(uid);
		
		String findDetails = "Select \"uid\", \"fname\", \"lname\", \"role\", \"tid\", \"email\" from \"User\", \"Teacher_User\" where \"uid\"=? AND \"uid\" = \"tid\"";
		String getTeacherClass = "Select \"cid\" From \"Class\" where \"tid\"=?";
		try {
			preparedStatement = conn.prepareStatement(findDetails);
			preparedStatement.setInt(1, teacher.getUid());
			rs = preparedStatement.executeQuery();
			if(rs.next()){
				teacher.setFname(rs.getString(2));
				teacher.setLname(rs.getString(3));
				teacher.setUserType(rs.getInt(4));
				teacher.setEmail(rs.getString(5));
				teacher.setIsLoggedIn(true);

				System.out.println("Initialized " + teacher.getFname().trim() + "'s user details");
			
			}
			
			preparedStatement = conn.prepareStatement(getTeacherClass);
			preparedStatement.setInt(1, teacher.getUid());
			rs = preparedStatement.executeQuery();
			if(rs.next()){
				teacher.setCid(rs.getInt(1));
				System.out.println("Adding class ID " + rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return teacher;
	}
	public StudentUser initStudentUserClass() {
		StudentUser student = new StudentUser();
		student.setUid(uid);
		String findDetails = "Select \"uid\", \"fname\", \"lname\", \"role\", \"totalCoins\", \"currentCoins\", \"lvl\", \"progress\", \"lvlUpAmount\", \"sid\"" +
							 "from \"User\", \"Student_User\" " +
							 "where \"uid\"=? AND \"uid\" = \"sid\"";
					
		try {
			preparedStatement = conn.prepareStatement(findDetails);
			preparedStatement.setInt(1, student.getUid());
			rs = preparedStatement.executeQuery();
			if(rs.next()){
				student.setFname(rs.getString(2));
				student.setLname(rs.getString(3));
				student.setUserType(rs.getInt(4));
				student.setTotalCoins(rs.getInt(5));
				student.setCurrentCoins(rs.getInt(6));
				student.setLvl(rs.getInt(7));
				student.setProgress(rs.getInt(8));
				student.setLvlUpAmount(rs.getInt(9));
				student.setIsLoggedIn(true);
				System.out.println("Initialized " + student.getFname().trim() + "'s user details");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return student;
	
	}
	 public boolean isValidEmailAddress(String emailAddress){  
		   String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
		   CharSequence inputStr = emailAddress;   
		   Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
		   Matcher matcher = pattern.matcher(inputStr);  
		   return matcher.matches();  
		    
		 }  
	
	//////////////////////////
	//						//
	// Init session vars	//
	//						//
	//////////////////////////
	public void initTeacherSessionVars(TeacherUser teacher, HttpSession session) {
		session.setAttribute("uid", teacher.getUid());
		session.setAttribute("fname", teacher.getFname());
		session.setAttribute("lname", teacher.getLname());
		session.setAttribute("role", teacher.getUserType());
		session.setAttribute("email", teacher.getEmail());
		session.setAttribute("isLoggedIn", teacher.getIsLoggedIn());
		session.setAttribute("cid", teacher.getCid());
	}
	public void initStudentSessionVars(StudentUser student, HttpSession session){
		session.setAttribute("uid", student.getUid());
		session.setAttribute("fname", student.getFname());
		session.setAttribute("lname", student.getLname());
		session.setAttribute("role", student.getUserType());
		session.setAttribute("isLoggedIn", student.getIsLoggedIn());
		session.setAttribute("totalCoins", student.getTotalCoins());
		session.setAttribute("currentCoins", student.getCurrentCoins());
		session.setAttribute("lvl", student.getLvl());
		session.setAttribute("progress", student.getProgress());
		session.setAttribute("lvlUpAmount", student.getLvlUpAmount());
	}
	
	public String setCookies(HttpServletResponse response){
		// Generate this key And save In database And Set as cookies
		UUID generateId = UUID.randomUUID();
		String userToken = generateId.toString();
		Cookie cookie = new Cookie ("userToken", userToken);
		//Set the required cookies age
		cookie.setMaxAge(365 * 24 * 60 * 60);
		//Then add the cookies
		response.addCookie(cookie);
		return userToken;

	}
	public void updateRememberMeId(String userToken, int uid) throws SQLException{
		String teacherSql = "UPDATE \"User\" set \"rememberMeId\"=? where \"uid\"=?";
		preparedStatement = conn.prepareStatement(teacherSql);
		preparedStatement.setString(1, userToken);
		preparedStatement.setInt(2, uid);
		preparedStatement.execute();
		if (conn != null) {
			connection.disconnect();
		}
	}



	
	
}
