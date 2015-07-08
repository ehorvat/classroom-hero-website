package com.ego.hero;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

public class Entity {
	
	String name;
	
	String fname;
	
	String lname;
	
	public Entity(){
		
	}
	
	public Entity(String name){
		this.name = name;
	}
	
	public Entity(String fname, String lname){
		this.name = fname.trim() + " " + lname.trim();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
