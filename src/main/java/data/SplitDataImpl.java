package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataservice.SplitDataService;
import po.ReportPO;

public class SplitDataImpl implements SplitDataService {
	private ResultSet rSet = null;
	private PreparedStatement pStatement = null;

	public ArrayList<ReportPO> getFaultsByTaskname(String taskname) throws ClassNotFoundException, SQLException {
		ArrayList<ReportPO> result = new ArrayList<ReportPO>();

		String sql = "SELECT * FROM report WHERE tname = '" + taskname + "' ORDER BY page, location";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			ReportPO po = new ReportPO(rSet.getString("tname"), rSet.getString("uname"), rSet.getString("filename"),
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"));
			result.add(po);
		}
		DBManager.closeConnection();
		return result;
	}

	public ArrayList<ReportPO> getIncludedfaultsByFaultkey(ReportPO po) throws ClassNotFoundException, SQLException {
		ArrayList<ReportPO> result = new ArrayList<ReportPO>();
		int id = getID(po);
		String sql = "SELECT included_id FROM merge WHERE final_id = " + id;
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			int included_id = rSet.getInt("included_id");
			ReportPO included_po = getFault(included_id);
			result.add(included_po);
		}
		DBManager.closeConnection();
		return result;
	}

	public boolean splitFaults(ArrayList<ReportPO> pos, ReportPO po) throws ClassNotFoundException, SQLException {
		int id = getID(po);
		ArrayList<ReportPO> included = getIncludedfaultsByFaultkey(po);
		if (pos.size() == included.size()) {

			String sql = "DELETE FROM merge WHERE final_id = " + id;
			pStatement = DBManager.getPreparedStatement(sql);
			pStatement.executeUpdate(sql);

		}
		DBManager.closeConnection();
		return false;
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

		String sql = "SELECT id FROM report WHERE uname = ? AND tname = ? AND filename = ? AND page = ? AND location = ? AND description = ?";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, po.getUserName());
		pStatement.setString(2, po.getTaskName());
		pStatement.setString(3, po.getFileName());
		pStatement.setInt(4, po.getPage());
		pStatement.setInt(5, po.getLocation());
		pStatement.setString(6, po.getDescription());
		DBManager.closeConnection();
		rSet = DBManager.getResultSet(sql);
		int i = -1;
		int number = 0;
		while (rSet.next()) {
			i = rSet.getInt("id");
			number++;
		}
		DBManager.closeConnection();
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
		rSet = DBManager.getResultSet(sql);

		rSet.next();
		ReportPO po = new ReportPO(rSet.getString("tname"), rSet.getString("uname"), rSet.getString("filename"),
				rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"));

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
		rSet = DBManager.getResultSet(sql);

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
		pStatement = DBManager.getPreparedStatement(sql);

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

		rSet = DBManager.getResultSet(sql);

		rSet.next();
		int origin = rSet.getInt("origin");
		DBManager.closeConnection();
		return origin;
	}

}
