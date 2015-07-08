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
import com.ego.entities.School;
import com.ego.interfaces.WebHelperInterface;
import com.ego.users.League;
import com.ego.users.StudentUser;
import com.ego.util.Queries;

public class WebHelperImpl implements WebHelperInterface{

	
	PreparedStatement preparedStatement = null;
	
	Connection connection;
	
	public WebHelperImpl() throws NamingException, SQLException, ServletException{
		connection = DBConnection.dbc();
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> getLeagues() throws SQLException {
		
		
		
		ArrayList<HashMap<String, Object>> leagues = new ArrayList<HashMap<String, Object>>();
		
		preparedStatement = connection.prepareStatement(Queries.GET_LEAGUES);
		ResultSet rs = preparedStatement.executeQuery();
		
		int i = 0;
		while(rs.next()){
			//Read league name and privacy into hashmap
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", rs.getString(1).trim());
			
			//Check if private or public
			if(rs.getBoolean(2)){
				map.put("visibility", "private");
			}else{
				map.put("visibility", "public");
			}
			
			

			leagues.add(map);
			
			
		}
		
		return leagues; 
	}

	public String[] getPrivacy(HttpSession session) throws SQLException {
		
		//Get user id from session
		int uid = (Integer) session.getAttribute("uid");
		String setting[] = new String[2];
		
		preparedStatement = connection.prepareStatement(Queries.GET_PRIVACY_SETTING);
		preparedStatement.setInt(1, uid);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			boolean privacy = rs.getBoolean(1);
			String code = rs.getString(2);
			
			if(privacy){
				setting[0] = "Private";
			}else{
				setting[0] = "Public";
			}
			
			setting[1] = code;
		}
		
		return setting;
	}

	@Override
	public int getLeagueStatus(HttpSession session) throws SQLException {
		
		int leagueId = 0;
		
		//Get uid from session
		int uid = (Integer) session.getAttribute("uid");
		
		preparedStatement = connection.prepareStatement(Queries.GET_LEAGUE_STATUS);
		preparedStatement.setInt(1, uid);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			
			//Row found. User is in a league. Get league id
			leagueId = rs.getInt(1);	
		}
		
		
		return leagueId;
	}

	public League getLeagueData(int leagueId) throws SQLException {
		
	
		League league = null;
		
		preparedStatement = connection.prepareStatement(Queries.GET_LEAGUE_DATA);
		preparedStatement.setInt(1, leagueId);
		
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			league = new League(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		}
		
		
		return league;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getSchools() throws SQLException {
		
		ArrayList<HashMap<String, Object>> schools = new ArrayList<HashMap<String, Object>>();
		
		
		preparedStatement = connection.prepareStatement(Queries.GET_ALL_SCHOOLS);
		ResultSet rs = preparedStatement.executeQuery();
	
		while(rs.next()){
			HashMap<String, Object> map = new HashMap<String, Object>();
			System.out.println(rs.getString(1));
			map.put("name", rs.getString(1).trim());
			schools.add(map);
		}

		
		
		return schools;
	}

	
}
