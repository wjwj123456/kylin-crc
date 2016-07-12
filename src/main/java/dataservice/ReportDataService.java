package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.ReportPO;


public interface ReportDataService {
	/**
	 * ldk
	 * @param vos
	 * @return 0: 创建成功        1：失败
	 */
	public int createReport(List<ReportPO> pos)throws ClassNotFoundException, SQLException;
	
	/**
	 * ldk
	 * 设置完成一份评审报告的时间
	 * @param time
	 * @return 0:设置成功        1：字段重复    2:失败
	 */
	public int setCompleteTime(String taskName, String reviewerName ,double time)throws ClassNotFoundException, SQLException;
}
