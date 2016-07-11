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
	
	
	/**
	 *    ldk
	 *    �õ������ߴ����������ڽ��е���������
	 * @param createrName
	 * @return TaskPOs
	 */
	public List<TaskPO> getAllDoingTask(String createrName)throws SQLException, ClassNotFoundException;
	
	
	/**
	 *    ldk
	 *    �õ������ߴ��������Ѿ���ɵ���������
	 * @param createrName
	 * @return TaskPOs
	 */
	public List<TaskPO> getAllCompleteTask(String createrName)throws SQLException, ClassNotFoundException;
	
	/**
	 *    ldk
	 * @param taskName
	 * @return List<String>
	 */
	public List<String> getAgreeUser(String taskName)throws SQLException, ClassNotFoundException;
	
	
	/**
	 *    ldk
	 * @param taskName
	 * @return List<String>
	 */
	public List<String> getDisagreeUser(String taskName)throws SQLException, ClassNotFoundException;
}
