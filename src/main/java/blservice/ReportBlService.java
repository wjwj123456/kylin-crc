package blservice;

import java.util.List;

import vo.ReportVO;

public interface ReportBlService {

	/**
	 * ldk
	 * @param vos
	 * @return 0: �����ɹ�        1��ʧ��
	 */
	public int createReport(List<ReportVO> vos);
	
	/**
	 * ldk
	 * �������һ�����󱨸��ʱ��
	 * @param time
	 * @return 0:���óɹ�       1���ֶ��ظ�    2:ʧ��
	 */
	public int setCompleteTime(String taskName, String reviewerName ,double time);
}