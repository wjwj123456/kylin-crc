/**
 * @author lpt14
 * @version V1.0
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataservice.MergeDataService;
import po.ReportPO;

public class MergeDataImpl implements MergeDataService {

	public boolean canMerge(String taskName) throws SQLException, ClassNotFoundException {
		boolean flag = false;
		double time = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "SELECT time FROM review WHERE tname = '" + taskName + "'";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		while (rSet.next()) {
			time = rSet.getDouble("time");
			if (time != 0) {
				flag = true;
				break;
			}
		}
		DBManager.stopAll(rSet, pStatement, connection);
		return flag;
	}

	public List<ReportPO> mergeReport(String taskName) throws SQLException, ClassNotFoundException {
		List<ReportPO> list = new ArrayList<ReportPO>();
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "SELECT * FROM report WHERE tname = '" + taskName
				+ "' and state <>1 and state<>3  order by filename , page ,location";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		while (rSet.next()) {
			ReportPO po = new ReportPO(taskName, rSet.getString("uname"), rSet.getString("filename"),
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"), rSet.getInt("state"),
					rSet.getInt("origin"), rSet.getInt("merge"), rSet.getString("operator"));
			list.add(po);
		}
		DBManager.stopAll(rSet, pStatement, connection);
		return list;
	}

	/**
	 * 
	 * state 0 -active 1 - can't be seen after merge 2- delete 3-active no
	 * submit 4-can't seen no submit
	 *
	 * @author lpt14
	 * @since 2016��7��25��
	 * @param reportList
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.MergeDataService#saveMergeReport(java.util.List,
	 *      java.lang.String)
	 *
	 */
	public int saveMergeReport(List<ReportPO> reportList, String taskName, String operator)
			throws SQLException, ClassNotFoundException {
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		ReportPO finalPO = reportList.get(0);
		reportList.remove(0);

		if (finalPO.getOrigin() == 1) {
			String sql2 = "INSERT INTO report (tname,uname,filename,page,location,description,state,origin,merge,operator) VALUES (?,?,?,?,?,?,?,?,?,?)";
			pStatement = connection.prepareStatement(sql2);
			pStatement.setString(1, finalPO.getTaskName());
			pStatement.setString(2, finalPO.getUserName());
			pStatement.setString(3, finalPO.getFileName());
			pStatement.setInt(4, finalPO.getPage());
			pStatement.setInt(5, finalPO.getLocation());
			pStatement.setString(6, finalPO.getDescription());
			pStatement.setInt(7, 3);
			pStatement.setInt(8, 1);
			pStatement.setInt(9, 1);
			pStatement.setString(10, operator);
			pStatement.executeUpdate();
		}
		for (ReportPO po : reportList) {
			String sql = "UPDATE report SET state = ?,operator=? WHERE tname = ? and uname= ? and filename=? and page=? and location=? and description=?  ";
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, 1);
			pStatement.setString(2, operator);
			pStatement.setString(3, taskName);
			pStatement.setString(4, po.getUserName());
			pStatement.setString(5, po.getFileName());
			pStatement.setInt(6, po.getPage());
			pStatement.setInt(7, po.getLocation());
			pStatement.setString(8, po.getDescription());
			pStatement.executeUpdate();
		}

		String sql2 = "UPDATE report SET state = ?, merge= ? ,operator=? WHERE tname = ? and uname= ? and filename=? and page=? and location=? and description=?  ";
		pStatement = connection.prepareStatement(sql2);
		pStatement.setInt(1, 0);
		pStatement.setInt(2, 1);
		pStatement.setString(3, operator);
		pStatement.setString(4, taskName);
		pStatement.setString(5, finalPO.getUserName());
		pStatement.setString(6, finalPO.getFileName());
		pStatement.setInt(7, finalPO.getPage());
		pStatement.setInt(8, finalPO.getLocation());
		pStatement.setString(9, finalPO.getDescription());
		pStatement.executeUpdate();

		for (ReportPO po : reportList) {
			String sql1 = "INSERT INTO merge (final_id, included_id,uname) VALUES (?,  ?,?)";
			pStatement = connection.prepareStatement(sql1);
			if (getID(finalPO) != getID(po)) {
				pStatement.setInt(1, getID(finalPO));
				pStatement.setInt(2, getID(po));
				pStatement.setString(3, getUNameById(getID(po)));
				pStatement.executeUpdate();
			}
		}
		// updateMerge();
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	public int getID(ReportPO po) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT id FROM report WHERE uname = ? AND tname = ? AND filename = ? AND page = ? AND location = ? AND description = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, po.getUserName());
		pStatement.setString(2, po.getTaskName());
		pStatement.setString(3, po.getFileName());
		pStatement.setInt(4, po.getPage());
		pStatement.setInt(5, po.getLocation());
		pStatement.setString(6, po.getDescription());
		ResultSet rSet = pStatement.executeQuery();
		int i = -1;
		int number = 0;
		while (rSet.next()) {
			i = rSet.getInt("id");
			number++;
		}
		DBManager.stopAll(rSet, pStatement, connection);
		if (number == 0)
			return -1;
		else {
			if (number > 1)
				return -2;
			else
				return i;
		}
	}

	public int saveAddedMergeReport(List<ReportPO> reportList, String taskName)
			throws SQLException, ClassNotFoundException {
		int flag = 0;
		// Connection connection = DBManager.connect();
		// PreparedStatement pStatement = null;
		// ReportPO finalPO = reportList.get(0);
		// reportList.remove(0);
		//
		// String sql2 = "INSERT INTO report
		// (tname,uname,filename,page,location,description,state,origin,merge)
		// VALUES (?,?,?,?,?,?,?,?,?)";
		// pStatement = connection.prepareStatement(sql2);
		// pStatement.setString(1, finalPO.getTaskName());
		// pStatement.setString(2, finalPO.getUserName());
		// pStatement.setString(3, finalPO.getFileName());
		// pStatement.setInt(4, finalPO.getPage());
		// pStatement.setInt(5, finalPO.getLocation());
		// pStatement.setString(6, finalPO.getDescription());
		// pStatement.setInt(7, 0);
		// pStatement.setInt(8, 1);
		// pStatement.setInt(9, 1);
		// pStatement.executeUpdate();
		//
		// for (ReportPO po : reportList) {
		// String sql = "UPDATE report SET state = ? WHERE tname = ? and uname=
		// ? and filename=? and page=? and location=? and description=? ";
		// pStatement = connection.prepareStatement(sql);
		// pStatement.setInt(1, 1);
		// pStatement.setString(2, taskName);
		// pStatement.setString(3, po.getUserName());
		// pStatement.setString(4, po.getFileName());
		// pStatement.setInt(5, po.getPage());
		// pStatement.setInt(6, po.getLocation());
		// pStatement.setString(7, po.getDescription());
		// pStatement.executeUpdate();
		// }
		// DBManager.stopAll(null, pStatement, connection);
		// Connection connection1 = DBManager.connect();
		// for (ReportPO po : reportList) {
		// String sql1 = "INSERT INTO merge (final_id, included_id) VALUES (?,
		// ?)";
		// pStatement = connection1.prepareStatement(sql1);
		// pStatement.setInt(1, getID(finalPO));
		// pStatement.setInt(2, getID(po));
		// pStatement.executeUpdate();
		// }
		// updateMerge();
		// DBManager.stopAll(null, pStatement, connection1);
		return flag;
	}

	@Override
	public int saveHistory(String userName, String taskName, int fault, int assessfalut_mt, int assessfalut_mh)
			throws SQLException, ClassNotFoundException {
		int flag = 0;
		String sql = "INSERT INTO history (tname, uname, fault,assessfault_mt,assessfault_mh) VALUES (?, ?, ?,?,?)";
		PreparedStatement pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, taskName);
		pStatement.setString(2, userName);
		pStatement.setInt(3, fault);
		pStatement.setInt(4, assessfalut_mt);
		pStatement.setInt(5, assessfalut_mh);
		pStatement.executeUpdate();
		DBManager.closeConnection();

		return flag;

	}

	/**
	 * @param @param
	 *            userName
	 * @return void
	 * @throws SQLException
	 * @throws @author
	 *             lpt14
	 */
	private void confirmOperation(String userName, String taskName) throws SQLException {
		String sql1 = "UPDATE report SET state = ? WHERE tname = ? and operator= ? and state=3";
		PreparedStatement pStatement1 = DBManager.getPreparedStatement(sql1);
		pStatement1.setInt(1, 0);
		String sql11 = "UPDATE report SET state = ? WHERE tname = ? and operator= ? and state=4";
		PreparedStatement pStatement11 = DBManager.getPreparedStatement(sql11);
		pStatement11.setInt(1, 1);
		DBManager.closeConnection();
		return;

	}

	@Override
	public int deleteMergeRecord(ReportPO po) throws SQLException, ClassNotFoundException {
		int flag = 0;
		Connection connection = DBManager.connect();
		String sql = "UPDATE report SET state = 2 WHERE tname = '" + po.getTaskName() + "' and uname = '"
				+ po.getUserName() + "' and filename = '" + po.getFileName() + "' and page = '" + po.getPage()
				+ "' and location = '" + po.getLocation() + "'";
		Statement statement = connection.createStatement();
		flag = statement.executeUpdate(sql);
		return flag;
	}

	@SuppressWarnings("unused")
	private void updateMerge() throws SQLException, ClassNotFoundException {
		String sql3 = "DELETE FROM merge  WHERE final_id=included_id  ";
		PreparedStatement pStatement = DBManager.getPreparedStatement(sql3);
		int i = pStatement.executeUpdate();
		DBManager.closeConnection();

		ResultSet rSet = null;
		String sql = "SELECT * FROM merge";
		rSet = DBManager.getResultSet(sql);
		List<Integer> ids = new ArrayList<>();
		List<Integer> finalIds = new ArrayList<>();
		while (rSet.next()) {
			finalIds.add(rSet.getInt(1));
			ids.add(rSet.getInt(2));
		}
		DBManager.closeConnection();

		for (int i1 = 0; i1 < ids.size(); i1++) {
			for (int j = 0; j < finalIds.size(); j++) {
				if (ids.get(i1) == finalIds.get(j)) {
					finalIds.set(j, finalIds.get(i1));
				}
			}
		}

		Connection connection = DBManager.connect();

		PreparedStatement pStatement1 = null;

		String sql2 = "UPDATE merge set	final_id=?, uname=? where included_id=?";
		for (int i1 = 0; i1 < ids.size(); i1++) {
			pStatement1 = connection.prepareStatement(sql2);
			pStatement1.setInt(1, finalIds.get(i1));
			pStatement1.setString(2, getUNameById(ids.get(i1)));
			pStatement1.setInt(3, ids.get(i1));
			pStatement1.executeUpdate();
		}

		DBManager.closeConnection();

		return;
	}

	public int saveMergeState(String userName, String taskName) throws SQLException {

		String sql1 = "UPDATE review SET state = ? WHERE tname = ? and uname= ? ";
		PreparedStatement pStatement1 = DBManager.getPreparedStatement(sql1);
		pStatement1.setString(1, "merged");
		pStatement1.setString(2, taskName);
		pStatement1.setString(3, userName);
		int i = pStatement1.executeUpdate();
		DBManager.closeConnection();
		return i;

	}

	@Override
	public int recoverMergeRecord(ReportPO po) throws SQLException, ClassNotFoundException {
		int flag = 0;
		Connection connection = DBManager.connect();
		String sql = "UPDATE report SET state = 0 WHERE tname = '" + po.getTaskName() + "' and uname = '"
				+ po.getUserName() + "' and filename = '" + po.getFileName() + "' and page = '" + po.getPage()
				+ "' and location = '" + po.getLocation() + "'";
		Statement statement = connection.createStatement();
		flag = statement.executeUpdate(sql);
		return flag;
	}

	public String getUNameById(int id) throws SQLException, ClassNotFoundException {
		String name = "";
		String sql = "SELECT uname FROM report where id = " + id;
		Connection connection = DBManager.connect();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			name = rs.getString(1);
		}
		DBManager.stopAll(rs, ps, connection);
		return name;
	}
}
