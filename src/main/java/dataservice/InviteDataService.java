package dataservice;

import java.util.List;

import po.TaskPO;


public interface InviteDataService {
	/**
	 *    ldk
	 * @param reviewerName
	 * @return TaskPOs
	 */
	public List<TaskPO> getInvitationInfo(String reviewerName);
	
	
	/**
	 *    ldk
	 * @param vo
	 * @return 0:ɾ���ɹ�   1��ɾ��ʧ��
	 */
	public int deleteInvitationInfo(String userName,String taskName); 
}
