package dataservice;

import java.util.List;

import po.ReportPO;


public interface ReportDataService {
	/**
	 * ldk
	 * @param vos
	 * @return 0: �����ɹ�        1��ʧ��
	 */
	public int createReport(List<ReportPO> pos);
	
	/**
	 * ldk
	 * �������һ�����󱨸��ʱ��
	 * @param time
	 * @return 0:���óɹ�       1������ʧ��
	 */
	public int setCompleteTime(double time);
}
