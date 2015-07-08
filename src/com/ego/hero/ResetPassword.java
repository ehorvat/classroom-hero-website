package com.ego.hero;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ego.exceptions.InvalidEmail;
import com.ego.exceptions.PasswordMisMatch;
import com.ego.exceptions.TokenExpired;
import com.ego.users.TeacherUser;

@Controller
public class ResetPassword {

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public @ResponseBody ModelAndView sendConfirmationLink(HttpServletRequest request, HttpServletResponse response) {

		String email = request.getParameter("email");
		
		ServletContext context = request.getServletContext();

		ModelAndView model = new ModelAndView();
		
		
		try {
			Connection conn = DBConnection.dbc();
			
			Profile p = new Profile(email);
			
			p.verifyEmail(conn, context);
			
			model.setViewName("/success");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvalidEmail e) {
			model.setViewName("/resetRequest");
			model.addObject("resetFailed", "Email does not exist in the system");
			e.printStackTrace();
		}finally{
			DBConnection.connection.disconnect();
		}
		return model;

	}

		@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
		public @ResponseBody ModelAndView checkToken(HttpServletRequest request, HttpServletResponse response) {

			HttpSession session = request.getSession();
			
			String token = request.getParameter("token");
			
			System.out.println("Token  :  " + token);

			ModelAndView model = new ModelAndView();
			try {
				Connection conn = DBConnection.dbc();
				
				Profile p = new Profile();
				
				p.verifyToken(conn, token);
				
				
				//Init session variables
				session.setAttribute("email", p.email);
				
				model.setViewName("/changePassword");
			
	
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (TokenExpired e) {
				
					model.setViewName("/expired");
			
				e.printStackTrace();
			}finally{
				DBConnection.connection.disconnect();
			}
			
			return model;
	
		}
		
		@RequestMapping(value = "/change", method = RequestMethod.POST) public @ResponseBody ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response) {

			HttpSession session = request.getSession();
			
			String p1 = request.getParameter("p1");
			String p2 = request.getParameter("p2");
			
			String email = (String) session.getAttribute("email");
			
			System.out.println("Email : " + email);
			
			ModelAndView model = new ModelAndView();

			
			try {
				Connection conn = DBConnection.dbc();
				
				Profile p = new Profile();
				
				p.changePassword(conn, p1, p2, email);
				
				model.setViewName("login");
				model.addObject("changeSuccess", "Password has been successfully reset!");
	
	
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (PasswordMisMatch e) {

				model.setViewName("changePassword");
				model.addObject("mismatch", "Passwords don't match.");
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBConnection.connection.disconnect();
			}
			return model;
	
		}

	
}
