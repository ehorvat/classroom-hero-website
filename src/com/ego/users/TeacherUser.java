package com.ego.users;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ego.util.DBConnect;

public class TeacherUser extends User {
	
	String email;
	
	String serial;
	
	int cid;
	
	int grade;
	
	int isActivated;
	
	int coins;
	
	int leagueId;
	
	boolean hasStamps;
	
	boolean success = false;

	//////////////////
	//				//
	// ctor			//
	//				//
	//////////////////
	public TeacherUser(){
		
	}
	
	public TeacherUser(int uid){
		this.uid = uid;
	}
	
	public TeacherUser(int uid, int cid){
		this.uid = uid;
		this.cid = cid;
	}
	
	public TeacherUser(String fname, String lname, String pass, String confPass, String email, int userType, String schoolName) throws NoSuchAlgorithmException {
		super(fname, lname, pass, confPass, userType, schoolName);
		
		
	}
	
	public TeacherUser(int uid, String fname, String lname, String email, int isActivated, String serial, int coins, int leagueId, boolean hasStamps, int cid, int schoolId) throws NoSuchAlgorithmException {
		super(uid, fname, lname, schoolId);
		
		this.email = email;
		this.isActivated = isActivated;
		this.serial = serial;
		this.coins = coins;
		this.leagueId = leagueId;
		this.hasStamps = hasStamps;
		this.cid = cid;
		
	}	
	
	public TeacherUser(int uid, String fname, String lname, String email, int isActivated, String serial, int coins, int leagueId, boolean hasStamps, int cid) throws NoSuchAlgorithmException {
		super(uid, fname, lname);
		
		this.email = email;
		this.isActivated = isActivated;
		this.serial = serial;
		this.coins = coins;
		this.leagueId = leagueId;
		this.hasStamps = hasStamps;
		this.cid = cid;
		
	}	
	
	public TeacherUser(int uid, String fname, String lname, String email,int isActivated, String serial, int coins, int leagueId, boolean hasStamps) {
		
		this.email = email;
		this.isActivated = isActivated;
		this.serial = serial;
		this.coins = coins;
		this.leagueId = leagueId;
		this.hasStamps = hasStamps;
	}
	//////////////////////////
	//						//
	// Setters				//
	//						//
	//////////////////////////
	public void setEmail(String email){
		this.email = email;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public void setGrade(int grade){
		this.grade = grade;
	}
	
	//////////////////////////
	//						//
	// Getters				//
	//						//
	//////////////////////////
	public String getEmail(){
		return email;
	}
	public int getCid() {
		return cid;
	}
	public int getGrade(){
		return grade;
	}

}
