package com.ego.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

public interface CategoryInterface {
	void createCategory(HttpSession session) throws SQLException;
	void deleteCategory(HttpSession session) throws SQLException;
	void changeCategory(HttpSession session) throws SQLException;
}
