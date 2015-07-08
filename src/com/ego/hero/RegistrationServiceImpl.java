package com.ego.hero;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.ego.exceptions.InvalidEmail;
import com.ego.exceptions.PasswordMisMatch;
import com.ego.interfaces.RegistrationInterface;
import com.ego.users.League;
import com.ego.users.StudentUser;
import com.ego.users.TeacherUser;
import com.ego.users.User;
import com.ego.util.PasswordManager;
import com.ego.util.Queries;

/////////////////////////////////
//
// Persistence Layer
//
/////////////////////////////////

public class RegistrationServiceImpl implements RegistrationInterface {

	private User user;

	private HttpSession session;

	private Connection conn;

	private PreparedStatement preparedStatement = null;

	private ResultSet rs;

	private boolean normalReg = false, leagueReg = false,
			leagueWithPassReg = false;

	private String leaguePass;

	private String leagueName;

	// /////////
	//
	// ctor
	//
	// /////////
	public RegistrationServiceImpl() throws NamingException, SQLException,
			ServletException {
		this.conn = DBConnection.dbc();
	}

	// ///////////////////////////////
	//
	// ctor for admins and teachers
	//
	// ///////////////////////////////
	public RegistrationServiceImpl(User user, String leagueName,
			String leaguePass) throws NoSuchAlgorithmException, SQLException,
			PasswordMisMatch, NamingException, ServletException {
		this.user = user;
		this.conn = DBConnection.dbc();
		this.leagueName = leagueName;
		if (leaguePass != null && leaguePass != "") {
			this.leaguePass = leaguePass;
		}
	}

	// ///////////////////////////////
	//
	// ctor for admins and teachers
	//
	// ///////////////////////////////
	public RegistrationServiceImpl(User user) throws NoSuchAlgorithmException,
			SQLException, PasswordMisMatch, NamingException, ServletException, InvalidEmail {
		this.user = user;
		this.conn = DBConnection.dbc();
		normalReg = true;
		registerUser(user);
	}

	// ///////////////////////////////
	//
	// ctor for students
	//
	// ///////////////////////////////
	public RegistrationServiceImpl(User user, HttpSession session)
			throws NoSuchAlgorithmException, SQLException, PasswordMisMatch,
			NamingException, ServletException, InvalidEmail {
		this.user = user;
		this.conn = DBConnection.dbc();
		this.session = session;
		registerUser(user);
	}

	// /////////////////////////////////////////////
	//
	// ctor for teachers joining a private league
	//
	// /////////////////////////////////////////////
	public RegistrationServiceImpl(User user, String leaguePass, boolean flag)
			throws NoSuchAlgorithmException, SQLException, PasswordMisMatch,
			NamingException, ServletException, InvalidEmail {
		this.user = user;
		this.conn = DBConnection.dbc();
		this.leaguePass = leaguePass;
		leagueWithPassReg = true;
		registerUser(user);
	}

	// ////////////////////////////////////////////
	//
	// ctor for teachers joining a public league
	//
	// ////////////////////////////////////////////
	public RegistrationServiceImpl(User user, String leagueName)
			throws NoSuchAlgorithmException, SQLException, PasswordMisMatch,
			NamingException, ServletException, InvalidEmail {
		this.user = user;
		this.conn = DBConnection.dbc();
		this.leagueName = leagueName;
		leagueReg = true;
		registerUser(user);
	}

	// ///////////////////////////////
	//
	// Insert new user of any type
	//
	// ///////////////////////////////
	public void registerUser(User user) throws NoSuchAlgorithmException,
			PasswordMisMatch, SQLException, InvalidEmail {

		// Check to see if user needs a password (Only teachers and admins)
		if (!(user instanceof StudentUser)) {

			// Before we do anything, first check if email already exists
			if (checkEmail()) {

				// Object for managing passwords
				PasswordManager pm = new PasswordManager();
				if (pm.comparePasswords(user.getPass(), user.getConfPass())) {

					// Secure password
					String hashedPassword = pm.encodePassword(user.getPass());
					user.setSalt(pm.genSalt);
					user.setHashedPassword(hashedPassword);
					

					int schoolId = getSchoolId(user.getSchoolName());
					System.out.println("schoolId : " + schoolId);
					preparedStatement = conn
							.prepareStatement(Queries.GENERAL_INFO);

					// Universal user traits
					prepareGeneralData(schoolId);

					// Users of type league or teacher set passwords
					preparedStatement.setString(5, user.getSalt());
					preparedStatement.setString(6, user.getHashedPassword());
					preparedStatement.setString(8, user.getEmail());
					// Get results from database in a result set
					rs = preparedStatement.executeQuery();

					// Get auto-incremented ID from DB and add to user object
					if (rs.next()) {
						int uid = rs.getInt(1);
						user.setUid(uid);

						if (user instanceof TeacherUser) {

							// Insert teacher-specific information
							registerTeacher();

						} else if (user instanceof League) {

							// Insert admin(or league)-specific information
							registerLeague();
						}
					}

				}
			} else {
				throw new InvalidEmail("invalidEmail");
			}
		} else {
			int schoolId = getSchoolId(user.getSchoolName());
			System.out.println("schoolId : " + schoolId);
			// Since students don't have profiles(yet), no need to manage
			// passwords.
			preparedStatement = conn
					.prepareStatement(Queries.GENERAL_INFO_NO_PASS);

			// Universal user traits
			prepareGeneralData(schoolId);

			rs = preparedStatement.executeQuery();

			// Get auto-incremented ID from DB and add to user object
			if (rs.next()) {
				int uid = rs.getInt(1);
				user.setUid(uid);

				// Insert student-specific information
				registerStudent();

				// Pair new student with teacher's class
				studentClassMatch(user, session);
			}

		}

	}

	private int getSchoolId(String schoolName) throws SQLException {
		
		int schoolId = 0;
		
		preparedStatement = conn.prepareStatement(Queries.SCHOOL_LOOKUP);
		preparedStatement.setString(1, schoolName);
		rs=preparedStatement.executeQuery();
		if(rs.next()){
			schoolId=rs.getInt(1);
		}
		
		return schoolId;
	}

	// ////////////////////////
	//
	// Register a League
	//
	// ////////////////////////
	public void registerLeague() throws SQLException {

		preparedStatement = conn.prepareStatement(Queries.REGISTER_LEAGUE);
		preparedStatement.setString(1, ((League) user).getLeagueName());
		preparedStatement.setBoolean(2, ((League) user).getHasStamps());
		preparedStatement.setInt(3, user.getUid());
		preparedStatement.setString(4, ((League) user).getLeagueCode());

		preparedStatement.execute();

		System.out.println("Successfully added league data");

		preparedStatement.close();

	}

	// ///////////////////////////////////////
	//
	// Register a Teacher to a private League
	//
	// ///////////////////////////////////////
	public void addToPrivateLeague() throws SQLException, PasswordMisMatch {

		if (leaguePass.trim() != null && leaguePass.trim() != "") {

			// Check user input league code vs database
			preparedStatement = conn
					.prepareStatement(Queries.PRIVATE_LEAGUE_LOOKUP);
			preparedStatement.setString(1, leaguePass);

			// Get results
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {

				// if there is a match, instantiate new league object and pair
				// teacher with league
				League league = new League(rs.getString(1), rs.getInt(2),
						rs.getBoolean(3));
				teacherLeagueMatch(league.getLeagueId());

			} else {
				// else throw error to user
				throw new PasswordMisMatch("wrongCode");
			}
		}

	}

	// ///////////////////////////////////////
	//
	// Register a Teacher to a public League
	//
	// ///////////////////////////////////////
	public void addToPublicLeague() throws SQLException, PasswordMisMatch {

		if (leagueName.trim() != null && leagueName.trim() != "") {
			preparedStatement = conn
					.prepareStatement(Queries.PUBLIC_LEAGUE_LOOKUP);
			preparedStatement.setString(1, leagueName);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {

				League league = new League(rs.getString(1), rs.getInt(2),
						rs.getBoolean(3));
				teacherLeagueMatch(league.getLeagueId());

			} else {
				throw new PasswordMisMatch("Server error");
			}
		}

	}

	public void teacherLeagueMatch(int leagueId) throws SQLException {

		preparedStatement = conn.prepareStatement(Queries.TEACHER_LEAUGE_MATCH);
		preparedStatement.setInt(1, leagueId);
		preparedStatement.setInt(2, user.getUid());
		preparedStatement.execute();
		preparedStatement.close();
	}

	// ////////////////////////
	//
	// Register a Teacher
	//
	// ////////////////////////
	public void registerTeacher() throws NoSuchAlgorithmException,
			SQLException, PasswordMisMatch {

		preparedStatement = conn.prepareStatement(Queries.REGISTER_TEACHER);
		preparedStatement.setInt(1, user.getUid());
		preparedStatement.execute();

		// init teacher class
		initClass();

		// init achievements
		initAchievements();

		preparedStatement.close();

		if (leagueReg) {
			// Register to public group
			addToPublicLeague();
		} else if (leagueWithPassReg) {
			// Register to private group
			addToPrivateLeague();
		}

		System.out.println("Successfully added teacher data");

	}

	// ////////////////////////
	//
	// Register a Student
	//
	// ////////////////////////
	public void registerStudent() throws NoSuchAlgorithmException, SQLException {

		preparedStatement = conn.prepareStatement(Queries.REGISTER_STUDENT);
		preparedStatement.setInt(1, user.getUid());
		preparedStatement.execute();

		System.out.println("Successfully added student data");

		preparedStatement.close();

	}

	// ////////////////////////
	//
	// Pair student and class
	//
	// ////////////////////////

	public void studentClassMatch(User student, HttpSession session)
			throws SQLException {

		preparedStatement = conn.prepareStatement(Queries.STUDENT_CLASS_MATCH);
		preparedStatement.setInt(1, (Integer) session.getAttribute("cid"));
		preparedStatement.setInt(2, student.getUid());
		preparedStatement.execute();
		preparedStatement.close();

	}

	public void initAchievements() throws SQLException {

		int goals[] = { 1, 10, 1, 1 };

		String getAids = "Select aid from \"Achievements\"";

		String initAchievement = "INSERT INTO \"Achievement_Progress\" (\"aid\", \"uid\") VALUES(?,?)";

		PreparedStatement preparedStatement = conn.prepareStatement(getAids,
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			PreparedStatement achievement = conn
					.prepareStatement(initAchievement);
			achievement.setInt(1, rs.getInt(1));
			achievement.setInt(2, user.getUid());
			achievement.execute();
			achievement.close();

		}

	}

	public void initClass() throws SQLException {

		preparedStatement = conn.prepareStatement(Queries.CREATE_CLASS);
		preparedStatement.setInt(1, ((TeacherUser) user).getGrade());
		preparedStatement.setInt(2, user.getUid());

		preparedStatement.execute();
		preparedStatement.close();
		System.out.println("Successfully created class with id: "
				+ user.getUid());
	}

	public void prepareGeneralData(int schoolId) throws SQLException {

		
		preparedStatement.setString(1, user.getFname());
		preparedStatement.setString(2, user.getLname());
		preparedStatement.setInt(3, user.getUserType());

		// Generate creation timestamp
		java.sql.Timestamp stamp = new java.sql.Timestamp(
				new java.util.Date().getTime());
		preparedStatement.setTimestamp(4, stamp);
		preparedStatement.setInt(7, 0);
		preparedStatement.setInt(9, schoolId);
	}

	@Override
	public void leaveLeague(int tid) throws SQLException {

		preparedStatement = conn.prepareStatement(Queries.LEAVE_LEAGUE);
		preparedStatement.setInt(1, tid);
		preparedStatement.execute();

	}

	@Override
	public boolean checkEmail() throws SQLException {

		boolean success;

		preparedStatement = conn.prepareStatement(Queries.CHECK_EMAIL);
		preparedStatement.setString(1, user.getEmail());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			success = false;
		} else {
			success = true;
		}

		return success;
	}



}
