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

}
