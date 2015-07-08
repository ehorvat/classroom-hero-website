package com.ego.hero;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;

import com.ego.exceptions.InvalidEmail;
import com.ego.exceptions.PasswordMisMatch;
import com.ego.exceptions.TokenExpired;
import com.ego.util.EmailUtility;
import com.ego.util.PasswordManager;
import com.ego.util.Queries;



public class Profile {
	

	String email;
	
	//////////////////
	//
	// ctor 
	// 
	//////////////////

	public Profile() {

	}
	
	public Profile(String email) {
		this.email=email;
	}
	
	
	public void verifyEmail(Connection conn, ServletContext context)
			throws InvalidEmail, SQLException {
		
		String host;
		String port;
		String userName;
		String pass;
	
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		userName = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
		
		
		PreparedStatement ps;
		ResultSet rs;
	
			ps = conn.prepareStatement(Queries.CHECK_EMAIL);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()){
				//email found
				String email = rs.getString(1);
				
				//Generate random token
				SecureRandom random = new SecureRandom();
				String bigint = new BigInteger(100, random).toString(32);
				
				//Create time stamp 30mins ahead of current time
				java.sql.Timestamp expiration = new java.sql.Timestamp(
						new java.util.Date(System.currentTimeMillis()+30*60*1000).getTime());


				
				ps = conn.prepareStatement(Queries.INSERT_TOKEN);
				ps.setString(1,bigint.trim());
				ps.setTimestamp(2, expiration);
				ps.setString(3,email.trim());
				ps.execute();
				
				String subject = "Classroom Hero Password Reset";
				String content = "<p>To reset your Classroom Hero account password, please click the link below.</p>"
						+ "<br><a href='http://www.classroom-hero.com/changePassword?token="+ bigint +"'>Reset Password</a>";
				try {
					EmailUtility.sendEmail(host, port, userName, pass, email, subject, content);
					
				} catch (AddressException e) {
					e.printStackTrace();

				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				throw new InvalidEmail();
			}


		
	}
	
	public void verifyToken(Connection conn, String token) throws SQLException, TokenExpired{
		PreparedStatement ps;
		ResultSet rs;
		
		ps = conn.prepareStatement(Queries.TOKEN_LOOKUP);
		ps.setString(1, token);
		rs = ps.executeQuery();
		if(rs.next()){
			
			this.email = rs.getString(3);
			
			//Token found. Verify current time is before the expiration
			java.sql.Timestamp expiration = rs.getTimestamp(2);
			if(expiration.before(new Date())){
				//If token expired, throw TokenExpired exception
				throw new TokenExpired();
			}
		}
	}
	
	public void changePassword(Connection conn, String p1, String p2, String email) throws NoSuchAlgorithmException, SQLException, PasswordMisMatch{
		//First check if passwords match
		PasswordManager pm = new PasswordManager();
		if (pm.comparePasswords(p1, p2)) {

			// Secure password
			String hashedPassword = pm.encodePassword(p1);
			String salt = pm.genSalt;
			PreparedStatement ps;
			
			ps = conn.prepareStatement(Queries.UPDATE_PASSWORD);
			ps.setString(1, salt);
			ps.setString(2, hashedPassword);
			ps.setString(3, email);
			ps.execute();
			
		}
	}
}
