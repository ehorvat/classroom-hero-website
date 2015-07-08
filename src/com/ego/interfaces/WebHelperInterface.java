package com.ego.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.ego.hero.DBConnection;
import com.ego.users.League;

public interface WebHelperInterface {

	
	ArrayList<HashMap<String, Object>> getLeagues() throws SQLException;
	
	ArrayList<HashMap<String, Object>> getSchools() throws SQLException;
	
	String[] getPrivacy(HttpSession session) throws SQLException;
	
	int getLeagueStatus(HttpSession session) throws SQLException;
	
	League getLeagueData(int leagueId) throws SQLException;
	
	
	
}
