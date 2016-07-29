package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dataservice.ReviewDataService;
import po.TaskPO;
import po.UserPO;
import vo.Language;
import vo.Power;
import vo.State;
import vo.Type;

/**
 * review data impl
 *
 * @author lpt14
 * @since 2016闁跨喐鏋婚幏锟�7闁跨喐鏋婚幏锟�8闁跨喐鏋婚幏锟�
 * @see
 */
public class ReviewDataImpl implements ReviewDataService {

	private ResultSet rSet = null;
	private PreparedStatement pStatement = null;

	/**
	 * save review infomation to database
	 *
	 * @author lpt14
	 * @since 2016闁跨喐鏋婚幏锟�7闁跨喐鏋婚幏锟�8闁跨喐鏋婚幏锟�
	 * @param po
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#saveReviewInfo(po.TaskPO)
	 *
	 */
	public int saveReviewInfo(TaskPO po) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = 0;

		String sql = "INSERT INTO task (uname, tname,type, project,discribe,deadline,state,language,power) VALUES (?, ?, ?, ?, ?, ?,?,?,? )";
		Timestamp tt = new Timestamp(po.getDeadline().getTime());
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, po.getUserName());
		pStatement.setString(2, po.getTaskName());
		pStatement.setString(3, String.valueOf(po.getType()));
		pStatement.setString(4, po.getProject());
		pStatement.setString(5, po.getDescribe());
		pStatement.setTimestamp(6, tt);
		pStatement.setInt(7, po.getState());
		pStatement.setString(8, po.getLanguage().toString());
		pStatement.setString(9, String.valueOf(po.getPower()));
		try {
			int j = pStatement.executeUpdate();
			if (j == 1) {
				flag = 0;
			} else {
				flag = 2;
			}
		} catch (Exception SQLIntegrityConstraintViolationException) {
			flag = 1;
			System.out.println("conflict!");
		}
		DBManager.closeConnection();

		String sql1 = "INSERT INTO power (tname, uname) VALUES (?, ? )";

		pStatement = DBManager.getPreparedStatement(sql1);
		pStatement.setString(1, po.getTaskName());
		pStatement.setString(2, "");
		System.out.println(po.getTaskName());

		pStatement.executeUpdate();
		DBManager.closeConnection();
		return flag;
	}

	/**
	 * TODO:闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
	 *
	 * @author lpt14
	 * @since 2016闁跨喐鏋婚幏锟�7闁跨喐鏋婚幏锟�8闁跨喐鏋婚幏锟�
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

		String sql = "SELECT * FROM task WHERE state <> 2 and uname = '" + userName + "'" + "order by " + "'deadline'"
				+ "DESC";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7),
					Language.valueOf(rSet.getString(8)), Power.valueOf(rSet.getString(9)));
			poList.add(po);

		}

		DBManager.closeConnection();
		return poList;

	}

	/**
	 * TODO:闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
	 *
	 * @author lpt14
	 * @since 2016闁跨喐鏋婚幏锟�7闁跨喐鏋婚幏锟�8闁跨喐鏋婚幏锟�
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

		String sql = "SELECT * FROM task WHERE state = 2 and uname = '" + userName + "'" + "order by " + "'deadline'"
				+ "DESC";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7),
					Language.valueOf(rSet.getString(8)), Power.valueOf(rSet.getString(9)));
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
	 * * @author ldk14
	 * 
	 * @param keyword,language
	 * @return + * @throws SQLException + * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#searchUserByKeyword(java.lang.String)
	 *
	 */
	@Override
	public List<TaskPO> searchTasksByKeyword(String keyword, Language language)
			throws SQLException, ClassNotFoundException {
		List<TaskPO> poList = new ArrayList<TaskPO>();

		String sql = "SELECT * FROM task WHERE tname like '%" + keyword + "%' and language = '" + language.toString()
				+ "' and power = '" + Power.PUBLIC.toString() + "'";
		try {
			rSet = DBManager.getResultSet(sql);

			while (rSet.next()) {
				TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
						rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7),
						Language.valueOf(rSet.getString(8)), Power.valueOf(rSet.getString(9)));
				poList.add(po);
			}
		} catch (Exception SQLException) {
			System.out.println("user not found");
		} finally {
			DBManager.closeConnection();
		}

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

	@Override
	public int joinReview(String taskName, String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			String sql = "INSERT INTO review (tname ,uname, state) VALUES (?, ?, ?)";

			pStatement = DBManager.getPreparedStatement(sql);
			pStatement.setString(1, taskName);
			pStatement.setString(2, userName);
			pStatement.setString(3, State.agree.toString());
			int j = pStatement.executeUpdate();
			if (j == 0) {
				flag = 2;
			}
			DBManager.closeConnection();

		} catch (Exception SQLIntegrityConstraintViolationException) {
			flag = 1;
		}

		return flag;
	}

	/**
	 *
	 * @author ldk14
	 * @since 2016闁跨喐鏋婚幏锟�7闁跨喐鏋婚幏锟�9闁跨喐鏋婚幏锟�
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
					rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7),
					Language.valueOf(rSet.getString(8)), Power.valueOf(rSet.getString(9)));
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
					rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7),
					Language.valueOf(rSet.getString(8)), Power.valueOf(rSet.getString(9)));
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
						rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7),
						Language.valueOf(rSet.getString(8)), Power.valueOf(rSet.getString(9)));
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
						rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7),
						Language.valueOf(rSet.getString(8)), Power.valueOf(rSet.getString(9)));
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
	 * TODO:闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
	 *
	 * @author lpt14
	 * @since 2016闁跨喐鏋婚幏锟�7闁跨喐鏋婚幏锟�14闁跨喐鏋婚幏锟�
	 * @param userName
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @see dataservice.ReviewDataService#getUserState(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public State getUserState(String userName, String taskName) throws SQLException {
		String name = "";
		try {
			name = getTaskPOByTaskName(taskName).getUserName();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(userName);
		System.out.println(name);
		if (userName.equals(name)) {
			if (getTaskState(taskName).equals(State.ownerfinish)) {
				return State.merged;
			} else {
				return State.commit;
			}

		} else {
			String sql = "SELECT * FROM review WHERE uname = '" + userName + "' and tname='" + taskName + "'";
			rSet = DBManager.getResultSet(sql);
			State state = State.refuse;
			while (rSet.next()) {
				state = State.valueOf(rSet.getString(3));
			}

			DBManager.closeConnection();

			return state;
		}
	}

	/**
	 * TODO:闁挎稑鐗婇弻鐔封枖閺囩喎浼庨弶鈺佸簻缁憋拷
	 *
	 * @author lpt14
	 * @since 2016妤犵儑鎷�7闁哄牞鎷�20闁哄喛鎷�
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @see dataservice.ReviewDataService#getTaskState(java.lang.String)
	 *
	 */
	@Override
	public State getTaskState(String taskName) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM task WHERE tname = '" + taskName + "'";
		rSet = DBManager.getResultSet(sql);
		State state = null;
		int flag = 0;
		while (rSet.next()) {
			flag = rSet.getInt(7);
		}
		if (flag == 0) {
			state = State.work;
		} else if (flag == 1) {
			state = State.timefinish;
		} else {
			state = State.ownerfinish;
		}
		DBManager.closeConnection();

		return state;

	}

	@Override
	public int setTaskState(State state, String taskName) throws SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		String sql = "UPDATE task SET state  = ?  WHERE tname = '" + taskName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setInt(1, State.getTaskState(state));
		int i = pStatement.executeUpdate();
		if (i == 1)
			flag = 0;
		else
			flag = 3;
		DBManager.closeConnection();
		return flag;
	}

	/**
	 * TODO:閿涘牊鏌熷▔鏇熷伎鏉╁府绱�
	 *
	 * @author lpt14
	 * @since 2016楠烇拷7閺堬拷22閺冿拷
	 * @param userName
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @see dataservice.ReviewDataService#isOwner(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public boolean isOwner(String userName, String taskName) throws SQLException {
		// TODO Auto-generated method stub
		String name = "";
		try {
			name = getTaskPOByTaskName(taskName).getUserName();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (userName.equals(name)) {

			return true;

		} else {
			return false;

		}

	}

	@Override
	public List<TaskPO> getAllDoingTaskList() throws ClassNotFoundException, SQLException {
		List<TaskPO> poList = new ArrayList<TaskPO>();

		String sql = "SELECT * FROM task WHERE state = 0";
		Connection connection = DBManager.connect();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			TaskPO po = new TaskPO(resultSet.getString(1), resultSet.getString(2), Type.valueOf(resultSet.getString(3)),
					resultSet.getString(4), resultSet.getString(5), resultSet.getTimestamp(6), resultSet.getInt(7),
					Language.valueOf(resultSet.getString(8)), Power.valueOf(resultSet.getString(9)));
			poList.add(po);

		}

		DBManager.stopAll(resultSet, statement, connection);
		return poList;
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月28日
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#isPublic(java.lang.String)
	 *
	 */
	@Override
	public boolean isPublic(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM task WHERE tname = '" + taskName + "'";
		Power power = Power.PRIVATE;
		Connection connection = DBManager.connect();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
			power = Power.valueOf(resultSet.getString(9));
		}
		DBManager.stopAll(resultSet, statement, connection);
		if (power.equals(Power.PRIVATE)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月28日
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#isReviewer(java.lang.String)
	 *
	 */
	@Override
	public boolean isReviewer(String taskName, String userName) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		if (isOwner(userName, taskName)) {
			return true;
		} else {
			State state = State.refuse;
			String sql = "SELECT * FROM review WHERE tname = '" + taskName + "' and uname = '" + userName + "'";
			Connection connection = DBManager.connect();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				state = State.valueOf(resultSet.getString(3));
			}

			DBManager.stopAll(resultSet, statement, connection);
			if (state.equals(State.refuse)) {
				return false;
			} else {
				return true;
			}
		}
	}

}
