package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.TaskPO;

public interface InviteDataService {
	/**
	 * ldk
	 * 
	 * @param reviewerName
	 * @return TaskPOs
	 */
	public List<TaskPO> getInvitationInfo(String reviewerName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk
	 * 
	 * @param vo
	 * @return 0:delete success 1£ºdelete fail
	 */
	public int deleteInvitationInfo(String userName, String taskName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk get all tasks on doing
	 * 
	 * @param createrName
	 * @return TaskPOs
	 */
	public List<TaskPO> getAllDoingTask(String createrName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk get all completed tasks
	 * 
	 * @param createrName
	 * @return TaskPOs
	 */
	public List<TaskPO> getAllCompleteTask(String createrName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk
	 * 
	 * @param taskName
	 * @return List<String>
	 */
	public List<String> getAgreeUser(String taskName) throws SQLException, ClassNotFoundException;

	/**
	 * ldk
	 * 
	 * @param taskName
	 * @return List<String>
	 */
	public List<String> getDisagreeUser(String taskName) throws SQLException, ClassNotFoundException;
}
