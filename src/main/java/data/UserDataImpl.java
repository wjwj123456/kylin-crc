package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataservice.UserDataService;
import po.UserPO;

public class UserDataImpl implements UserDataService {

	private Connection connection;
	private PreparedStatement pStatement;

	public UserPO getUserPOByName(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		connection = DBManager.connect();
		String sql = "SELECT * FROM user WHERE uname = '" + userName + "'";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();

		if (rSet.next()) {
			UserPO po = new UserPO(rSet.getString(1), rSet.getString(2), rSet.getString(3));
			DBManager.stopAll(rSet, pStatement, connection);
			return po;
		} else
			return new UserPO();
	}

}
