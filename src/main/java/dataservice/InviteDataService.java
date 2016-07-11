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
	 * @return 0:删除成功   1：删除失败
	 */
	public int deleteInvitationInfo(String userName,String taskName)throws SQLException, ClassNotFoundException; 
	
	
	/**
	 *    ldk
	 *    得到发起者创建过的正在进行的所有任务
	 * @param createrName
	 * @return TaskPOs
	 */
	public List<TaskPO> getAllDoingTask(String createrName)throws SQLException, ClassNotFoundException;
	
	
	/**
	 *    ldk
	 *    得到发起者创建过的已经完成的所有任务
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
