package dataservice;

import java.sql.SQLException;

import po.AchievementPO;

public interface AchievementDataService {

	/**
	 * ldk get one's achievement
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public AchievementPO getAchievement(String userName) throws SQLException, ClassNotFoundException;
}
