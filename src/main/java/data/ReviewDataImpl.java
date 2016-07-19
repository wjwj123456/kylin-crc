package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dataservice.ReviewDataService;
import po.TaskPO;
import po.UserPO;
import vo.State;
import vo.Type;

/**
 * review data impl
 *
 * @author lpt14
 * @since 2016��7��8��
 * @see
 */
public class ReviewDataImpl implements ReviewDataService {

	private ResultSet rSet = null;
	private PreparedStatement pStatement = null;

	/**
	 * save review infomation to database
	 *
	 * @author lpt14
	 * @since 2016��7��8��
	 * @param po
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#saveReviewInfo(po.TaskPO)
	 *
	 */
	public int saveReviewInfo(TaskPO po) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = 0;

		String sql = "INSERT INTO task (uname, tname,type, project,discribe,deadline,state) VALUES (?, ?, ?, ?, ?, ?,? )";
		Timestamp tt = new Timestamp(po.getDeadline().getTime());
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, po.getUserName());
		pStatement.setString(2, po.getTaskName());
		pStatement.setString(3, String.valueOf(po.getType()));
		pStatement.setString(4, po.getProject());
		pStatement.setString(5, po.getDescribe());
		pStatement.setTimestamp(6, tt);
		pStatement.setInt(7, po.getState());
		try {
			int i = pStatement.executeUpdate();
			if (i == 0) {
				flag = 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("task has exist!");
		}
		DBManager.closeConnection();
		return flag;
	}

	/**
	 * TODO:������������
	 *
	 * @author lpt14
	 * @since 2016��7��8��
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList(java.lang.String)
	 *
	 */
	public List<TaskPO> getDoingTaskList(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		String sql = "SELECT * FROM task WHERE state = 0 and uname = '" + userName + "'" + "order by " + "'deadline'"
				+ "DESC";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7));
			poList.add(po);

		}

		DBManager.closeConnection();
		return poList;

	}

	/**
	 * TODO:������������
	 *
	 * @author lpt14
	 * @since 2016��7��8��
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList(java.lang.String)
	 *
	 */
	public List<TaskPO> getEndTaskList(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		String sql = "SELECT * FROM task WHERE state = 1 and uname = '" + userName + "'" + "order by " + "'deadline'"
				+ "DESC";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7));
			poList.add(po);

		}

		DBManager.closeConnection();
		return poList;

	}

	/**
	 *
	 * - * @author lpt14 + * @author ldk14
	 * 
	 * @param keyword
	 * @return + * @throws SQLException + * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#searchUserByKeyword(java.lang.String)
	 *
	 */
	public List<UserPO> searchUserByKeyword(String keyword) throws ClassNotFoundException, SQLException {

		// TODO Auto-generated method stub
		List<UserPO> poList = new ArrayList<UserPO>();

		String sql = "SELECT * FROM user WHERE uname like '%" + keyword + "%'";
		try {
			rSet = DBManager.getResultSet(sql);

			while (rSet.next()) {
				UserPO po = new UserPO(rSet.getString(1), rSet.getString(2), rSet.getString(3));
				poList.add(po);

			}
		} catch (Exception SQLException) {
			// TODO: handle exception
			System.out.println("user not found");
		}

		DBManager.closeConnection();
		return poList;
	}

	/**
	 *
	 * @author lpt14
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#saveAcceptReviewer(java.lang.String)
	 *
	 */
	public int saveAcceptReviewer(String userName, String taskName) throws SQLException, ClassNotFoundException {

		// TODO Auto-generated method stub
		int flag = 0;
		System.out.println("adasd");
		String sql = "UPDATE review SET state  = ? WHERE uname = '" + userName + "'and tname='" + taskName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, State.agree.toString());

		int i = pStatement.executeUpdate();
		if (i == 1)
			flag = 0;
		else
			flag = 3;
		DBManager.closeConnection();
		return flag;
	}

	/**
	 *
	 * @author ldk14
	 * @since 2016��7��9��
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#saveInvitation(java.lang.String[])
	 *
	 */
	public int saveInvitation(String[] userName, String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			for (int i = 0; i < userName.length; i++) {
				String sql = "INSERT INTO review (tname ,uname, state) VALUES (?, ?, ?)";

				pStatement = DBManager.getPreparedStatement(sql);
				pStatement.setString(1, taskName);
				pStatement.setString(2, userName[i]);
				pStatement.setString(3, State.refuse.toString());
				int j = pStatement.executeUpdate();
				if (j == 0) {
					flag = 2;
				}
				DBManager.closeConnection();
			}

		} catch (Exception SQLIntegrityConstraintViolationException) {
			flag = 1;
		}

		return flag;
	}

	/**
	 *
	 * @author lpt14
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 *
	 */
	public List<TaskPO> getTaskList() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		String sql = "SELECT * FROM task ";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7));
			poList.add(po);

		}

		DBManager.closeConnection();
		return poList;
	}

	/**
	 *
	 * @author ldk14
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList()
	 */
	public TaskPO getTaskPOByTaskName(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM task WHERE tname = '" + taskName + "'";
		rSet = DBManager.getResultSet(sql);

		if (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7));
			DBManager.closeConnection();
			return po;
		} else
			return new TaskPO();

	}

	@Override
	public List<TaskPO> getJoinedDoingTasksByUserName(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list = getJoinedTaskNamesByUserName(userName);

		List<TaskPO> poList = new ArrayList<TaskPO>();
		for (int i = 0; i < list.size(); i++) {
			String sql = "SELECT * FROM task WHERE tname = '" + list.get(i) + "' and state = 0 ";
			rSet = DBManager.getResultSet(sql);
			if (rSet.next()) {
				TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
						rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7));
				poList.add(po);
			}
			DBManager.closeConnection();
		}
		return poList;
	}

	@Override
	public List<TaskPO> getJoinedEndTasksByUserName(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list = getJoinedTaskNamesByUserName(userName);

		List<TaskPO> poList = new ArrayList<TaskPO>();
		for (int i = 0; i < list.size(); i++) {
			String sql = "SELECT * FROM task WHERE tname = '" + list.get(i) + "' and state = 1 ";
			rSet = DBManager.getResultSet(sql);
			if (rSet.next()) {
				TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
						rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7));
				poList.add(po);
			}
			DBManager.closeConnection();
		}
		return poList;
	}

	public List<String> getJoinedTaskNamesByUserName(String userName) throws SQLException, ClassNotFoundException {
		List<String> list = new ArrayList<String>();

		String sql = "SELECT * FROM review WHERE uname = '" + userName + "' and state <> 'refuse' ";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			list.add(rSet.getString(1));
		}

		DBManager.closeConnection();
		return list;
	}

	/**
	 * TODO:������������
	 *
	 * @author lpt14
	 * @since 2016��7��14��
	 * @param userName
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @see dataservice.ReviewDataService#getState(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public State getState(String userName, String taskName) throws SQLException {
		String name = "";
		try {
			name = getTaskPOByTaskName(taskName).getUserName();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (userName.equals(name)) {
			return State.commit;
		} else {
			String sql = "SELECT * FROM review WHERE uname = '" + userName + "' and tname='" + taskName + "'";
			rSet = DBManager.getResultSet(sql);
			State state = null;
			while (rSet.next()) {
				state = State.valueOf(rSet.getString(3));
			}

			DBManager.closeConnection();

			return state;
		}
	}

}
