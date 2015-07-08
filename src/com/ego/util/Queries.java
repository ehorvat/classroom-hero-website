package com.ego.util;


public class Queries {
	
	/////////////////////////////////
	//
	// Registration SQL Queries
	//
	/////////////////////////////////
	
	public static final String GENERAL_INFO_NO_PASS = "INSERT INTO \"User\"(\"fname\", \"lname\", \"role\", \"registerTime\") VALUES(?,?,?,?) RETURNING \"uid\"";
	
	public static final String SCHOOL_LOOKUP = "Select \"schoolId\" from \"School\" where \"school_name\"=?";
	
	public static final String INSERT_SCHOOL = "INSERT INTO \"School\"(\"school_name\") VALUES(?)";
	
	public static final String GENERAL_INFO = "INSERT INTO \"User\"(\"fname\", \"lname\", \"role\", \"registerTime\", \"salt\", \"hash\", \"isActivated\", \"email\", \"schoolId\") VALUES(?,?,?,?,?,?,?,?,?) RETURNING \"uid\"";
	
	public static final String REGISTER_TEACHER = "INSERT INTO \"Teacher_User\"(\"tid\") VALUES(?)";
	
	public static final String REGISTER_STUDENT = "INSERT INTO \"Student_User\"(\"sid\") VALUES(?)";
	
	public static final String REGISTER_LEAGUE = "INSERT INTO \"League\"(\"leagueName\", \"hasStamps\", \"leagueAdmin\", \"leagueCode\") VALUES(?,?,?,?)";
	
	public static final String CREATE_CLASS = "INSERT INTO \"Class\"(\"grade\" , \"tid\") VALUES(?,?)";
	
	public static final String STUDENT_CLASS_MATCH = "INSERT INTO \"Student_Class_Match\" (\"cid\", \"sid\") VALUES(?,?)";
	
	public static final String PRIVATE_LEAGUE_LOOKUP = "SELECT \"leagueName\", \"leagueId\", \"hasStamps\" FROM \"League\" WHERE \"leagueCode\"=?";
	
	public static final String PUBLIC_LEAGUE_LOOKUP = "SELECT \"leagueName\", \"leagueId\", \"hasStamps\" FROM \"League\" WHERE \"leagueName\"=?";
	
	public static final String TEACHER_LEAUGE_MATCH = "UPDATE \"Teacher_User\" SET \"leagueId\"=? WHERE \"tid\"=?";
	
	public static final String LEAVE_LEAGUE = "Update \"Teacher_User\" set \"leagueId\"=NULL where \"tid\"=?";
	
	public static final String DELETE_USER_DATA = "DELETE FROM \"User\" WHERE \"uid\"=?";
	
	public static final String DELETE_TEACHER_DATA = "DELETE FROM \"Teacher_User\" WHERE \"tid\"=?";
	
	public static final String GET_LEAGUE_STATUS = "SELECT \"leagueId\" FROM \"Teacher_User\" WHERE \"tid\"=?";

	public static final String CHECK_EMAIL = "Select \"email\" from \"User\" where \"email\"=?";
	
	public static final String INSERT_TOKEN = "Update \"User\" set \"token\"=?, \"expires\"=? where \"email\"=?";
	
	public static final String TOKEN_LOOKUP = "Select \"token\", \"expires\", \"email\" from \"User\" where \"token\"=?";
	
	public static final String DELETE_PASSWORD = "Update \"User\" set \"salt\"=NULL, \"hash\"=NULL where \"email\"=?";

	public static final String UPDATE_PASSWORD = "Update \"User\" set \"salt\"=?, \"hash\"=? where \"email\"=?";
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/////////////////////////////////
	//
	// Login SQL Queries
	//
	/////////////////////////////////
	
	public static final String VERIFY_EMAIL = "Select \"email\", \"uid\" from \"User\" where \"email\"=?";
	
	public static final String VERIFY_EMAIL_ADMIN = "Select \"email\", \"leagueAdmin\" from \"League\" where \"email\"=?";
	
	public static final String VERIFY_PASSWORD = "Select \"uid\", \"hash\", \"role\" from \"User\" where \"uid\"=?";
	
	public static final String GET_TEACHER_DATA = "Select \"uid\", \"fname\", \"lname\", \"email\", \"isActivated\", \"stamp\", \"coins\", \"leagueId\", \"hasStamps\", \"schoolId\" from \"User\", \"Teacher_User\" where \"uid\"=? AND \"uid\" = \"tid\"";
	
	public static final String GET_ADMIN_DATA = "Select \"uid\", \"fname\", \"lname\", \"email\", \"leagueId\", \"hasStamps\", \"leagueAdmin\", \"leagueCode\", \"schoolId\" from \"User\", \"League\" where \"uid\"=? AND \"uid\" = \"leagueAdmin\"";
	
	
	///////////////////////////////////////
	//
	// Teacher and Admin profile queries
	//
	///////////////////////////////////////
	
	public static final String GET_STUDENTS = "Select DISTINCT \"cid\", \"uid\", \"fname\", \"lname\", \"totalCoins\", \"currentCoins\" from \"User\""
			+ "INNER JOIN \"Student_User\" ON uid = \"Student_User\".sid "
			+ "NATURAL JOIN \"Student_Class_Match\" WHERE \"cid\"=?";
	
	public static final String GET_ITEMS = "Select \"cost\", \"name\" From \"Item\" where uid=?";
	
	public static final String GET_CATEGORIES = "Select \"Category\" From \"CategoryCodes\" where uid=?";
	
	public static final String GET_CLASS_JAR = "Select \"total\", \"progress\", \"name\" From \"Class_Jar\" where uid=?";
	
	public static final String GET_CLASS = "Select \"cid\", \"grade\" From \"Class\" where \"tid\"=?";
	
	
	///////////////////////////////////////
	//
	// Admin-specific queries
	//
	///////////////////////////////////////
	
	public static final String GET_LEAGUE_TEACHERS = "Select \"uid\", \"fname\", \"lname\", \"tid\", \"email\", \"isActivated\", \"serial\", \"coins\", \"leagueId\", \"hasStamps\" from \"User\", \"Teacher_User\" where \"leagueAdmin\"=?";
	
	public static final String GET_ADMIN_ITEMS = "Select \"cost\", \"name\" From \"Item\" where \"storeType\"=3 AND uid=?";
	
	public static final String GET_ADMIN_CATEGORIES = "Select \"Category\" From \"CategoryCodes\" where \"categoryType\"=3 AND uid=?";
	
	public static final String GET_ADMIN_JAR = "Select \"total\", \"progress\", \"name\" From \"Class_Jar\" where \"type\"=3 AND uid =?";
	
	public static final String GET_PRIVACY_SETTING = "Select \"private\", \"leagueCode\" from \"League\" where \"leagueAdmin\"=?";
	
	public static final String CHANGE_PRIVACY_SETTINGS = "Update \"League\" set \"private\"=? where \"leagueAdmin\"=?";
	
	///////////////////////////////////////
	//
	// CRUD operations for items
	//
	///////////////////////////////////////
	
	public static final String INSERT_ITEM = "INSERT INTO \"Item\" (\"cost\", \"name\", \"storeType\", uid) VALUES (?,?,?,?)";
	
	public static final String DELETE_ITEM = "DELETE FROM \"Item\" where uid=? AND name=?";
	
	public static final String UPDATE_ITEM = "UPDATE \"Item\" set name=?, cost=? where uid=? AND name=?";
	
	
	///////////////////////////////////////
	//
	// CRUD operations for categories
	//
	///////////////////////////////////////

	public static final String INSERT_CATEGORY = "Insert into \"CategoryCodes\" (\"Category\", \"categoryType\", uid) VALUES(?,?,?)";

	public static final String DELETE_CATEGORY = "DELETE FROM \"CategoryCodes\" where uid=? AND \"Category\"=?";

	public static final String UPDATE_CATEGORY = "UPDATE \"CategoryCodes\" set \"Category\"=? where uid=? AND \"Category\"=?";
	
	///////////////////////////////////////
	//
	// CRUD operations for jar
	//
	///////////////////////////////////////

	public static final String INSERT_JAR = "INSERT INTO \"Class_Jar\" (\"total\", \"progress\", \"name\", \"type\", \"uid\") VALUES (?,?,?,?,?)";

	public static final String DELETE_JAR = "DELETE FROM \"Class_Jar\" where uid=?";

	public static final String UPDATE_JAR = "UPDATE \"Class_Jar\" set \"name\"=?, \"total\"=? where uid=?";
	
	
	///////////////////////////////////////
	//
	// CRUD operations for students
	//
	///////////////////////////////////////
	
	public static final String INSERT_STUDENT_USER ="INSERT INTO \"User\"(\"fname\", \"lname\", \"role\", \"registerTime\", \"schoolId\") VALUES(?,?,?,?,?) RETURNING \"uid\"";

	public static final String INSERT_STUDENT = "INSERT INTO \"Student_User\"(\"sid\") VALUES(?)";

	public static final String DELETE_STUDENT = "DELETE FROM \"User\" where uid=?";

	public static final String UPDATE_STUDENT = "UPDATE \"User\" set \"fname\"=?, \"lname\"=? where \"uid\"=?";
	
	
	///////////////////////////////////////
	//
	// WebHelperImpl Queries
	//
	///////////////////////////////////////
	
	public static final String GET_LEAGUES = "Select \"leagueName\", \"private\" From \"League\"";
	
	public static final String GET_LEAGUE_DATA = "Select \"uid\", \"fname\", \"lname\", \"leagueName\", \"leagueId\" from \"User\", \"League\" where \"leagueId\"=? AND \"leagueAdmin\"=\"uid\"";
	
	public static final String GET_ALL_SCHOOLS = "Select \"school_name\" From \"School\"";
	
}
