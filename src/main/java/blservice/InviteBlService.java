package blservice;

import java.sql.SQLException;
import java.util.List;

import vo.TaskVO;

public interface InviteBlService {
	/**
	 *    ldk
	 * @param reviewerName
	 * @return TaskVOs
	 */
	public List<TaskVO> getInvitationInfo(String reviewerName)throws SQLException, ClassNotFoundException;
	
	
	/**
	 *    ldk
	 * @param vo
	 * @return 0:ɾ���ɹ�   1��ɾ��ʧ��
	 */
	public int deleteInvitationInfo(String userName,String taskName); 
}
