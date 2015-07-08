package com.ego.users;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class StudentUser extends User {
	
	int totalCoins;
	int currentCoins;
	int totalStars;
	int lvl;
	int progress;
	int lvlUpAmount;
	
	
	//////////////////
	//				//
	// ctor			//
	//				//
	//////////////////
	public StudentUser(){
		
	}
	
	
	public StudentUser(String fname, String lname, String pass, String confPass, int userType) throws NoSuchAlgorithmException {
		super(fname, lname, pass, confPass, userType);
		
		
	}
	
	public StudentUser(String fname, String lname, int totalCoins){
		super(fname, lname);
		this.totalCoins = totalCoins;
	}

	
	//////////////////////////
	//						//
	// Getters				//
	//						//
	//////////////////////////
	public int getTotalCoins() {
		return totalCoins;
	}
	public int getCurrentCoins() {
		return currentCoins;
	}
	public int getTotalStars() {
		return totalStars;
	}
	public int getLvl() {
		return lvl;
	}
	public int getProgress() {
		return progress;
	}
	public int getLvlUpAmount() {
		return lvlUpAmount;
	}
	
	//////////////////////////
	//						//
	// Setters				//
	//						//
	//////////////////////////
	public void setTotalCoins(int totalCoins) {
		this.totalCoins = totalCoins;
	}
	public void setCurrentCoins(int currentCoins) {
		this.currentCoins = currentCoins;
	}
	public void setTotalStars(int totalStars) {
		this.totalStars = totalStars;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public void setLvlUpAmount(int lvlUpAmount) {
		this.lvlUpAmount = lvlUpAmount;
	}	
}
