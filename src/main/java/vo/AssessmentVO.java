package vo;

import java.io.Serializable;

import po.AssessmentPO;

public class AssessmentVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reviewerName;
	private int assessfaults;
	private int findedfaults;

	public AssessmentVO(String reviewerName, int assessfaults, int findedfaults) {
		super();
		this.reviewerName = reviewerName;
		this.assessfaults = assessfaults;
		this.findedfaults = findedfaults;
	}

	public AssessmentVO(AssessmentPO po) {
		super();
		this.reviewerName = po.getReviewerName();
		this.assessfaults = po.getAssessfaults();
		this.findedfaults = po.getFindedfaults();
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
