package blservice;

import java.sql.SQLException;
import java.util.List;

import vo.TaskVO;
import vo.UserVO;

public interface ReviewBlService {
	public int saveReviewInfo(TaskVO vo);

	public List<TaskVO> geTaskList(String userName);

	public List<TaskVO> getTaskList();

	public List<UserVO> searchUserByKeyword(String keyword) throws ClassNotFoundException;

	/**
	 * save all user who has been invited
	 * 
	 * @param userName
	 * @return int 0 -- success 1-- conflict 2: fail
	 * @author lpt14
	 */
	public int saveInvitation(String[] userName, String taskName);

	/**
	 * save user who agree the invitation
	 * 
	 * @param userName
	 * @return int 0 -- success 1-- fail
	 * @author lpt14
	 */
	public int saveAcceptReviewer(String userName, String taskName);

	/**
	 * TODO:（方法描述）
	 *
	 * @author ldk14
	 * @since 2016年7月9日
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public TaskVO getTaskVOByTaskName(String taskName);

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
	public List<TaskVO> getJoinedDoingTasksByUserName(String userName);

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
	public List<TaskVO> getJoinedEndTasksByUserName(String userName);

}
