package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataservice.AchievementDataService;
import po.AchievementPO;

public class AchievementDataImpl implements AchievementDataService {

	private PreparedStatement pStatement;
	private ResultSet rSet = null;

	@Override
	public AchievementPO getAchievement(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM achievement WHERE uname = '" + userName + "'";
		rSet = DBManager.getResultSet(sql);
		AchievementPO po = null;
		if (rSet.next()) {
			po = new AchievementPO(userName, rSet.getInt(2), rSet.getDouble(3), rSet.getInt(4), rSet.getInt(5));
		}
		DBManager.closeConnection();
		return po;
	}

	@Override
	public int updateReviewCount(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "UPDATE achievement SET review_count = review_count+1 WHERE uname='" + userName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		int i = pStatement.executeUpdate();
		DBManager.closeConnection();
		if (i == 1) {
			i = 0;
		} else {
			i = 1;
		}
		return i;

	}

	@Override
	public int updateReviewTime(String userName, double time) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "UPDATE achievement SET review_time = review_time+" + time + " WHERE uname='" + userName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		int i = pStatement.executeUpdate();
		DBManager.closeConnection();
		if (i == 1) {
			i = 0;
		} else {
			i = 1;
		}
		return i;
	}

	@Override
	public int updateEfficiencyCount(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "UPDATE achievement SET efficiency_count = efficiency_count+1 WHERE uname='" + userName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		int i = pStatement.executeUpdate();
		DBManager.closeConnection();
		if (i == 1) {
			i = 0;
		} else {
			i = 1;
		}
		return i;
	}

	@Override
	public int updateExperience(String userName, int experience) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "UPDATE achievement SET experience = experience+" + experience + " WHERE uname='" + userName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		int i = pStatement.executeUpdate();
		DBManager.closeConnection();
		if (i == 1) {
			i = 0;
		} else {
			i = 1;
		}
		return i;
	}

}
