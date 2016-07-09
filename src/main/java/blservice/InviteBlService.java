package blservice;

import java.util.List;

import vo.TaskVO;

public interface InviteBlService {
	/**
	 *    ldk
	 * @param reviewerName
	 * @return TaskVOs
	 */
	public List<TaskVO> getInvitationInfo(String reviewerName);
	
	
	/**
	 *    ldk
	 * @param vo
	 * @return 0:ɾ���ɹ�   1��ɾ��ʧ��
	 */
	public int deleteInvitationInfo(String userName,String taskName); 
}
