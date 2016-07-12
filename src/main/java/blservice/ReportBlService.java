package blservice;

import java.util.List;

import vo.ReportVO;

public interface ReportBlService {

	/**
	 * ldk
	 * 
	 * @param vos
	 * @return 0: success 1 : fail
	 */
	public int createReport(List<ReportVO> vos);

	/**
	 * ldk 设置完成一份评审报告的时间
	 * 
	 * @param time
	 * @return 0:set success 1：conflict 2:fail
	 */
	public int setCompleteTime(String taskName, String reviewerName, double time);
}
