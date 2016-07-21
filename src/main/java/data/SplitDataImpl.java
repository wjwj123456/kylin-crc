package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataservice.SplitDataService;
import po.ReportPO;

/**
 * 
 * @author MAC
 *
 */
public class SplitDataImpl implements SplitDataService {

	public ArrayList<ReportPO> getFaultsByTaskname(String taskname) throws ClassNotFoundException, SQLException {
		ArrayList<ReportPO> result = new ArrayList<ReportPO>();

		String sql = "SELECT * FROM report WHERE tname = '" + taskname + "' ORDER BY page, location";
		ResultSet rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			ReportPO po = new ReportPO(rSet.getString("tname"), rSet.getString("uname"), rSet.getString("filename"),
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"), rSet.getInt("state"),
					rSet.getInt("origin"));
			result.add(po);
		}
		DBManager.closeConnection();
		return result;
	}

	public ArrayList<ReportPO> getIncludedfaultsByFaultkey(ReportPO po) throws ClassNotFoundException, SQLException {
		ArrayList<ReportPO> result = new ArrayList<ReportPO>();
		int id = getID(po);
		Connection connection = DBManager.connect();
		Statement statement = connection.createStatement();
		String sql = "SELECT included_id FROM merge WHERE final_id = " + id;
		ResultSet rSet = statement.executeQuery(sql);
		while (rSet.next()) {
			int included_id = rSet.getInt("included_id");
			ReportPO included_po = getFault(included_id);
			result.add(included_po);
		}
		DBManager.stopAll(rSet, statement, connection);
		return result;
	}

	public boolean splitFaults(ArrayList<ReportPO> pos, ReportPO po) throws ClassNotFoundException, SQLException {
		int id = getID(po);
		Connection connection = DBManager.connect();
		Statement statement = connection.createStatement();
		System.out.println(pos);
		for (ReportPO reportPO : pos) {
			int id0 = getID(reportPO);
			String sql1 = "UPDATE report SET state = 0 WHERE id = " + id0;
			statement.executeUpdate(sql1);

			String sql2 = "DELETE FROM merge WHERE final_id = " + id + " AND included_id = " + id0;
			statement.executeUpdate(sql2);
		}

		ResultSet rSet = null;
		if (po.getOrigin() != 0) {
			String sql3 = "SELECT * FROM merge WHERE final_id = " + id;
			rSet = statement.executeQuery(sql3);
			if (!rSet.next()) {
				String sql4 = "DELETE FROM report WHERE id = " + id;
				statement.executeUpdate(sql4);
			}
		}

		String sql5 = "SELECT * FROM merge WHERE final_id = " + id;
		rSet = statement.executeQuery(sql5);
		if (!rSet.next()) {
			int origin = getOrigin(id);
			if (origin == 0) {
				String sql7 = "UPDATE report SET merge = 0 WHERE id = " + id;
				statement.executeUpdate(sql7);
			} else {
				String sql6 = "DELETE FROM report WHERE id = " + id;
				int i = statement.executeUpdate(sql6);
				if (i == 0)
					return false;
			}
		}
		DBManager.stopAll(rSet, statement, connection);
		return true;
	}

	/**
	 * get the fault id by ReportPO
	 * 
	 * @param po
	 * @return -1 means the fault is not recorded, -2 means the fault is not
	 *         unique in the , >=0 means the
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private int getID(ReportPO po) throws ClassNotFoundException, SQLException {
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

	/**
	 * get fault by id
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ReportPO getFault(int id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT * FROM report WHERE id = " + id;
		ResultSet rSet = DBManager.getResultSet(sql);

		rSet.next();
		ReportPO po = new ReportPO(rSet.getString("tname"), rSet.getString("uname"), rSet.getString("filename"),
				rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"), rSet.getInt("state"),
				rSet.getInt("origin"));

		DBManager.closeConnection();
		return po;
	}

	/**
	 * get fault state by id
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private int getState(int id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT state FROM report WHERE id = " + id;
		ResultSet rSet = DBManager.getResultSet(sql);

		rSet.next();
		int state = rSet.getInt("state");
		DBManager.closeConnection();
		return state;
	}

	/**
	 * set fault state
	 * 
	 * @param id
	 * @param newState
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void setState(int id, int newState) throws ClassNotFoundException, SQLException {

		String sql = "UPDATE report SET  state = ? WHERE id = ?";
		PreparedStatement pStatement = DBManager.getPreparedStatement(sql);

		pStatement.setInt(1, newState);
		pStatement.setInt(2, id);
		pStatement.executeUpdate();
		DBManager.closeConnection();
	}

	/**
	 * get fault origin by id
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private int getOrigin(int id) throws ClassNotFoundException, SQLException {

		String sql = "SELECT origin FROM report WHERE id = " + id;

		ResultSet rSet = DBManager.getResultSet(sql);

		rSet.next();
		int origin = rSet.getInt("origin");
		DBManager.closeConnection();
		return origin;
	}

	@Override
	public ArrayList<ReportPO> getCanSplitReports(String taskname) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT * FROM report WHERE id IN"
				+ " (SELECT included_id FROM merge WHERE final_id IN (SELECT r.id FROM report r WHERE r.tname = ?))";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, taskname);
		ResultSet rSet = pStatement.executeQuery();
		ArrayList<ReportPO> reportPOs = new ArrayList<>();
		while (rSet.next()) {
			ReportPO po = new ReportPO(taskname, rSet.getString("uname"), rSet.getString("filename"),
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"));
			reportPOs.add(po);
		}
		DBManager.stopAll(rSet, pStatement, connection);
		return reportPOs;
	}

	/**
	 * 
	 * @param pos
	 * @param po
	 * @return	0 means success, 1 means failures
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public int splitForUndoMerge(List<ReportPO> pos, ReportPO po) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		Statement statement = connection.createStatement();
		int id = getID(po);
		for(ReportPO po0: pos) {
			int id0 = getID(po0);
			String sql = "DELETE FROM merge WHERE final_id = " + id + " AND included_id = " + id0;
			int i = statement.executeUpdate(sql);
			if(i!=1) {
				DBManager.stopAll(null, statement, connection);
				return 1;
			}
		}
		DBManager.stopAll(null, statement, connection);
		return 0;
	}

}
