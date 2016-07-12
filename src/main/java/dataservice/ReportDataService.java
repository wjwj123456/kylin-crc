package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.ReportPO;


public interface ReportDataService {
	/**
	 * ldk
	 * @param vos
	 * @return 0: �����ɹ�        1��ʧ��
	 */
	public int createReport(List<ReportPO> pos)throws ClassNotFoundException, SQLException;
	
	/**
	 * ldk
	 * �������һ�����󱨸��ʱ��
	 * @param time
	 * @return 0:���óɹ�        1���ֶ��ظ�    2:ʧ��
	 */
	public int setCompleteTime(String taskName, String reviewerName ,double time)throws ClassNotFoundException, SQLException;
}
