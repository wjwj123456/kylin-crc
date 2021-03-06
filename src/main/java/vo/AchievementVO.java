package vo;

import po.AchievementPO;

public class AchievementVO {

	private String userName;
	private int experience;

	private double review_time;
	private boolean review_time_20_achi;
	private boolean review_time_50_achi;
	private boolean review_time_100_achi;
	private boolean review_time_200_achi;
	private boolean review_time_500_achi;
	private boolean review_time_1000_achi;
	private boolean review_time_2000_achi;

	private int review_count;
	private boolean review_count_5_achi;
	private boolean review_count_10_achi;
	private boolean review_count_20_achi;
	private boolean review_count_50_achi;
	private boolean review_count_100_achi;
	private boolean review_count_200_achi;
	private boolean review_count_500_achi;

	private int efficiency_count;
	private boolean efficiency_count_5_achi;
	private boolean efficiency_count_10_achi;
	private boolean efficiency_count_20_achi;
	private boolean efficiency_count_50_achi;
	private boolean efficiency_count_100_achi;
	private boolean efficiency_count_200_achi;
	private boolean efficiency_count_500_achi;

	public AchievementVO(AchievementPO po) {
		super();
		this.userName = po.getUserName();
		this.experience = po.getExperience();
		this.review_time = po.getReview_time();
		this.review_time_20_achi = (int) this.review_time >= 20;
		this.review_time_50_achi = (int) this.review_time >= 50;
		this.review_time_100_achi = (int) this.review_time >= 100;
		this.review_time_200_achi = (int) this.review_time >= 200;
		this.review_time_500_achi = (int) this.review_time >= 500;
		this.review_time_1000_achi = (int) this.review_time >= 1000;
		this.review_time_2000_achi = (int) this.review_time >= 2000;
		this.review_count = po.getReview_count();
		this.review_count_5_achi = this.review_count >= 5;
		this.review_count_10_achi = this.review_count >= 10;
		this.review_count_20_achi = this.review_count >= 20;
		this.review_count_50_achi = this.review_count >= 50;
		this.review_count_100_achi = this.review_count >= 100;
		this.review_count_200_achi = this.review_count >= 200;
		this.review_count_500_achi = this.review_count >= 500;
		this.efficiency_count = po.getEfficiency_count();
		this.efficiency_count_5_achi = this.efficiency_count >= 5;
		this.efficiency_count_10_achi = this.efficiency_count >= 10;
		this.efficiency_count_20_achi = this.efficiency_count >= 20;
		this.efficiency_count_50_achi = this.efficiency_count >= 50;
		this.efficiency_count_100_achi = this.efficiency_count >= 100;
		this.efficiency_count_200_achi = this.efficiency_count >= 200;
		this.efficiency_count_500_achi = this.efficiency_count >= 500;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public double getReview_time() {
		return review_time;
	}

	public void setReview_time(double review_time) {
		this.review_time = review_time;
	}

	public boolean isReview_time_20_achi() {
		return review_time_20_achi;
	}

	public void setReview_time_20_achi(boolean review_time_20_achi) {
		this.review_time_20_achi = review_time_20_achi;
	}

	public boolean isReview_time_50_achi() {
		return review_time_50_achi;
	}

	public void setReview_time_50_achi(boolean review_time_50_achi) {
		this.review_time_50_achi = review_time_50_achi;
	}

	public boolean isReview_time_100_achi() {
		return review_time_100_achi;
	}

	public void setReview_time_100_achi(boolean review_time_100_achi) {
		this.review_time_100_achi = review_time_100_achi;
	}

	public boolean isReview_time_200_achi() {
		return review_time_200_achi;
	}

	public void setReview_time_200_achi(boolean review_time_200_achi) {
		this.review_time_200_achi = review_time_200_achi;
	}

	public boolean isReview_time_500_achi() {
		return review_time_500_achi;
	}

	public void setReview_time_500_achi(boolean review_time_500_achi) {
		this.review_time_500_achi = review_time_500_achi;
	}

	public boolean isReview_time_1000_achi() {
		return review_time_1000_achi;
	}

	public void setReview_time_1000_achi(boolean review_time_1000_achi) {
		this.review_time_1000_achi = review_time_1000_achi;
	}

	public boolean isReview_time_2000_achi() {
		return review_time_2000_achi;
	}

	public void setReview_time_2000_achi(boolean review_time_2000_achi) {
		this.review_time_2000_achi = review_time_2000_achi;
	}

	public int getReview_count() {
		return review_count;
	}

	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}

	public boolean isReview_count_5_achi() {
		return review_count_5_achi;
	}

	public void setReview_count_5_achi(boolean review_count_5_achi) {
		this.review_count_5_achi = review_count_5_achi;
	}

	public boolean isReview_count_10_achi() {
		return review_count_10_achi;
	}

	public void setReview_count_10_achi(boolean review_count_10_achi) {
		this.review_count_10_achi = review_count_10_achi;
	}

	public boolean isReview_count_20_achi() {
		return review_count_20_achi;
	}

	public void setReview_count_20_achi(boolean review_count_20_achi) {
		this.review_count_20_achi = review_count_20_achi;
	}

	public boolean isReview_count_50_achi() {
		return review_count_50_achi;
	}

	public void setReview_count_50_achi(boolean review_count_50_achi) {
		this.review_count_50_achi = review_count_50_achi;
	}

	public boolean isReview_count_100_achi() {
		return review_count_100_achi;
	}

	public void setReview_count_100_achi(boolean review_count_100_achi) {
		this.review_count_100_achi = review_count_100_achi;
	}

	public boolean isReview_count_200_achi() {
		return review_count_200_achi;
	}

	public void setReview_count_200_achi(boolean review_count_200_achi) {
		this.review_count_200_achi = review_count_200_achi;
	}

	public boolean isReview_count_500_achi() {
		return review_count_500_achi;
	}

	public void setReview_count_500_achi(boolean review_count_500_achi) {
		this.review_count_500_achi = review_count_500_achi;
	}

	public int getEfficiency_count() {
		return efficiency_count;
	}

	public void setEfficiency_count(int efficiency_count) {
		this.efficiency_count = efficiency_count;
	}

	public boolean isEfficiency_count_5_achi() {
		return efficiency_count_5_achi;
	}

	public void setEfficiency_count_5_achi(boolean efficiency_count_5_achi) {
		this.efficiency_count_5_achi = efficiency_count_5_achi;
	}

	public boolean isEfficiency_count_10_achi() {
		return efficiency_count_10_achi;
	}

	public void setEfficiency_count_10_achi(boolean efficiency_count_10_achi) {
		this.efficiency_count_10_achi = efficiency_count_10_achi;
	}

	public boolean isEfficiency_count_20_achi() {
		return efficiency_count_20_achi;
	}

	public void setEfficiency_count_20_achi(boolean efficiency_count_20_achi) {
		this.efficiency_count_20_achi = efficiency_count_20_achi;
	}

	public boolean isEfficiency_count_50_achi() {
		return efficiency_count_50_achi;
	}

	public void setEfficiency_count_50_achi(boolean efficiency_count_50_achi) {
		this.efficiency_count_50_achi = efficiency_count_50_achi;
	}

	public boolean isEfficiency_count_100_achi() {
		return efficiency_count_100_achi;
	}

	public void setEfficiency_count_100_achi(boolean efficiency_count_100_achi) {
		this.efficiency_count_100_achi = efficiency_count_100_achi;
	}

	public boolean isEfficiency_count_200_achi() {
		return efficiency_count_200_achi;
	}

	public void setEfficiency_count_200_achi(boolean efficiency_count_200_achi) {
		this.efficiency_count_200_achi = efficiency_count_200_achi;
	}

	public boolean isEfficiency_count_500_achi() {
		return efficiency_count_500_achi;
	}

	public void setEfficiency_count_500_achi(boolean efficiency_count_500_achi) {
		this.efficiency_count_500_achi = efficiency_count_500_achi;
	}

	@Override
	public String toString() {
		return "AchievementVO [userName=" + userName + ", experience=" + experience + ", review_time=" + review_time
				+ ", review_time_20_achi=" + review_time_20_achi + ", review_time_50_achi=" + review_time_50_achi
				+ ", review_time_100_achi=" + review_time_100_achi + ", review_time_200_achi=" + review_time_200_achi
				+ ", review_time_500_achi=" + review_time_500_achi + ", review_time_1000_achi=" + review_time_1000_achi
				+ ", review_time_2000_achi=" + review_time_2000_achi + ", review_count=" + review_count
				+ ", review_count_5_achi=" + review_count_5_achi + ", review_count_10_achi=" + review_count_10_achi
				+ ", review_count_20_achi=" + review_count_20_achi + ", review_count_50_achi=" + review_count_50_achi
				+ ", review_count_100_achi=" + review_count_100_achi + ", review_count_200_achi="
				+ review_count_200_achi + ", review_count_500_achi=" + review_count_500_achi + ", efficiency_count="
				+ efficiency_count + ", efficiency_count_5_achi=" + efficiency_count_5_achi
				+ ", efficiency_count_10_achi=" + efficiency_count_10_achi + ", efficiency_count_20_achi="
				+ efficiency_count_20_achi + ", efficiency_count_50_achi=" + efficiency_count_50_achi
				+ ", efficiency_count_100_achi=" + efficiency_count_100_achi + ", efficiency_count_200_achi="
				+ efficiency_count_200_achi + ", efficiency_count_500_achi=" + efficiency_count_500_achi + "]";
	}

}
