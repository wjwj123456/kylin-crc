package blservice;

import java.util.List;

import vo.ReportVO;

public interface ReportBlService {

	/**
	 * ldk
	 * @param vos
	 * @return 0: 创建成功        1：失败
	 */
	public int createReport(List<ReportVO> vos);
	
	/**
	 * ldk
	 * 设置完成一份评审报告的时间
	 * @param time
	 * @return 0:设置成功       1：设置失败
	 */
	public int setCompleteTime(double time);
}
