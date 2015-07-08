package com.ego.hero;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ego.entities.Jar;
import com.ego.users.League;
import com.ego.users.TeacherUser;
import com.ego.util.EmailUtility;

@Controller
public class WebController {
	
	private static final String STUDENT_ENTITY = "list";

	private static final String ITEM_ENTITY = "itemList";

	private static final String CATEGORY_ENTITY = "categoriesList";
	
	private static final String[] teacher_keys = { STUDENT_ENTITY, ITEM_ENTITY, CATEGORY_ENTITY };
	
	private static final String[] admin_keys = { ITEM_ENTITY, CATEGORY_ENTITY };
	
	private boolean reloadTeacherPage = false, reloadAdminPage = false;

	// map index.html to WEB-INF/jsp/index.jsp
	@RequestMapping("/*")
	protected void directToIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("/");
	}
	
	@RequestMapping("/contact")
	public void contact(HttpServletRequest request,HttpServletResponse response){
		// Get request variables
		String name = null;
		String email = null;
		String message = null;
		
		name = request.getParameter("name").trim();
		email = request.getParameter("email").trim();
		message = request.getParameter("message").trim();
		
		System.out.println("Name: " + name + " Email: " + email + " Message: " + message); 
		
		ServletContext context = request.getServletContext();
		
		String host;
		String port;
		String userName;
		String pass;
	
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		userName = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
		
		try {
			EmailUtility.sendEmail(host, port, userName, pass, "eric.horvat@gmail.com", name + " - " + email, message);
			
			response.setContentType("text/html;charset=UTF-8");
	        response.getWriter().write("Success");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@RequestMapping("/startleague")
	public String createLeague() {
		return "startleague";
	}
	
	@RequestMapping("/home")
	protected ModelAndView home(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView();
		
		if(session!=null){
			int cid = 0;
			int uid = (Integer) session.getAttribute("uid");
			
			if(session.getAttribute("cid")!=null){
				reloadTeacherPage = true;
				cid = (Integer) session.getAttribute("cid");
			}else{
				reloadAdminPage = true;
			}
			
			
		
			
			// Load teacher data and teacher jsp pages
			Profile profile;
			try {
				if(reloadTeacherPage){
					TeacherUser teacher = new TeacherUser(uid, cid);
					
					profile = new TeacherProfile((TeacherUser) teacher);
					ArrayList<ArrayList> values = ((TeacherProfile) profile).initProfile();

					// Create key-value pairs for students, items, and
					// categories to
					// be retrieved in client
					for (int i = 0; i < values.size(); i++) {
						System.out.println(teacher_keys[i] + " " + values.get(i).toString());
						request.setAttribute(teacher_keys[i], values.get(i));
					}
					
					//Retrieve the class jar for client
					Jar jar = ((TeacherProfile) profile).getClassJar();
					if (jar != null) {
						model.addObject("jarName", jar.getJarName());
						model.addObject("jarProgress", jar.getJarProgress());
						model.addObject("jarTotal", jar.getJarTotal());
					} else {
						model.addObject("jarProgress", "No Class Jar!");
					}
				
					//Direct to teacher home page
					model.setViewName("teacherhome");		
				}else if(reloadAdminPage){
					League admin = new League(uid);
					
					profile = new LeagueProfile((League) admin);
					ArrayList<ArrayList> values = ((LeagueProfile) profile).initProfile();

					// Create key-value pairs for students, items, and
					// categories to
					// be retrieved in client
					for (int i = 0; i < values.size(); i++) {
						System.out.println(admin_keys[i] + " " + values.get(i).toString());
						request.setAttribute(admin_keys[i], values.get(i));
					}
					
					//Retrieve the class jar for client
					Jar jar = ((LeagueProfile) profile).getSchoolJar();
					if (jar != null) {
						model.addObject("jarName", jar.getJarName());
						model.addObject("jarProgress", jar.getJarProgress());
						model.addObject("jarTotal", jar.getJarTotal());
					} else {
						model.addObject("jarProgress", "No Class Jar!");
					}
				
					//Direct to teacher home page
					model.setViewName("adminhome");		
				}
				
				
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBConnection.connection.disconnect();

			}
			
		}else{
			DBConnection.connection.disconnect();

			model.setViewName("login");
		}
		return model;
		
	}
	
	@RequestMapping("/login.html")
	protected String loginPage(){
		return "login";
	}
	
	@RequestMapping("/logout.html")
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("isLoggedIn", false);
		session.invalidate();
		response.sendRedirect("login.html");
		return;
		
	}

	@RequestMapping("/store.html")
	public String teacherStore() {
		return "store";
	}

	@RequestMapping("/leaderboards.html")
	public String leaderboards() {
		return "leaderboards";
	}

	/*@RequestMapping("/studenthome.html")
	public @ResponseBody
	ModelAndView loadStudentPage(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException,
			SQLException {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession(false);

		if (session == null) {
			System.out.println("Session not created");
			model.setViewName("index");
		} else {
			model.addObject("message", session.getAttribute("fname"));
			model.addObject("fname", session.getAttribute("fname"));
			model.setViewName("studentname");
		}
		return model;
	}*/

	@RequestMapping("/privacypolicy.html")
	public String studentInventory() {
		return "privacypolicy";
	}


	@RequestMapping("/settings.html")
	public String settings(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//Load the settings for this user
		try {
			WebHelperImpl helper = new WebHelperImpl();
			
			String settings[] = helper.getPrivacy(session);
		
			
			request.setAttribute("privacy", settings[0]);
			request.setAttribute("code", settings[1]);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.connection.disconnect();

		}
		
		return "settings";
	}
	
	@RequestMapping("/league.html")
	public String leagueSettings(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//Load the settings for this user
		
		League league = null;
		
		Jar schoolJar = null;
				
		ArrayList<HashMap<String, Object>> leagues = null;
		
	
		try {
			
			
			WebHelperImpl helper = new WebHelperImpl();
			
			int leagueId = helper.getLeagueStatus(session);
			
			if(leagueId != 0){
				//If the teacher is in a league, find league and admin data
				league = helper.getLeagueData(leagueId);
				
				
				
				if(league!=null){
					LeagueProfile profile = new LeagueProfile(league);
					schoolJar = profile.getSchoolJar();
					
					String fullName = league.getFname().trim() + " " + league.getLname().trim();
					
					request.setAttribute("status", "in");
					
					request.setAttribute("leagueName", league.getLeagueName());
					
					request.setAttribute("leader", fullName);
					
					if(schoolJar != null){
						request.setAttribute("progress", schoolJar.getJarProgress() + "/");
						
						request.setAttribute("total", schoolJar.getJarTotal());
					}else{
						request.setAttribute("progress","No School Jar!");
					}
					
				}
				
			}else{
				
				//If not in a league, set league status flag to out
				request.setAttribute("status", "out");
				
				//Get all leagues
				leagues = helper.getLeagues();
				
				//Display all leagues for user to select from
				request.setAttribute("leagues", leagues);
			}
			
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.connection.disconnect();
		}
		
		return "league";
	}
	
	@RequestMapping("/signup.html")
	protected String signup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		//When this page is loading, populate drop down menu with league names
		try {
			
			WebHelperImpl helper = new WebHelperImpl();
			
			ArrayList<HashMap<String, Object>> leagues = helper.getLeagues();
			
			ArrayList<HashMap<String,Object>> schools = helper.getSchools();
			System.out.println("Schools: " + schools.toString());
			request.setAttribute("leagues", leagues);
			request.setAttribute("schools", schools);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.connection.disconnect();

		}
		
		return "signup";
	}
	
	@RequestMapping("/success.html")
	protected String successPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		return "success";
	}
	
	
	@RequestMapping("/changePassword.html")
	protected String changePassword(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		return "changePassword";
	}
	@RequestMapping("/expired.html")
	protected String expiredRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		return "expired";
	}
	
	@RequestMapping("/resetRequest.html")
	protected String resetRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		return "resetRequest";
	}
}
