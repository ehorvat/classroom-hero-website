package com.ego.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Card {

	private int cardId;
	
	private DBConnect connection;
	private Connection conn;
	PreparedStatement preparedStatement = null;
	
	//////////////////////////
	//
	//ctor
	//
	//////////////////////////
	public Card(int cardId){
		this.cardId = cardId;
		connection = new DBConnect("postgres", null);
		conn = connection.connect();
	}
	
	//////////////////////////
	//
	//Insert card Information
	//
	//////////////////////////
	public void insertCardInfo(){
		String selectTermId = "INSERT INTO \"Card\"(\"activationId\", \"insertTime\") VALUES(?,?)";
		try {
			//Insert passed in cardId
			preparedStatement = conn.prepareStatement(selectTermId);
			preparedStatement.setInt(1, cardId);
			
			//Insert timestamp
			java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
			preparedStatement.setTimestamp(2, sqlDate);
			
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (conn != null) {
				connection.disconnect();
			}
		}
	}
	
	
	//////////////////////////
	//
	//Getters
	//
	//////////////////////////
	public int getCardId(){
		return cardId;
	}
}
