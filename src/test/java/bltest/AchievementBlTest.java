package bltest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bl.AchievementBlImpl;
import vo.AchievementVO;

public class AchievementBlTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAchievement() {
		AchievementBlImpl achievementBlImpl = new AchievementBlImpl();
		List<AchievementVO> vos = new ArrayList<AchievementVO>();
		vos.add(achievementBlImpl.getAchievement("aoliao"));
		System.out.println(vos.get(0).getEfficiency_count());
		System.out.println(vos.get(0).getExperience());
		System.out.println(vos.get(0).getUserName());

		System.out.println(vos.get(0).getReview_count());
		System.out.println(vos.get(0).getReview_time());
		System.out.println(vos.get(0).isEfficiency_count_5_achi());
		System.out.println(vos.get(0).isEfficiency_count_10_achi());
		System.out.println(vos.get(0).isEfficiency_count_20_achi());
		System.out.println(vos.get(0).isEfficiency_count_50_achi());
		System.out.println(vos.get(0).isEfficiency_count_100_achi());
		System.out.println(vos.get(0).isReview_count_5_achi());
		System.out.println(vos.get(0).isReview_count_10_achi());
		System.out.println(vos.get(0).isReview_count_20_achi());
		System.out.println(vos.get(0).isReview_count_50_achi());
		System.out.println(vos.get(0).isReview_count_100_achi());
		System.out.println(vos.get(0).isReview_count_200_achi());
		System.out.println(vos.get(0).isReview_count_500_achi());
	}
}
