package dataservice;

import java.sql.SQLException;

import po.AchievementPO;

public interface AchievementDataService {

	/**
	 * ldk
	 * 
	 * @param userName
	 * @return 0:sucess 1:conflict 2:fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int initAchievement(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk get one's achievement
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public AchievementPO getAchievement(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int updateReviewCount(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk
	 * 
	 * @param userName
	 * @param time
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int updateReviewTime(String userName, double time) throws SQLException, ClassNotFoundException;

	/**
	 * ldk
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int updateEfficiencyCount(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int updateExperience(String userName, int experience) throws SQLException, ClassNotFoundException;
}
