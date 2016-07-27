package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataservice.UserInfoDataService;
import po.UserInfoPO;
import vo.Language;
import vo.Sex;

public class UserInfoDataImpl implements UserInfoDataService {

	@Override
	public boolean update(UserInfoPO po) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "UPDATE inform SET name = ?, sex = ?, job = ?,"
				+ " province = ?, city = ?, description = ?, picture = ? WHERE uname = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, po.getTrueName());
		pStatement.setString(2, po.getSex().toString());
		pStatement.setString(3, po.getJob());
		pStatement.setString(4, po.getProvince());
		pStatement.setString(5, po.getCity());
		pStatement.setString(6, po.getDescription());
		pStatement.setString(7, po.getPicture());
		pStatement.setString(8, po.getUserName());
		int i = pStatement.executeUpdate();

		String sql2 = "DELETE FROM user_language WHERE uname = ?";
		pStatement = connection.prepareStatement(sql2);
		pStatement.setString(1, po.getUserName());
		pStatement.executeUpdate();

		String username = po.getUserName();
		for (Language language : po.getLanguages()) {
			String sql3 = "INSERT INTO user_language(uname, language) VALUES(?, ?)";
			pStatement = connection.prepareStatement(sql3);
			pStatement.setString(1, username);
			pStatement.setString(2, language.toString());
			pStatement.executeUpdate();
		}
		DBManager.stopAll(null, pStatement, connection);
		if (i == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean add(String username) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "INSERT INTO inform (uname) VALUES (?)";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, username);
		int i = pStatement.executeUpdate();
		if (i == 1)
			return true;
		else
			return false;
	}

	@Override
	public UserInfoPO get(String username) throws ClassNotFoundException, SQLException {
		UserInfoPO result = null;
		Connection connection = DBManager.connect();
		String sql = "SELECT * FROM inform WHERE uname = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, username);
		ResultSet rSet = pStatement.executeQuery();
		if (rSet.next()) {
			result = new UserInfoPO(username, rSet.getString("name"), Sex.valueOf(rSet.getString("sex")),
					rSet.getString("job"), rSet.getString("province"), rSet.getString("city"),
					rSet.getString("description"), rSet.getString("picture"), null);
		} else
			return null;
		String sql2 = "SELECT * FROM user_language WHERE uname = ?";
		pStatement = connection.prepareStatement(sql2);
		pStatement.setString(1, username);
		rSet = pStatement.executeQuery();
		ArrayList<Language> languages = new ArrayList<>();
		while (rSet.next()) {
			languages.add(Language.valueOf(rSet.getString("language")));
		}

		Language[] user_language = new Language[languages.size()];
		languages.toArray(user_language);

		result.setLanguages(user_language);
		DBManager.stopAll(rSet, pStatement, connection);
		return result;
	}

	@Override
	public int setPicture(String userName, String path) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "UPDATE inform SET picture = ? WHERE uname = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, path);
		pStatement.setString(2, userName);
		int i = -1;
		i = pStatement.executeUpdate();
		DBManager.stopAll(null, pStatement, connection);
		return i;
	}

	@Override
	public String getPicture(String userName) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT picture FROM inform WHERE uname = '"+userName+"'";
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		String path = null;
		if(rSet.next()) path = rSet.getString("picture");
		return path;
	}

}
