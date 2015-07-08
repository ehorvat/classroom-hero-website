package com.ego.entities;

import java.io.Serializable;

public class Jar implements Serializable{
	
	String jarName;
	int jarTotal;
	int jarProgress;
	
	//ctor
	public Jar(){
		
	}
	
	public Jar(String jarName, int jarCost, int jarProgress){
		this.jarName = jarName;
		this.jarTotal = jarCost;
		this.jarProgress = jarProgress;
	}
	
	//Getters

	public String getJarName() {
		return jarName;
	}

	public int getJarTotal() {
		return jarTotal;
	}

	public int getJarProgress() {
		return jarProgress;
	}

	//Setters
	
	public void setJarName(String jarName) {
		this.jarName = jarName;
	}

	public void setJarTotal(int jarCost) {
		this.jarTotal = jarCost;
	}

	public void setJarProgress(int jarProgress) {
		this.jarProgress = jarProgress;
	}
	
	

}
