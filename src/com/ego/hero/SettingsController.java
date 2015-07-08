package com.ego.hero;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ego.exceptions.PasswordMisMatch;
import com.ego.users.TeacherUser;
import com.ego.users.User;

@Controller
public class SettingsController {

	private static final String PRIVATE = "private";
	
	private static final String PUBLIC = "public";
	
	
	//////////////////////////////
	// 
	// Change privacy settings
	// 
	//////////////////////////////
	@RequestMapping(value = "/savesettings", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView changePrivacy(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		PrintWriter out = null;
		ModelAndView model = new ModelAndView();
		// Get item data from modal form
		String privacy = request.getParameter("radios").trim();
		
		System.out.println(privacy);
		try {
			LeagueProfile profile = new LeagueProfile();
			
			profile.changePrivacy(privacy, session);
			
			WebHelperImpl helper = new WebHelperImpl();
			
			String settings[] = helper.getPrivacy(session);			
			
			//Determine which privacy setting to display
			if(privacy.equals(PRIVATE)){
				request.setAttribute("privacy", "Private");
				request.setAttribute("code", settings[1]);
			}else if(privacy.equals(PUBLIC)){
				request.setAttribute("privacy", "Public");
				request.setAttribute("code", settings[1]);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}
		
		model.setViewName("settings");
		return model;
	}
	
	
	//////////////////////////////
	// 
	// Join League
	// 
	//////////////////////////////
	@RequestMapping(value = "/joinLeague", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView createItem(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		PrintWriter out = null;
		
		String leaguePass = null;
		
		ModelAndView model = new ModelAndView();
		
		boolean regWithPass = false;
		
		RegistrationServiceImpl registerToLeague = null;
		
		//Get some teacher data
		int uid = (Integer) session.getAttribute("uid");
		String leagueName = request.getParameter("leagueName");
		
		if(request.getParameter("leaguePass") != null || request.getParameter("leaguePass") != ""){
			leaguePass = request.getParameter("leaguePass");
			regWithPass = true;
		}
		
		try {
			TeacherUser user = new TeacherUser(uid);
			
			if(regWithPass){
				//Attempt to register user to password-protected league
				
				registerToLeague = new RegistrationServiceImpl(user, leagueName, leaguePass);
				registerToLeague.addToPrivateLeague();
			}else{
				//Attempt to register user to league with no password
				
				registerToLeague = new RegistrationServiceImpl(user, leagueName, leaguePass);
				registerToLeague.addToPublicLeague();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PasswordMisMatch e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}
		
		model.setViewName("settings");
		return model;
	}
	
	
	
	
}
