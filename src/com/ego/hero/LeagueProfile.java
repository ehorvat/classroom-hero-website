package com.ego.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.ego.entities.Category;
import com.ego.entities.Item;
import com.ego.entities.Jar;
import com.ego.interfaces.AdministrativeInterface;
import com.ego.interfaces.LeagueInterface;
import com.ego.interfaces.LeagueSettingsInterface;
import com.ego.users.League;
import com.ego.users.StudentUser;
import com.ego.users.TeacherUser;
import com.ego.util.Queries;

public class LeagueProfile extends Profile implements AdministrativeInterface,LeagueInterface,LeagueSettingsInterface {

	private League league;

	private Connection conn;

	private PreparedStatement preparedStatement = null;
	
	public LeagueProfile() throws NamingException, SQLException, ServletException {
		conn = DBConnection.dbc();
	}
	
	public LeagueProfile(League league) throws NamingException, SQLException,
			ServletException {
		this.league = league;
		conn = DBConnection.dbc();
	}

	/*@Override
	public ArrayList<ArrayList<ArrayList<StudentUser>>> getTeacherStudentPairs()
			throws SQLException {

		// Outermost arraylist
		ArrayList parent = new ArrayList<>();

		// Middle arraylist
		ArrayList<ArrayList<StudentUser>> teachers = new ArrayList<>();

		// Innermost arraylist
		ArrayList<StudentUser> students = null;

		// Variable to hold teacher's class data
		int classData[];

		int cid;

		int grade;

		preparedStatement = conn.prepareStatement(Queries.GET_LEAGUE_TEACHERS);
		preparedStatement.setInt(1, league.getUid());
		ResultSet rs = preparedStatement.executeQuery();

		// Read all teachers and corresponding students into arraylist
		while (rs.next()) {

			// Instantiate a new teacher object per database row
			TeacherUser teacher = new TeacherUser(rs.getInt(1),
					rs.getString(2), rs.getString(3), rs.getString(5),
					rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9),
					rs.getBoolean(10));

			// For each teacher, retrieve the classID
			classData = getClass(teacher.getUid());

			cid = classData[1];
			grade = classData[2];

			// Set the teacher's class ID
			teacher.setCid(cid);
			teacher.setGrade(grade);

			// Get students in current teacher's class
			students = getStudent(cid);

			// Add student list to current teacher
			teachers.add(students);

			// And new teacher to the arraylist of teachers
			parent.add(teacher);
		}

		return parent;
	}*/

	@Override
	public ArrayList<StudentUser> getStudent(int cid) throws SQLException {

		// Arraylist to hold student users
		ArrayList<StudentUser> students = new ArrayList<StudentUser>();

		// Get students by class ID
		PreparedStatement getStudents = conn
				.prepareStatement(Queries.GET_STUDENTS);
		getStudents.setInt(1, cid);
		ResultSet results = getStudents.executeQuery();

		while (results.next()) {
			// For each DB row create a new student and store in students
			// arraylist
			StudentUser student = new StudentUser(results.getString(3),
					results.getString(4), results.getInt(5));
			students.add(student);
		}

		return students;
	}

	@Override
	public ArrayList getStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList getItems() throws SQLException {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		preparedStatement = conn.prepareStatement(Queries.GET_ADMIN_ITEMS);
		preparedStatement.setInt(1, league.getUid());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cost", rs.getString(1).trim());
			map.put("name", rs.getString(2).trim());

			list.add(map);

			System.out.println(list.size());

		}
		preparedStatement.close();

		return list;
	}

	@Override
	public ArrayList getCategories() throws SQLException {

		ArrayList<String> list = new ArrayList<String>();

		preparedStatement = conn.prepareStatement(Queries.GET_ADMIN_CATEGORIES);
		preparedStatement.setInt(1, league.getUid());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			list.add(rs.getString(1).trim());
		}
		preparedStatement.close();

		return list;
	}

	@Override
	public ArrayList<ArrayList> initProfile() throws SQLException {

		ArrayList parent = new ArrayList<Object>();

		ArrayList<Item> items = getItems();

		ArrayList<Category> categories = getCategories();

		parent.add(items);
		parent.add(categories);

		return parent;
	}

	private int[] getClass(int uid) throws SQLException {

		int cid = 0;
		int grade = 0;

		// Get class id using user id
		PreparedStatement getCid = conn.prepareStatement(Queries.GET_CLASS);
		getCid.setInt(1, uid);

		ResultSet result = getCid.executeQuery();

		if (result.next()) {

			// Teacher has a class. Get class ID from result set
			cid = result.getInt(1);
			grade = result.getInt(2);

		}
		int classData[] = { cid, grade };
		return classData;
	}

	@Override
	public Jar getSchoolJar() throws SQLException {

		Jar jar = null;

		preparedStatement = conn.prepareStatement(Queries.GET_ADMIN_JAR);
		preparedStatement.setInt(1, league.getUid());
		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			jar = new Jar();
			jar.setJarTotal(rs.getInt(1));
			jar.setJarProgress(rs.getInt(2));
			jar.setJarName(rs.getString(3));
		}

		preparedStatement.close();

		return jar;

	}

	@Override
	public void changePrivacy(String privacy, HttpSession session) throws SQLException {
		
		//Get user id
		int uid = (Integer) session.getAttribute("uid");
		
		preparedStatement = conn.prepareStatement(Queries.CHANGE_PRIVACY_SETTINGS);
		
		//Check if user is setting account to private or public
		if(privacy.equals("private")){
			System.out.println("Setting to private");
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, uid);
		}else if(privacy.equals("public")){
			System.out.println("Setting to public");
			preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(2, uid);
		}
		
		preparedStatement.execute();
		
		preparedStatement.close();

	}
	
	public League getLeague(){
		return this.league;
	}
	
	
}
