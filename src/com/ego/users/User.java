package com.ego.users;
public class User {

	String fname;
	String lname;
	String pass;
	String confPass;
	int userType;
	String salt;
	String hashedPassword = null;
	int uid;
	boolean isLoggedIn = false;
	String email;
	String stamp;
	int isActivated;
	int schoolId;
	String schoolName;
	




	




	//////////////////
	//				//
	// ctor			//
	//				//
	//////////////////
	public User(){
		
	}
	
	public User(int uid){
		this.uid = uid;
	}
	
	public User(String fname, String lname, String pass, String confPass, int userType){
		
		//initialize class members
		this.fname = fname;
		this.lname = lname;
		this.pass = pass;
		this.confPass = confPass;
		this.userType = userType;
		this.schoolName = schoolName;
	}
	
	
	
	public User(String fname, String lname, String pass, String confPass, int userType, String schoolName){
		
		//initialize class members
		this.fname = fname;
		this.lname = lname;
		this.pass = pass;
		this.confPass = confPass;
		this.userType = userType;
		this.schoolName = schoolName;
	}


	public User(int uid, String fname, String lname) {
		
		//initialize class members
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;	
	}
	
	public User(int uid, String fname, String lname, int schoolId) {
		
		//initialize class members
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.schoolId = schoolId;
	}
	
	public User(int uid, String fname, String lname, String email) {
		
		//initialize class members
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	
	public User(int uid, String fname, String lname, String email, int schoolId) {
		
		//initialize class members
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.schoolId = schoolId;
	}

	public User(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}

	//////////////////////////
	//						//
	// Getters				//
	//						//
	//////////////////////////

	public String getSchoolName() {
		return schoolName;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public int getUserType() {
		return userType;
	}
	public int getUid(){
		return uid;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public String getConfPass() {
		return confPass;
	}
	public String getPass() {
		return pass;
	}
	public String getSalt() {
		return salt;
	}
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	public String getEmail() {
		return email;
	}
	public int getSchoolId() {
		return schoolId;
	}

	//////////////////////////
	//						//
	// Setters				//
	//						//
	//////////////////////////
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public void setFname(String fname) {
		
		this.fname = fname;
	}
	public void setLname(String lname) {
	
		this.lname = lname;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setSalt(String salt){
		this.salt = salt;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setIsLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
}
