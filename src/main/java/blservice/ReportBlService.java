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
	 * ldk
	 * 
	 * @param vo
	 * @return 0 :delete success 1: delete fail
	 */
	public int deleteReport(ReportVO vo);

	/**
	 * ldk 设置完成一份评审报告的时间
	 * 
	 * @param time
	 * @return 0:set success 1：conflict 2:fail
	 */
	public int setCompleteTime(String taskName, String reviewerName, double time);

	/**
	 * 
	 * @param vo
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean abandonReport(ReportVO vo) throws ClassNotFoundException, SQLException;
	
	/**
	 * ldk get all reports by taskname *
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ReportVO> getAllReportsByTaskName(String taskName);
}
