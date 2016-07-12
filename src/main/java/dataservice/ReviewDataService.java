package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.TaskPO;
import po.UserPO;

public interface ReviewDataService {
	/**
	 * @param po
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return int 0 - ï¿½É¹ï¿½ 1 - ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ø¸ï¿½
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
	 * TODO:£¨·½·¨ÃèÊö£©
	 *
	 * @author ldk14
	 * @since 2016Äê7ÔÂ11ÈÕ
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public TaskPO getTaskPOByTaskName(String taskName) throws SQLException, ClassNotFoundException;
}
