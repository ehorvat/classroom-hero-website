package com.ego.entities;

import java.io.Serializable;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String item_name = new String();
	
	int item_cost;
	
	public Item(){
		
	}
	
	public Item(int item_cost, String item_name) {
		this.item_name = item_name;
		
		this.item_cost = item_cost;
	}


	public String getItemName() {
		return item_name;
	}


	public int getItemCost() {
		return item_cost;
	}

	public void setItemName(String item_name) {
		this.item_name = item_name;
	}


	public void setItemCost(int item_cost) {
		this.item_cost = item_cost;
	}

}
