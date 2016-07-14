package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.TaskPO;
import po.UserPO;
import vo.State;

public interface ReviewDataService {
	/**
	 * @param po
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return int 0 - 锟缴癸拷 1 - 锟斤拷锟斤拷锟斤拷锟截革拷
	 * @author lpt14
	 */
	public int saveReviewInfo(TaskPO po) throws ClassNotFoundException, SQLException;

	public List<TaskPO> getTaskList(String userName) throws SQLException, ClassNotFoundException;

	public List<TaskPO> getTaskList() throws ClassNotFoundException, SQLException;

	public List<UserPO> searchUserByKeyword(String keyword) throws SQLException, ClassNotFoundException;

	/**
	 * save all user who has been invited
	 * 
	 * @param userName
	 * @return int 0 -- success 1-- fail
	 * @author lpt14
	 */
	public int saveInvitation(String[] userName, String taskName) throws SQLException, ClassNotFoundException;

	/**
	 * save user who agree the invitation
	 * 
	 * @param userName
	 * @return int 0 -- success 1-- fail
	 * @author lpt14
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int saveAcceptReviewer(String userName, String taskName) throws SQLException, ClassNotFoundException;

	/**
	 * TODO:（方法描述）
	 *
	 * @author ldk14
	 * @since 2016年7月11日
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public TaskPO getTaskPOByTaskName(String taskName) throws SQLException, ClassNotFoundException;

	/**
	 * TODO:（方法描述） get all tasks that a user has joined
	 * 
	 * @author ldk14
	 * @since 2016年7月12日
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public List<TaskPO> getJoinedDoingTasksByUserName(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * TODO:（方法描述） get all tasks that a user has joined
	 * 
	 * @author ldk14
	 * @since 2016年7月12日
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public List<TaskPO> getJoinedEndTasksByUserName(String userName) throws SQLException, ClassNotFoundException;

	public State getState(String userName, String taskName) throws SQLException;
}
