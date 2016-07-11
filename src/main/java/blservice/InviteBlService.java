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
	public List<TaskVO> getInvitationInfo(String reviewerName);
	
	
	/**
	 *    ldk
	 * @param vo
	 * @return 0:删除成功   1：删除失败
	 */
	public int deleteInvitationInfo(String userName,String taskName); 
	
	/**
	 *    ldk
	 *    得到发起者创建过得所有正在进行的任务
	 * @param createrName
	 * @return TaskVOs
	 */
	public List<TaskVO> getAllDoingTask(String createrName);
	
	/**
	 *    ldk
	 *    得到发起者创建过得所有已完成的任务
	 * @param createrName
	 * @return TaskVOs
	 */
	public List<TaskVO> getAllCompleteTask(String createrName);
	
	/**
	 *    ldk
	 * @param taskName
	 * @return List<String>
	 */
	public List<String> getAgreeUser(String taskName);
	
	
	/**
	 *    ldk
	 * @param taskName
	 * @return List<String>
	 */
	public List<String> getDisagreeUser(String taskName);
}
