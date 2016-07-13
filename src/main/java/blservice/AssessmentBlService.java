package blservice;

public interface AssessmentBlService {

	/**
	 * ldk get the history assessment fault counts
	 * 
	 * @param taskName
	 * @return
	 */
	public int[] getHistoryAssessmentValues(String taskName);

	/**
	 * ldk get the history finded fault counts
	 * 
	 * @param taskName
	 * @return
	 */
	public int[] getHistoryFaultValues(String taskName);
}
