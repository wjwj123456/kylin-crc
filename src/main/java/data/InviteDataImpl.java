package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataservice.InviteDataService;
import po.TaskPO;
import vo.Type;

public class InviteDataImpl implements InviteDataService {

	private Connection connection;
	private PreparedStatement pStatement;

	/**
	 * ldk14 get the invitation info
	 */

	public List<TaskPO> getInvitationInfo(String reviewerName) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		connection = DBManager.connect();
		String sql = "SELECT * FROM review WHERE uname = '" + reviewerName + "' and isAgree = 0";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();

		List<String> list = new ArrayList<String>();
		while (rSet.next()) {
			list.add(rSet.getString(1));
		}

		for (int i = 0; i < list.size(); i++) {
			pStatement = connection.prepareStatement("SELECT * FROM task WHERE tname = '" + list.get(i) + "'");
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
						rSet.getString(4), rSet.getString(5), rSet.getDate(6), rSet.getInt(7));
				poList.add(po);
			}
		}

		DBManager.stopAll(rSet, pStatement, connection);
		return poList;
	}

	/**
	 * ldk14 0:delete success 1£ºdelete fail
	 */
	public int deleteInvitationInfo(String userName, String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		connection = DBManager.connect();
		int flag = -1;
		String sql = "DELETE   FROM review WHERE uname = '" + userName + "' and tname = '" + taskName + "'";
		pStatement = connection.prepareStatement(sql);
		int i = pStatement.executeUpdate();

		if (i == 1)
			flag = 0;
		else
			flag = 1;

		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	/**
	 * ldk14 get all tasks on doing
	 */

	public List<TaskPO> getAllDoingTask(String createrName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		connection = DBManager.connect();
		String sql = "SELECT * FROM task WHERE uname = '" + createrName + "' and state = 0";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();

		while (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getDate(6), rSet.getInt(7));
			poList.add(po);
		}

		DBManager.stopAll(rSet, pStatement, connection);
		return poList;
	}

	/**
	 * ldk14 get all completed tasks
	 */
	public List<TaskPO> getAllCompleteTask(String createrName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();

		connection = DBManager.connect();
		String sql = "SELECT * FROM task WHERE uname = '" + createrName + "' and state = 1";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();

		while (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getDate(6), rSet.getInt(7));
			poList.add(po);
		}

		DBManager.stopAll(rSet, pStatement, connection);
		return poList;
	}

	/**
	 * ldk14 get user name who has agreed the invitation
	 */
	public List<String> getAgreeUser(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		connection = DBManager.connect();
		String sql = "SELECT uname FROM review WHERE tname = '" + taskName + "' and isAgree = 1";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();

		List<String> list = new ArrayList<String>();
		while (rSet.next()) {
			list.add(rSet.getString(1));
		}
		return list;
	}

	public List<String> getDisagreeUser(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		connection = DBManager.connect();
		String sql = "SELECT uname FROM review WHERE tname = '" + taskName + "' and isAgree = 0";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();

		List<String> list = new ArrayList<String>();
		while (rSet.next()) {
			list.add(rSet.getString(1));
		}
		return list;
	}

}
