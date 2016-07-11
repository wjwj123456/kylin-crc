package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataservice.LoginDataService;

public class LoginDataImpl implements LoginDataService {

	private ResultSet rSet = null;
	private PreparedStatement pStatement = null;

	public int createAccount(String userName, String email, String password)
			throws ClassNotFoundException, SQLException {
		int flag = -1;

		int isExist = verifyAccount(userName, password);
		if (isExist == 0 || isExist == 2) {
			flag = 1;
			return flag;
		}

		String sql = "INSERT INTO user (uname, email, password) VALUES (?, ?, ?)";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, userName);
		pStatement.setString(2, email);
		pStatement.setString(3, password);
		int i = pStatement.executeUpdate();
		if (i == 1) {
			flag = 0;
		} else {
			flag = 2;
		}
		DBManager.closeConnection();
		return flag;
	}

	public int verifyAccount(String userName, String password) throws ClassNotFoundException, SQLException {
		int flag = -1;
		String sql = "SELECT * FROM user WHERE uname = '" + userName + "'";

		rSet = DBManager.getResultSet(sql);
		if (rSet.next()) {
			String savedPassword = rSet.getString("password");
			if (savedPassword.equals(password))
				flag = 0;
			else
				flag = 2;
		} else {
			flag = 1;
		}
		DBManager.closeConnection();
		return flag;
	}

	public int changePassword(String userName, String password) throws ClassNotFoundException, SQLException {
		int flag = -1;

		int isExist = verifyAccount(userName, password);
		if (isExist == 0) {
			flag = 2;
			return flag;
		} else {
			if (isExist == 1) {
				flag = 1;
				return flag;
			}
		}
		String sql = "UPDATE user SET password = ? WHERE uname = ?";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, password);
		pStatement.setString(2, userName);
		int i = pStatement.executeUpdate();
		if (i == 1)
			flag = 0;
		else
			flag = 3;
		DBManager.closeConnection();
		return flag;
	}

}
