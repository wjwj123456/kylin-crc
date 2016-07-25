package po;

public class AchievementPO {

	private String userName;
	private int experience;

	private double review_time;
	private int review_count;
	private int efficiency_count;

	public AchievementPO(String userName, int experience, double review_time, int review_count, int efficiency_count) {
		super();
		this.userName = userName;
		this.experience = experience;
		this.review_time = review_time;
		this.review_count = review_count;
		this.efficiency_count = efficiency_count;
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

	public int getReview_count() {
		return review_count;
	}

	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}

	public int getEfficiency_count() {
		return efficiency_count;
	}

	public void setEfficiency_count(int efficiency_count) {
		this.efficiency_count = efficiency_count;
	}

	@Override
	public String toString() {
		return "AchievementPO [userName=" + userName + ", experience=" + experience + ", review_time=" + review_time
				+ ", review_count=" + review_count + ", efficiency_count=" + efficiency_count + "]";
	}

}
