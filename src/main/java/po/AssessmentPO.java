package po;

import vo.AssessmentVO;

public class AssessmentPO {
	private String reviewerName;
	private int assessfaults;
	private int findedfaults;

	public AssessmentPO(String reviewerName, int assessfaults, int findedfaults) {
		super();
		this.reviewerName = reviewerName;
		this.assessfaults = assessfaults;
		this.findedfaults = findedfaults;
	}

	public AssessmentPO(AssessmentVO vo) {
		super();
		this.reviewerName = vo.getReviewerName();
		this.assessfaults = vo.getAssessfaults();
		this.findedfaults = vo.getFindedfaults();
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public int getAssessfaults() {
		return assessfaults;
	}

	public void setAssessfaults(int assessfaults) {
		this.assessfaults = assessfaults;
	}

	public int getFindedfaults() {
		return findedfaults;
	}

	public void setFindedfaults(int findedfaults) {
		this.findedfaults = findedfaults;
	}

}
