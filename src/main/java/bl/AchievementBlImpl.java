package bl;

import java.sql.SQLException;

import blservice.AchievementBlService;
import data.AchievementDataImpl;
import dataservice.AchievementDataService;
import vo.AchievementVO;

public class AchievementBlImpl implements AchievementBlService {

	private AchievementDataService achievementDataService = new AchievementDataImpl();

	@Override
	public AchievementVO getAchievement(String userName) {
		// TODO Auto-generated method stub
		AchievementVO vo = null;
		try {
			vo = new AchievementVO(achievementDataService.getAchievement(userName));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	public int circumulate(String userName, String control, double value) {
		int flag = -1;
		try {
			switch (control) {
			case "review_count":
				flag = achievementDataService.updateReviewCount(userName);
				break;
			case "review_time":
				flag = achievementDataService.updateReviewTime(userName, value);
				break;
			case "efficiency_count":
				flag = achievementDataService.updateEfficiencyCount(userName);
				break;
			case "experience":
				flag = achievementDataService.updateExperience(userName, (int) value);
				break;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
