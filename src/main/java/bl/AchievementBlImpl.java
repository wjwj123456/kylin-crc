package bl;

import java.sql.SQLException;
import java.util.List;

import blservice.AchievementBlService;
import data.AchievementDataImpl;
import dataservice.AchievementDataService;
import vo.AchievementVO;
import vo.AssessmentVO;

public class AchievementBlImpl implements AchievementBlService {

	private AchievementDataService achievementDataService = new AchievementDataImpl();

	public int initAchievement(String userName) {
		int flag = -1;
		try {
			flag = achievementDataService.initAchievement(userName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

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

	public void lastReportAccumulate(List<AssessmentVO> vos) {
		double maxEfficiency_mt = 0.0;
		double maxEfficiency_mh = 0.0;
		int maxFindedFault = 0;
		int maxUniqueFault = 0;
		int maxFaultperhour = 0;

		String maxEfficiency_mtName = "";
		String maxEfficiency_mhName = "";
		String maxFindedFaultName = "";
		String maxUniqueFaultName = "";
		String maxFaultperhourName = "";

		for (AssessmentVO vo : vos) {
			double efficiency_mt = (double) vo.getFindedfaults() / (double) vo.getAssessfaults_mt();
			double efficiency_mh = (double) vo.getFindedfaults() / (double) vo.getAssessfaults_mh();

			if (efficiency_mt > 0.8 && efficiency_mh > 0.8) {
				circumulate(vo.getReviewerName(), "efficiency_count", 1);
			}

			if (efficiency_mt > maxEfficiency_mt)
				maxEfficiency_mtName = vo.getReviewerName();
			if (efficiency_mh > maxEfficiency_mh)
				maxEfficiency_mhName = vo.getReviewerName();
			if (vo.getFindedfaults() > maxFindedFault)
				maxFindedFaultName = vo.getReviewerName();
			if (vo.getUniquefaults() > maxUniqueFault)
				maxUniqueFaultName = vo.getReviewerName();
			if (vo.getFaultsperhour() > maxFaultperhour)
				maxFaultperhourName = vo.getReviewerName();

		}
		circumulate(maxEfficiency_mtName, "experience", 100);
		circumulate(maxEfficiency_mhName, "experience", 100);
		circumulate(maxFindedFaultName, "experience", 100);
		circumulate(maxUniqueFaultName, "experience", 100);
		circumulate(maxFaultperhourName, "experience", 100);
	}

	public void reviewAccumulate(String reviewerName, double time) {
		circumulate(reviewerName, "experience", 100);
		circumulate(reviewerName, "review_count", 1);
		circumulate(reviewerName, "review_time", time);
	}
}
