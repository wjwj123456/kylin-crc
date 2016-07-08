package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dataservice.LoginDataService;

public class LoginDataImpl implements LoginDataService{

	public int createAccount(String userName, String email, String password) throws ClassNotFoundException, SQLException {
		int flag = -1;
		Connection connection = DBManager.connect();
		int isExist = verifyAccount(userName, password);
		if(isExist == 0 || isExist == 2) {
			flag = 1;
			return flag;
		}
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO user (uname, email, password) VALUES (?, ?, ?)";
		pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, userName);
		pStatement.setString(2, email);
		pStatement.setString(3, password);
		int i = pStatement.executeUpdate();
		if(i==1) {
			flag = 0;
		}
		else {
			flag = 2;
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

	public int changePassword(String userName, String password) throws ClassNotFoundException, SQLException {
		int flag = -1;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		int isExist = verifyAccount(userName, password);
		if(isExist == 0) {
			flag = 2;
			return flag;
		}
		else {
			if(isExist == 1) {
				flag = 1;
				return flag;
			}
		}
		String sql = "UPDATE user SET password = ? WHERE uname = ?";
		pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, password);
		pStatement.setString(2, userName);
		int i = pStatement.executeUpdate();
		if(i==1)	flag = 0;
		else	flag = 3;
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	
}
