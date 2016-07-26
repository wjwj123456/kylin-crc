package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataservice.InviteDataService;
import po.TaskPO;
import vo.Language;
import vo.Power;
import vo.Type;

public class InviteDataImpl implements InviteDataService {

	private PreparedStatement pStatement;
	private ResultSet rSet = null;

	/**
	 * ldk14 get the invitation info
	 */

	public List<TaskPO> getInvitationInfo(String reviewerName) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		String sql = "SELECT * FROM review WHERE uname = '" + reviewerName + "' and state = 'refuse'";
		rSet = DBManager.getResultSet(sql);
		List<String> list = new ArrayList<String>();
		while (rSet.next()) {
			list.add(rSet.getString(1));
		}

		for (int i = 0; i < list.size(); i++) {

			rSet = DBManager.getResultSet("SELECT * FROM task WHERE tname = '" + list.get(i) + "'");
			while (rSet.next()) {
				TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
						rSet.getString(4), rSet.getString(5), rSet.getTimestamp(6), rSet.getInt(7),
						Language.valueOf(rSet.getString(8)), Power.valueOf(rSet.getString(9)));
				poList.add(po);
			}
		}

		DBManager.closeConnection();
		return poList;
	}

	/**
	 * ldk14 0:delete success 1��delete fail
	 */
	public int deleteInvitationInfo(String userName, String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		int flag = -1;
		String sql = "DELETE   FROM review WHERE uname = '" + userName + "' and tname = '" + taskName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		int i = pStatement.executeUpdate();

		if (i == 1)
			flag = 0;
		else
			flag = 1;

		DBManager.closeConnection();
		return flag;
	}

	/**
	 * ldk14 get all tasks on doing
	 */

	public List<TaskPO> getAllDoingTask(String createrName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		String sql = "SELECT * FROM task WHERE uname = '" + createrName + "' and state = 0";

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
	 * ldk14 get all completed tasks
	 */
	public List<TaskPO> getAllCompleteTask(String createrName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		String sql = "SELECT * FROM task WHERE uname = '" + createrName + "' and state = 1";
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
	 * ldk14 get user name who has agreed the invitation
	 */
	public List<String> getAgreeUser(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String sql = "SELECT uname FROM review WHERE tname = '" + taskName + "' and state <> 'refuse'";

		rSet = DBManager.getResultSet(sql);

		List<String> list = new ArrayList<String>();
		while (rSet.next()) {
			list.add(rSet.getString(1));
		}
		DBManager.closeConnection();
		return list;
	}

	public List<String> getDisagreeUser(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String sql = "SELECT uname FROM review WHERE tname = '" + taskName + "' and state = 'refuse'";

		rSet = DBManager.getResultSet(sql);

		List<String> list = new ArrayList<String>();
		while (rSet.next()) {
			list.add(rSet.getString(1));
		}
		DBManager.closeConnection();
		return list;
	}

}
