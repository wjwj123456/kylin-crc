package vo;

import java.io.Serializable;

import po.AssessmentPO;

public class AssessmentVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reviewerName;
	private int assessfaults_mt;
	private int assessfaults_mh;
	private int findedfaults;

	public AssessmentVO(String reviewerName, int assessfaults_mt, int assessfaults_mh, int findedfaults) {
		super();
		this.reviewerName = reviewerName;
		this.assessfaults_mt = assessfaults_mt;
		this.assessfaults_mh = assessfaults_mh;
		this.findedfaults = findedfaults;
	}

	public AssessmentVO(AssessmentPO po) {
		super();
		this.reviewerName = po.getReviewerName();
		this.assessfaults_mt = po.getAssessfaults_mt();
		this.assessfaults_mh = po.getAssessfaults_mh();
		this.findedfaults = po.getFindedfaults();
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public int getAssessfaults_mt() {
		return assessfaults_mt;
	}

	public void setAssessfaults_mt(int assessfaults_mt) {
		this.assessfaults_mt = assessfaults_mt;
	}

	public int getAssessfaults_mh() {
		return assessfaults_mh;
	}

	public void setAssessfaults_mh(int assessfaults_mh) {
		this.assessfaults_mh = assessfaults_mh;
	}

	public int getFindedfaults() {
		return findedfaults;
	}

	public void setFindedfaults(int findedfaults) {
		this.findedfaults = findedfaults;
	}
}
