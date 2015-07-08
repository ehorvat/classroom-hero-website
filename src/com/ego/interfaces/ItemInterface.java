package com.ego.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

public interface ItemInterface {
	void createItem(HttpSession session) throws SQLException;
	void deleteItem(HttpSession session) throws SQLException;
	void changeItem(HttpSession session) throws SQLException;
}
