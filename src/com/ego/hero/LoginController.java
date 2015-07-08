package com.ego.hero;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ego.entities.Jar;
import com.ego.exceptions.PasswordMisMatch;
import com.ego.users.League;
import com.ego.users.StudentUser;
import com.ego.users.TeacherUser;
import com.ego.users.User;

@Controller
public class LoginController {

	private User user;

	private static final String STUDENT_ENTITY = "list";

	private static final String ITEM_ENTITY = "itemList";

	private static final String CATEGORY_ENTITY = "categoriesList";

	private static final String[] teacher_keys = { STUDENT_ENTITY, ITEM_ENTITY, CATEGORY_ENTITY };
	
	private static final String[] admin_keys = { ITEM_ENTITY, CATEGORY_ENTITY };
	
	private HttpSession session;
	
	private Profile profile;
	
	private ArrayList<TeacherUser> fourthTeacher = new ArrayList<TeacherUser>();
	
	private ArrayList<TeacherUser> fifthTeacher = new ArrayList<TeacherUser>();
	
	private ArrayList<TeacherUser> sixthTeacher = new ArrayList<TeacherUser>();
	
	private ArrayList<StudentUser> fourthStudent = new ArrayList<StudentUser>();
	
	private ArrayList<StudentUser> fifthStudent = new ArrayList<StudentUser>();
	
	private ArrayList<StudentUser> sixthStudent = new ArrayList<StudentUser>();

	@RequestMapping(value = "/home", method = RequestMethod.POST) public @ResponseBody ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) {
		
		// Get request variables
		String username = null;
		String password = null;
		
		username = request.getParameter("email").trim();
		password = request.getParameter("password").trim();
		
		System.out.println(username + " " + password);
		/*if(request.getParameter("email") == "" || request.getParameter("password") == ""){
			//If logging on from sign in get credentials from parameters
			System.out.println("parameter");
			username = request.getParameter("email");
			password = request.getParameter("password");

		}else{
			//If coming from registration, get credentials from session attributes
			username = (String) request.getAttribute("email");
			password = (String) request.getAttribute("pw");
		}*/

		ModelAndView model = new ModelAndView();
		session = request.getSession();

		// Check if logged in
		if (session.getAttribute("isLoggedIn") == null || session.getAttribute("email") != username) {

			LoginServiceImpl auth;
			try {
				auth = new LoginServiceImpl(username, password);
				
				user = auth.getUser();
				
				if (user instanceof StudentUser) {
					//This functionality will come in a few months
				} else if (user instanceof TeacherUser) {
					
					// Load teacher data and teacher jsp pages
					profile = new TeacherProfile((TeacherUser) user);
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
					
					System.out.println("Class ID " + ((TeacherUser) user).getCid());
					
					//Init session variables
					session.setAttribute("uid", user.getUid());
					session.setAttribute("fname", user.getFname());
					session.setAttribute("lname", user.getLname());
					session.setAttribute("email", ((TeacherUser) user).getEmail());
					session.setAttribute("cid", ((TeacherUser) user).getCid());
					session.setAttribute("role", 1);
					session.setAttribute("schoolId", user.getSchoolId());
					
					//Direct to teacher home page
					model.setViewName("teacherhome");			
					
				} else if (user instanceof League) {
					
					// Load admin (league) data and admin jsp pages
					profile = new LeagueProfile((League) user);
					
					ArrayList<ArrayList> values = ((LeagueProfile) profile).initProfile();
					
					//ArrayList<ArrayList<ArrayList<StudentUser>>> teacherStudentPairs = ((LeagueProfile) profile).getTeacherStudentPairs();
			
					
					//Create key-value pairs for items and categories to be displayed on client
					for (int i = 0; i < values.size(); i++) {
						request.setAttribute(admin_keys[i], values.get(i));
					}
					
					//Retrieve the school jar for client
					Jar jar = ((LeagueProfile) profile).getSchoolJar();
					if (jar != null) {
						model.addObject("jarName", jar.getJarName());
						model.addObject("jarProgress", jar.getJarProgress());
						model.addObject("jarTotal", jar.getJarTotal());
					} else {
						model.addObject("jarProgress", "No Class Jar!");
					}
					
					
					//Init session variables
					session.setAttribute("uid", user.getUid());
					session.setAttribute("fname", user.getFname());
					session.setAttribute("lname", user.getLname());
					session.setAttribute("email", ((League) user).getEmail());
					session.setAttribute("role", 3);
					
					
					model.setViewName("adminhome");
					
					//Create key value-pairs for teacherStudentPairs to be displayed to client
					/*for(int i = 0; i<teacherStudentPairs.size(); i++){
						
						//Get the middle arraylist (the teachers)
						ArrayList teachers = teacherStudentPairs.get(i);						
						
						//Instantiate innermost array (the students) for this teacher
						ArrayList<StudentUser> students = teacherStudentPairs.get(i).get(i);
						
						//Loop through all the teachers
						for(int t = 0; t<teachers.size(); t++){
							
							//Instantiate a new teacher
							TeacherUser teacher = (TeacherUser) teachers.get(t);
							
							StudentUser student = students.get(t);
							
							//Determine which grade level the teacher teaches and construct an arraylist
							buildArrays(teacher, student);
							
							//set request attributes for teachers
							request.setAttribute("fourthTeacher", fourthTeacher);
							request.setAttribute("fifthTeacher", fifthTeacher);
							request.setAttribute("sixthTeacher", sixthTeacher);
							
							//set request attributes for students
							request.setAttribute("fourthStudent", fourthStudent);
							request.setAttribute("fifthStudent", fifthStudent);
							request.setAttribute("sixthStudent", sixthStudent);
						
						}		*/			
				
					
					
					
					
					
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (PasswordMisMatch e) {
				model.setViewName("/login");
				model.addObject("loginFailed", "Invalid email or password");
				e.printStackTrace();
			}finally{
				DBConnection.connection.disconnect();
			}

		}
		return model;
	}

	private void buildArrays(TeacherUser teacher, StudentUser student) {	
		
		//Perform switch case to figure out which grade to populate with teachers and students
		switch(teacher.getGrade()){
		case 4:
			//Put under 4th grade section
			fourthTeacher.add(teacher);
			fourthStudent.add(student);
			break;
		case 5:
			//Put under 5th grade section
			fifthTeacher.add(teacher);
			fifthStudent.add(student);
			break;
		case 6:
			//Put under 6th grade section
			sixthTeacher.add(teacher);
			sixthStudent.add(student);
			break;
		}
		
	}

}
