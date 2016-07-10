package dataservice;

import java.util.List;

import po.ReportPO;


public interface ReportDataService {
	/**
	 * ldk
	 * @param vos
	 * @return 0: 创建成功        1：失败
	 */
	public int createReport(List<ReportPO> pos);
	
	/**
	 * ldk
	 * 设置完成一份评审报告的时间
	 * @param time
	 * @return 0:设置成功       1：设置失败
	 */
	public int setCompleteTime(double time);
}
