package com.ego.interfaces;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

public interface LeagueSettingsInterface {
	void changePrivacy(String privacy, HttpSession session) throws SQLException;
}
