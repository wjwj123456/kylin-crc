package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.ReportPO;

public interface ReportDataService {
	/**
	 * ldk
	 * 
	 * @param pos
	 * @return 0: �����ɹ� 1��ʧ��
	 */
	public int createReport(List<ReportPO> pos) throws ClassNotFoundException, SQLException;

	/**
	 * ldk
	 * 
	 * @param po
	 * @return 0 :delete success 1: delete fail
	 */
	public int deleteReport(ReportPO po) throws ClassNotFoundException, SQLException;

	/**
	 * ldk �������һ�����󱨸��ʱ��
	 * 
	 * @param time
	 * @return 0:���óɹ� 1���ֶ��ظ� 2:ʧ��
	 */
	public int setCompleteTime(String taskName, String reviewerName, double time)
			throws ClassNotFoundException, SQLException;

	/**
	 * ldk get all reports by taskname *
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ReportPO> getAllReportsByTaskName(String taskName) throws ClassNotFoundException, SQLException;
	
	/**
	 * get merged report
	 * @param taskname
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<ReportPO> getMergeReport(String taskname) throws ClassNotFoundException, SQLException;
}
