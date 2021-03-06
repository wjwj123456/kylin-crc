package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.ReportPO;

public interface ReportDataService {
	/**
	 * ldk
	 * 
	 * @param pos
	 * @return 0: 创建成功 1：失败
	 */
	public int createReport(List<ReportPO> pos) throws ClassNotFoundException, SQLException;

	/**
	 * 
	 * 
	 * @param taskname
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<ReportPO> getTempReport(String taskName, String reviewerName)
			throws ClassNotFoundException, SQLException;

	/**
	 * ldk
	 * 
	 * @param po
	 * @return 0 :delete success 1: delete fail
	 */
	public int deleteReport(ReportPO po) throws ClassNotFoundException, SQLException;

	/**
	 * ldk 设置完成一份评审报告的时间
	 * 
	 * @param time
	 * @return 0:设置成功 1：字段重复 2:失败
	 */
	public int setCompleteTime(String taskName, String reviewerName, double time)
			throws ClassNotFoundException, SQLException;

	/**
	 * get merged report
	 * 
	 * @param taskname
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<ReportPO> getMergeReport(String taskname) throws ClassNotFoundException, SQLException;

	/**
	 * 
	 * @param po
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean abandonReport(ReportPO po) throws ClassNotFoundException, SQLException;

	/**
	 * 
	 * @param po
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean recoverReport(ReportPO po) throws ClassNotFoundException, SQLException;
}
