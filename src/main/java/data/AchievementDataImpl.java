package data;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataservice.AchievementDataService;
import po.AchievementPO;

public class AchievementDataImpl implements AchievementDataService {

	private ResultSet rSet = null;

	@Override
	public AchievementPO getAchievement(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM achievement WHERE uname = '" + userName + "'";
		rSet = DBManager.getResultSet(sql);
		AchievementPO po = new AchievementPO(userName, rSet.getInt(2), rSet.getDouble(3), rSet.getInt(4),
				rSet.getInt(5));
		return po;
	}

}
