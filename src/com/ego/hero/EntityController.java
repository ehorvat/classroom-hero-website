package com.ego.hero;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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

import com.ego.entities.Jar;
import com.ego.util.DBConnect;

/////////////////////////////////////
// 
// CRUD operations for all entities
// 
/////////////////////////////////////

@Controller
public class EntityController {

	DBConnect conn;
	private Entity entity;

	// ////////////////////////////
	// //
	// Add Item Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "item/create", method = RequestMethod.POST)
	public @ResponseBody
	void createItem(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		PrintWriter out = null;

		// Get item data from modal form
		String name = request.getParameter("item_title");
		int cost = Integer.parseInt(request.getParameter("item_cost"));
		
		
		try {
			entity = new ItemEntity(cost, name);

			((ItemEntity) entity).createItem(session);

			JSONObject json = new JSONObject();

			out = response.getWriter();
			json.put("name", entity.getName());
			json.put("cost", ((ItemEntity) entity).cost);

			out.print(json);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}

	}

	// ////////////////////////////
	// //
	// Edit Item Handler //
	// //
	// ////////////////////////////

	@RequestMapping(value = "item/change", method = RequestMethod.POST)
	public @ResponseBody
	void changeItem(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		// Get item data from modal form
		String name = request.getParameter("item_title");
		int cost = Integer.parseInt(request.getParameter("item_cost"));
		String original = request.getParameter("original");
		try {
			entity = new ItemEntity(cost, name, original);

			((ItemEntity) entity).changeItem(session);

			PrintWriter out = null;
			JSONObject json = new JSONObject();

			out = response.getWriter();
			json.put("name", entity.name);
			json.put("cost", ((ItemEntity) entity).cost);

			out.print(json);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	}

	// ////////////////////////////
	// //
	// Delete Item Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "item/delete", method = RequestMethod.POST)
	public @ResponseBody
	void deleteItem(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		// Get item data from modal form
		String name = request.getParameter("name");
		int cost = Integer.parseInt(request.getParameter("cost").trim());
		try {
			entity = new ItemEntity(cost, name);

			((ItemEntity) entity).deleteItem(session);

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

		
	}

	// ////////////////////////////
	// //
	// Add Category Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "/createCategory", method = RequestMethod.POST)
	public @ResponseBody
	void createCategory(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		// Get item data from modal form
		String name = request.getParameter("newCategoryName");

		try {
			entity = new CategoryEntity(name);
			((CategoryEntity) entity).createCategory(session);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}
		
	}

	// ////////////////////////////
	// //
	// Add Category Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "/editCategory", method = RequestMethod.POST)
	public @ResponseBody
	void editCategory(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		// Get item data from modal form
		String name = request.getParameter("newCategoryName");
		String original = request.getParameter("original");

		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {

			entity = new CategoryEntity(name, original);
			((CategoryEntity) entity).changeCategory(session);

			out = response.getWriter();
			json.put("name", ((CategoryEntity) entity).name);
			out.print(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}

	}

	// ////////////////////////////
	// //
	// Delete Category Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
	public @ResponseBody
	void deleteCategory(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		// Get item data from modal form
		String name = request.getParameter("name");

		try {
			entity = new CategoryEntity(name);
			((CategoryEntity) entity).deleteCategory(session);
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

	}

	// ////////////////////////////
	// //
	// Add Student Handler //
	// //
	// ////////////////////////////

	@RequestMapping(value = "register/addStudent", method = RequestMethod.POST)
	public void registerStudent(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException,
			SQLException, IOException, JSONException {

		HttpSession session = request.getSession();

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");

		try {
			entity = new StudentEntity(fname, lname);
			((StudentEntity) entity).createStudent(session);

			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("fname", ((StudentEntity) entity).fname);
			json.put("lname", ((StudentEntity) entity).lname);

			out.print(json);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}

	}

	// ////////////////////////////
	// //
	// Edit Student Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "/changeStudent", method = RequestMethod.POST)
	public @ResponseBody
	void changeStudent(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		// Get item data from modal form
		String name = request.getParameter("edit_name");
		String original = request.getParameter("original");
		
		System.out.println("new " + name + " orignal " + original);

		String[] splited = name.split("\\s+");

		try {
			entity = new StudentEntity(splited[0], splited[1], original);
			((StudentEntity) entity).changeStudent(session);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}

	}

	// ////////////////////////////
	// //
	// Delete Student Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
	public @ResponseBody
	void deleteStudent(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		// Get item data from modal form
		String name = request.getParameter("name");
		String[] splited = name.split("\\s+");
		
		System.out.println(name);
		
		try {
			entity = new StudentEntity(splited[0], splited[1]);
			((StudentEntity) entity).deleteStudent(session);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.connection.disconnect();
		}
	}

	// ////////////////////////////
	// //
	// Add Jar Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "jar/add", method = RequestMethod.POST)
	public @ResponseBody
	void createJar(HttpServletRequest request, HttpServletResponse response) throws NamingException, ServletException {

		HttpSession session = request.getSession();
		// Get jar data from modal form
		
		String jarName = request.getParameter("newJarName");
		int jarTotal = Integer.parseInt(request.getParameter("newJarTotal"));
		
		
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {
			entity = new JarEntity(jarName, jarTotal);
			((JarEntity) entity).createJar(session);

			out = response.getWriter();
			json.put("name", jarName);
			json.put("total", jarTotal);

			out.print(json);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}finally{
			DBConnection.connection.disconnect();

		}

	}

	// ////////////////////////////
	// //
	// Edit Jar Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "jar/edit", method = RequestMethod.POST)
	public @ResponseBody
	void editJar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, NamingException, ServletException {


		HttpSession session = request.getSession();

		// Get jar data from modal form
		String jarName = request.getParameter("editJarName");
		String jarTotal = request.getParameter("editJarTotal");

		PrintWriter out = null;
		JSONObject json = new JSONObject();

		try {
			entity = new JarEntity(jarName, Integer.parseInt(jarTotal));
			((JarEntity) entity).changeJar(session);

			out = response.getWriter();
			json.put("name", jarName);
			json.put("total", jarTotal);
			out.print(json);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			DBConnection.connection.disconnect();

		}

	}

	// ////////////////////////////
	// //
	// Delete Jar Handler //
	// //
	// ////////////////////////////
	@RequestMapping(value = "jar/delete", method = RequestMethod.POST)
	public @ResponseBody
	void deleteJar(HttpServletRequest request, HttpServletResponse response) {
	


		HttpSession session = request.getSession();

		// Get jar data from modal form
		String jarName = request.getParameter("editJarName");


		try {
			entity = new JarEntity(jarName);
			((JarEntity) entity).deleteJar(session);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.connection.disconnect();

		}
	
	}

}
