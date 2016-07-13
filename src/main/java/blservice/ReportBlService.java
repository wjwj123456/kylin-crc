package blservice;

import java.sql.SQLException;
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
	 * ldk �������һ�����󱨸��ʱ��
	 * 
	 * @param time
	 * @return 0:set success 1��conflict 2:fail
	 */
	public int setCompleteTime(String taskName, String reviewerName, double time);

	/**
	 * ldk get all reports by taskname *
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ReportVO> getAllReportsByTaskName(String taskName);
}
