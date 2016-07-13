package blservice;

public interface AssessmentBlService {

	/**
	 * ldk get the history assessment fault counts
	 * 
	 * @param taskName
	 * @return
	 */
	public double[] getHistoryAssessmentValues(String taskName);
}
