package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataservice.UserDataService;
import po.UserPO;

public class UserDataImpl implements UserDataService {
	private ResultSet rSet = null;

	public UserPO getUserPOByName(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM user WHERE uname = '" + userName + "'";
		rSet = DBManager.getResultSet(sql);

		if (rSet.next()) {
			UserPO po = new UserPO(rSet.getString(1), rSet.getString(2), rSet.getString(3));
			DBManager.closeConnection();
			return po;
		} else
			return new UserPO();
	}

}
