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
	
	/**
	 * return all faults that were included in the fault chosen
	 * @param po
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
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

	
	/**
	 * return all faults that were included in the fault chosen
	 * @param finalParent
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean splitFaults(ArrayList<ReportPO> splitedPOs, ReportPO finalParent) throws ClassNotFoundException, SQLException {
		int finalParentID = getID(finalParent);
		Connection connection = DBManager.connect();
		Statement statement = connection.createStatement();
		
		for (ReportPO reportPO : splitedPOs) {
			int splitID0 = getID(reportPO);
			int parentID0 = getParentID(splitID0, statement);
			ArrayList<ReportPO> childPOs = getIncludedfaultsByFaultkey(reportPO);
			
			for(ReportPO childPO: childPOs) {
				int childID = getID(childPO);				
				changeParent(splitID0, parentID0, childID, statement);
				deleteFromMerge(splitID0, childID, statement);
			}
			
			deleteFromMerge(parentID0, splitID0, statement);
			setMerge(splitID0, 0, statement);
			setState(splitID0, 0, statement);
		}

		int mergeNumber = mergeNumber(finalParentID, statement);
		if(mergeNumber == 0) {
			setMerge(finalParentID, 0, statement);
			int origin = getOrigin(finalParentID);
			if(origin != 0) setState(finalParentID, 1, statement);
		}
		
		DBManager.stopAll(null, statement, connection);
		return true;
	}
	
	/**
	 * 
	 * @param id
	 * @param statement
	 * @return	-1 means exception, 0 means include nothing, 
	 * 			else means how many records included except itself
	 */
	private int mergeNumber(int id, Statement statement) {
		int i = -1;
		String sql = "SELECT * FROM merge WHERE final_id = "  + id;
		try {
			ResultSet rSet = statement.executeQuery(sql);
			i = 0;
			while(rSet.next()) i++;
		} catch (SQLException e) {
			
		}
		return i;
	}
	
	private int setState(int id, int newstate, Statement statement) {
		String sql = "UPDATE report SET state = " + newstate + " WHERE id = " + id;
		int i = -1;
		try {
			i = statement.executeUpdate(sql);
		} catch (SQLException e) {
			
		}
		return i;
	}
	
	private int setMerge(int id, int newmerge, Statement statement) {
		String sql = "UPDATE report SET merge = " + newmerge + " WHERE id = " + id;
		int i = -1;
		try {
			i = statement.executeUpdate(sql);
		} catch (SQLException e) {
	
		}
		return i;
	}
	
	private int deleteFromMerge(int final_id, int included_id, Statement statement) {
		int i = -1;
		String sql = "DELETE FROM merge WHERE final_id = " + final_id 
				+ " AND included_id = " + included_id;
		try {
			i = statement.executeUpdate(sql);
		} catch (SQLException e) {
			
		}
		return i;
		
	}
	
	/**
	 * 
	 * @param fromID
	 * @param toID
	 * @param childID
	 * @param statement
	 * @return	-1 means invalid DB operation, else means how many records has been changed
	 */
	private int changeParent(int fromID, int toID, int childID, Statement statement) {
		String sql = "UPDATE merge SET final_id = " + toID 
				+ " WHERE final_id = " + fromID + " AND included_id = " + childID;
		int i = -1;
		try {
			i = statement.executeUpdate(sql);
		} catch (SQLException e) {
			
		}
		return i;
	}
	
	/**
	 * 
	 * @param childID
	 * @param statement
	 * @return	-5 means invalid DB query, -4 means not only one record in DB
	 */
	private int getParentID(int childID, Statement statement) {
		int parentID = -5;
		String sql = "SELECT final_id FROM merge WHERE included_id = " + childID;
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			int number = 0;
			while(resultSet.next()) {
				number++;
				parentID = resultSet.getInt("final_id");
			}
			if(number != 1) parentID = -4;
		} catch (SQLException e) {
			
		}
		return parentID;
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

//	/**
//	 * set fault state
//	 * 
//	 * @param id
//	 * @param newState
//	 * @throws ClassNotFoundException
//	 * @throws SQLException
//	 */
//	private void setState(int id, int newState) throws ClassNotFoundException, SQLException {
//
//		String sql = "UPDATE report SET  state = ? WHERE id = ?";
//		PreparedStatement pStatement = DBManager.getPreparedStatement(sql);
//
//		pStatement.setInt(1, newState);
//		pStatement.setInt(2, id);
//		pStatement.executeUpdate();
//		DBManager.closeConnection();
//	}

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
			if (id0 == id) {
				continue;
			}
			String sql = "DELETE FROM merge WHERE final_id = " + id + " AND included_id = " + id0;
			int i = statement.executeUpdate(sql);
			if(i!=1) {
				DBManager.stopAll(null, statement, connection);
				return 1;
			}
			sql = "UPDATE report SET state = 0 WHERE id = " + id0;
			i = statement.executeUpdate(sql);
			if(i!=1) {
				DBManager.stopAll(null, statement, connection);
				return 2;
			}
			i = mergeNumber(id0, statement);
			if(i==0) {
				sql = "UPDATE report SET merge = 0 WHERE id = " + id0;
				i = statement.executeUpdate(sql);
				if(i != 1) {
					DBManager.stopAll(null, statement, connection);
					return 3;
				}
			}
		}

		int i = mergeNumber(id, statement);
		System.out.println(i);
		if (i == 0) {
			System.out.println("lll");
			setMerge(id, 0, statement);
		}
		DBManager.stopAll(null, statement, connection);
		return 0;
	}

}
