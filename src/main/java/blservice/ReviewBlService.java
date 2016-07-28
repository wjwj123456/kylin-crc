package blservice;

import java.sql.SQLException;
import java.util.List;

import vo.Language;
import vo.State;
import vo.TaskVO;
import vo.UserVO;

public interface ReviewBlService {
	public int saveReviewInfo(TaskVO vo);

	public List<TaskVO> getDoingTaskList(String userName);

	public List<TaskVO> getEndTaskList(String userName);

	public List<TaskVO> getTaskList();

	public List<UserVO> searchUserByKeyword(String keyword) throws ClassNotFoundException;

	public List<TaskVO> searchTasksByKeyword(String keyword, Language language);

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
	 * TODO:������������
	 *
	 * @author ldk14
	 * @since 2016��7��9��
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public TaskVO getTaskVOByTaskName(String taskName);

	/**
	 * TODO:������������ get all tasks that a user has joined
	 * 
	 * @author ldk14
	 * @since 2016��7��12��
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public List<TaskVO> getJoinedDoingTasksByUserName(String userName);

	/**
	 * TODO:������������ get all tasks that a user has joined
	 * 
	 * @author ldk14
	 * @since 2016��7��12��
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public List<TaskVO> getJoinedEndTasksByUserName(String userName);

	public State getUserState(String userName, String taskName);

	public State getTaskState(String taskName);

	public boolean isOwner(String userName, String taskName);

	public List<TaskVO> getAllDoingTaskList();

	public int setState(State state, String taskName);

	public boolean isPublic(String taskName);

	public boolean isReviewer(String taskName, String userName);

}
