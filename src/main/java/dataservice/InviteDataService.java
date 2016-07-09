package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.TaskPO;


public interface InviteDataService {
	/**
	 *    ldk
	 * @param reviewerName
	 * @return TaskPOs
	 */
	public List<TaskPO> getInvitationInfo(String reviewerName)throws SQLException, ClassNotFoundException;
	
	
	/**
	 *    ldk
	 * @param vo
	 * @return 0:ɾ���ɹ�   1��ɾ��ʧ��
	 */
	public int deleteInvitationInfo(String userName,String taskName)throws SQLException, ClassNotFoundException; 
}
