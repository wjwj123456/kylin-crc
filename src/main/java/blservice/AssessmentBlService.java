package blservice;

import java.util.List;

import vo.AssessmentVO;

public interface AssessmentBlService {

	/**
	 * ldk get the history assessment fault counts
	 * 
	 * @param taskName
	 * @return
	 */
	public int[] getHistoryAssessmentValues_Mt(String taskName);

	/**
	 * ldk get the history finded fault counts
	 * 
	 * @param taskName
	 * @return
	 */
	public int[] getHistoryFaultValues(String taskName);

	/**
	 * ldk get all ananlyse of reviwers' reports
	 * 
	 * @param taskName
	 * @return
	 */
	public List<AssessmentVO> getAllAssessments(String taskName);

	/**
	 * ldk get the history assessment fault counts
	 * 
	 * @param taskName
	 * @return
	 */
	public int[] getHistoryAssessmentValues_Mh(String taskName);

}
