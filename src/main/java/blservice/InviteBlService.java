package blservice;

import java.util.List;

import vo.TaskVO;

public interface InviteBlService {
	/**
	 * ldk
	 * 
	 * @param reviewerName
	 * @return TaskVOs
	 */
	public List<TaskVO> getInvitationInfo(String reviewerName);

	/**
	 * ldk
	 * 
	 * @param vo
	 * @return 0:delete success 1£ºdelete fail
	 */
	public int deleteInvitationInfo(String userName, String taskName);

	/**
	 * ldk get all tasks on doing
	 * 
	 * @param createrName
	 * @return TaskVOs
	 */
	public List<TaskVO> getAllDoingTask(String createrName);

	/**
	 * ldk get all completed tasks
	 * 
	 * @param createrName
	 * @return TaskVOs
	 */
	public List<TaskVO> getAllCompleteTask(String createrName);

	/**
	 * ldk
	 * 
	 * @param taskName
	 * @return List<String>
	 */
	public List<String> getAgreeUser(String taskName);

	/**
	 * ldk
	 * 
	 * @param taskName
	 * @return List<String>
	 */
	public List<String> getDisagreeUser(String taskName);
}
