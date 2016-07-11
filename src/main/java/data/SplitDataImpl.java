package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataservice.SplitDataService;
import po.ReportPO;

public class SplitDataImpl implements SplitDataService{

	public ArrayList<ReportPO> getFaultsByTaskname(String taskname) throws ClassNotFoundException, SQLException {
		ArrayList<ReportPO> result = new ArrayList<ReportPO>();
		Connection connection = DBManager.connect();
		String sql = "SELECT * FROM report WHERE tname = '"+taskname+"' ORDER BY page, location";
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		while(rSet.next()) {
			ReportPO po = new ReportPO(rSet.getString("tname"), rSet.getString("uname"), rSet.getString("filename"),
							rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"));
			result.add(po);
		}
		DBManager.stopAll(rSet, statement, connection);
		return result;
	}

	
	
	public ArrayList<ReportPO> getIncludedfaultsByFaultkey(ReportPO po) throws ClassNotFoundException, SQLException {
		ArrayList<ReportPO> result = new ArrayList<ReportPO>();
		Connection connection = DBManager.connect();
		int id = getID(po);
		String sql = "SELECT included_id FROM merge WHERE final_id = " + id;
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		while(rSet.next()) {
			int included_id = rSet.getInt("included_id");
			ReportPO included_po = getFault(included_id);
			result.add(included_po);
		}
		DBManager.stopAll(rSet, statement, connection);
		return result;
	}

	
	
	public boolean splitFaults(ArrayList<ReportPO> pos, ReportPO po) throws ClassNotFoundException, SQLException {
		int id = getID(po);
		ArrayList<ReportPO> included = getIncludedfaultsByFaultkey(po);
		if(pos.size() == included.size()) {
			Connection connection = DBManager.connect();
			String sql = "DELETE FROM merge WHERE final_id = " + id;
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			
		}
		return false;
	}
	
	/**
	 * get the fault id by ReportPO
	 * @param po
	 * @return	-1 means the fault is not recorded, -2 means the fault is not unique in the , >=0 means the 
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
		while(rSet.next()) {
			i = rSet.getInt("id");
			number ++;
		}
		DBManager.stopAll(rSet, pStatement, connection);
		if(number == 0) return -1;
		else {
			if(number > 1) return -2;
			else return i;
		}
	}
	
	/**
	 * get fault by id
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ReportPO getFault(int id) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT * FROM report WHERE id = " + id;
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		
		rSet.next();
		ReportPO po = new ReportPO(rSet.getString("tname"), rSet.getString("uname"), rSet.getString("filename"),
				rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"));
		
		DBManager.stopAll(rSet, statement, connection);
		return po;
	}
	
	/**
	 * get fault state by id
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private int getState(int id) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT state FROM report WHERE id = " + id;
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		
		rSet.next();
		int state = rSet.getInt("state");
		DBManager.stopAll(rSet, statement, connection);
		return state;
	}
	
	/**
	 * set fault state
	 * @param id
	 * @param newState
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void setState(int id, int newState) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "UPDATE report SET  state = ? WHERE id = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, newState);
		pStatement.setInt(2, id);
		pStatement.executeUpdate();
		DBManager.stopAll(null, pStatement, connection);
	}
	
	/**
	 * get fault origin by id
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private int getOrigin(int id) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT origin FROM report WHERE id = " + id;
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		
		rSet.next();
		int origin = rSet.getInt("origin");
		DBManager.stopAll(rSet, statement, connection);
		return origin;
	}

}
