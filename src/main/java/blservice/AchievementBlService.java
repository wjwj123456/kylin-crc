package blservice;

import vo.AchievementVO;

public interface AchievementBlService {

	/**
	 * ldk get one's achievement
	 * 
	 * @param userName
	 * @return
	 */
	public AchievementVO getAchievement(String userName);
}
