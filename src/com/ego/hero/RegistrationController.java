package com.ego.hero;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ego.entities.Jar;
import com.ego.exceptions.InvalidEmail;
import com.ego.exceptions.PasswordMisMatch;
import com.ego.exceptions.RegPassMismatch;
import com.ego.users.League;
import com.ego.users.TeacherUser;
import com.ego.users.User;
import com.ego.util.EmailUtility;
import com.ego.util.Queries;

@Controller
public class RegistrationController {
	RegistrationServiceImpl rs;
	User user;

	static final String PRIVATE = "private";
	static final String PUBLIC = "public";
	String host;
	String port;
	String userName;
	String pass;

	public void init() {
	
	}

	// ////////////////////////////
	// //
	// Add Teacher Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "register/general", method = RequestMethod.POST)
	public @ResponseBody
	void registerTeacher(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException,
			SQLException {
		
		// reads SMTP server setting from web.xml file
		ServletContext context = request.getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		userName = context.getInitParameter("user");
		pass = context.getInitParameter("pass");

		PrintWriter out = null;

		String leaguePass = null;

		String league = null;

		String privacy = null;

		String input = null;

		boolean normalReg = false, leagueReg = false, leagueWithPassReg = false;

		String fname = request.getParameter("fname").trim();
		String lname = request.getParameter("lname").trim();
		String email = request.getParameter("email").trim();
		String pw = request.getParameter("password").trim();
		String confPw = request.getParameter("confpass").trim();
		String schoolName = request.getParameter("schoolName").trim();

		int grade = Integer.parseInt(request.getParameter("radios"));

		// Determine if user is joining private, public, or no league
		if (request.getParameter("school") != null
				&& request.getParameter("school") != "") {

			// If got school from client, check if public or private league.
			input = request.getParameter("school");

			// Extract league and privacy setting from comma-separated string.
			// Also remove white space around strings.
			String[] split = input.split("\\s*,\\s*");

			// Store splitted values into variables
			league = split[0];
			privacy = split[1];

			if (privacy.trim().equals(PRIVATE)) {

				// If a private group, check if actually got a password
				if (request.getParameter("leaguePass") != null
						&& request.getParameter("leaguePass") != "") {
					// Set private league flag
					leaguePass = request.getParameter("leaguePass");
					leagueWithPassReg = true;
				}

			} else if (privacy.trim().equals(PUBLIC)) {

				// Set public league flag
				leagueReg = true;
			}

		} else {
			// No league selected. Normal registration flag set
			normalReg = true;
		}

		try {

			out = response.getWriter();

			final int userType = 1;

			user = new TeacherUser(fname, lname, pw, confPw, email, userType,
					schoolName);
			user.setEmail(email);
			((TeacherUser) user).setGrade(grade);

			if (leagueWithPassReg) {

				// Attempt to add user to a private league
				new RegistrationServiceImpl(user, leaguePass, true);

			} else if (leagueReg) {

				// Attempt to add user to a public league
				new RegistrationServiceImpl(user, league);

			} else if (normalReg) {

				// User does not wish to join a league. Create a single
				// account
				new RegistrationServiceImpl(user);

			}

			out.write("success");

		} catch (SQLException e1) {
			out.write("Server Error");
			e1.printStackTrace();
			deleteUserInfo(user.getUid());
		} catch (NamingException e) {
			e.printStackTrace();
			deleteUserInfo(user.getUid());
		} catch (ServletException e) {
			e.printStackTrace();
			deleteUserInfo(user.getUid());
		} catch (IOException e) {
			e.printStackTrace();
			deleteUserInfo(user.getUid());
		} catch (PasswordMisMatch e) {
			// Failed password message
			// Delete user that tried to register if there was an error
			deleteUserInfo(user.getUid());
			e.printStackTrace();
			out.write(e.getMessage());

		} catch (InvalidEmail e) {
			e.printStackTrace();
			out.write(e.getMessage());
		} finally {

			
			String subject = "Thank you for signing up with Classroom Hero!";
			String content = "<h1>Thank you for your interest in the Classroom Hero Beta!</h1><br><p>We are excited to have you as part of the Team! Click the link below to unlock the power of Classroom Hero!</p><br><a href='http://classroom-hero.com/login.html'>Sign In</a>";

			System.out.println("Email :" + email);
	

				try {
					EmailUtility.sendEmail(host, port, userName, pass, email, subject, content);
					
				} catch (AddressException e) {
					e.printStackTrace();
					out.write("Invalid Email (ex. batman@gmail.com)");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	


			out.close();
			DBConnection.connection.disconnect();
		}
	}

	@RequestMapping(value = "register/league", method = RequestMethod.POST)
	public void registerLeague(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException,
			SQLException, IOException, JSONException {

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String pw = request.getParameter("password");
		String confPw = request.getParameter("confpass");
		String school = request.getParameter("school");
		String creationcode = request.getParameter("creationcode");

		String code = "bearhugawhale";

		PrintWriter out = null;

		try {
			out = response.getWriter();
			if (creationcode.trim().equals(code.trim())) {

				final int userType = 3;
				SecureRandom random = new SecureRandom();

				user = new League(new BigInteger(20, random).toString(32),
						school, true, email, fname, lname, pw, confPw, userType);
				new RegistrationServiceImpl(user);

				out.write("success");

			} else {
				throw new RegPassMismatch("wrongCode");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PasswordMisMatch e) {
			out.write(e.getMessage());
			e.printStackTrace();
		} catch (RegPassMismatch e) {
			e.printStackTrace();
			out.write(e.getMessage());
		} catch (InvalidEmail e) {
			e.printStackTrace();
			out.write(e.getMessage());
		} finally {
			out.close();
			DBConnection.connection.disconnect();
		}

	}

	@RequestMapping(value = "/joinLeague", method = RequestMethod.POST)
	public ModelAndView joinLeagueFromProfile(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException,
			SQLException, IOException, JSONException {

		PrintWriter out = null;

		String leaguePass = null;

		String league = null;

		String privacy = null;

		String input = null;

		boolean leagueReg = false, leagueWithPassReg = false;

		Jar schoolJar = null;

		League newLeague = null;

		HttpSession session = request.getSession();

		User user = null;

		ModelAndView model = new ModelAndView();

		// Determine if user is joining private, public, or no league
		if (request.getParameter("school") != null
				&& request.getParameter("school") != "") {

			// If got school from client, check if public or private league.
			input = request.getParameter("school");

			// Extract league and privacy setting from comma-separated string.
			// Also remove white space around strings.
			String[] split = input.split("\\s*,\\s*");

			// Store splitted values into variables
			league = split[0];
			privacy = split[1];

			if (privacy.trim().equals(PRIVATE)) {

				// If a private group, check if actually got a password
				if (request.getParameter("leaguePass") != null
						&& request.getParameter("leaguePass") != "") {
					// Set private league flag
					leaguePass = request.getParameter("leaguePass");
					leagueWithPassReg = true;
				}

			} else if (privacy.trim().equals(PUBLIC)) {

				// Set public league flag set
				leagueReg = true;
			}
		}

		try {
			out = response.getWriter();
			user = new User((Integer) session.getAttribute("uid"));
			if (leagueWithPassReg) {

				// Attempt to add user to a private league
				RegistrationServiceImpl reg = new RegistrationServiceImpl(user,
						league, leaguePass);

				reg.addToPrivateLeague();

				WebHelperImpl helper = new WebHelperImpl();

				int leagueId = helper.getLeagueStatus(session);

				if (leagueId != 0) {
					// If the teacher is in a league, find league and admin data
					newLeague = helper.getLeagueData(leagueId);

					if (league != null) {
						LeagueProfile profile = new LeagueProfile(newLeague);
						schoolJar = profile.getSchoolJar();

						String fullName = newLeague.getFname().trim() + " "
								+ newLeague.getLname().trim();

						model.addObject("status", "in");

						model.addObject("leagueName", newLeague.getLeagueName());

						model.addObject("leader", fullName);

						model.addObject("progress", schoolJar.getJarProgress()
								+ "/");

						model.addObject("total", schoolJar.getJarTotal());

						model.setViewName("league");
					}

				}

			} else if (leagueReg) {

				// Attempt to add user to a public league
				RegistrationServiceImpl reg = new RegistrationServiceImpl(user,
						league, leaguePass);

				reg.addToPublicLeague();

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PasswordMisMatch e) {
			out.write(e.getMessage());
			e.printStackTrace();
		} finally {
			out.close();
			DBConnection.connection.disconnect();
		}
		return model;
	}

	@RequestMapping(value = "/leaveLeague", method = RequestMethod.GET)
	public ModelAndView leaveLeague(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException,
			SQLException, IOException, JSONException {

		HttpSession session = request.getSession();

		ModelAndView model = new ModelAndView();

		// Get the teacher's ID
		int tid = (Integer) session.getAttribute("uid");

		try {
			RegistrationServiceImpl service = new RegistrationServiceImpl();
			service.leaveLeague(tid);

			WebHelperImpl helper = new WebHelperImpl();

			ArrayList<HashMap<String, Object>> leagues = helper.getLeagues();

			model.addObject("leagues", leagues);
			model.setViewName("league");
			// request.setAttribute("leagues", leagues);

		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}
		return model;
	}

	private void deleteUserInfo(int uid) {

		Connection conn = null;
		try {
			conn = DBConnection.dbc();

			PreparedStatement preparedStatement = conn
					.prepareStatement(Queries.DELETE_TEACHER_DATA);
			preparedStatement.setInt(1, uid);
			preparedStatement.execute();

			preparedStatement.close();

			preparedStatement = conn.prepareStatement(Queries.DELETE_USER_DATA);
			preparedStatement.setInt(1, uid);
			preparedStatement.execute();

			preparedStatement.close();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
