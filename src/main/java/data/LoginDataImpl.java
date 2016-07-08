package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dataservice.LoginDataService;

public class LoginDataImpl implements LoginDataService{

	public boolean createAccount(String userName, String email, String password) throws ClassNotFoundException, SQLException {
		boolean flag = true;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO user (uname, email, password) VALUES (?, ?, ?)";
		pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, userName);
		pStatement.setString(2, email);
		pStatement.setString(3, password);
		int i = pStatement.executeUpdate();
		if(i==0) {
			flag = false;
		}
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	public int verifyAccount(String userName, String password) throws ClassNotFoundException, SQLException {
		int flag = -1;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "SELECT * FROM user WHERE uname = '" + userName + "'";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		if(rSet.next()) {
			String savedPassword = rSet.getString("password");
			if(savedPassword.equals(password))	flag = 0;
			else	flag = 2;
		}
		else {
			flag = 1;
		}
		DBManager.stopAll(rSet, pStatement, connection);
		return flag;
	}

	public boolean changePassword(String userName, String password) throws ClassNotFoundException, SQLException {
		boolean flag = true;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "UPDATE user SET password = ? WHERE uname = ?";
		pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, password);
		pStatement.setString(2, userName);
		int i = pStatement.executeUpdate();
		if(i==0)	flag = false;
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	
}
