/**
* @author       lpt14
* @version      V1.0
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
import vo.State;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月11日
 * @see
 */
public class MergeDataImpl implements MergeDataService {

	/**
	 * if it can merge return true
	 *
	 * @author lpt14
	 * @since 2016年7月11日
	 * @param userName
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.MergeDataService#canMerge(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	public boolean canMerge(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
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

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月11日
	 * @param userNme
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.MergeDataService#mergeReport(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	public List<ReportPO> mergeReport(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<ReportPO> list = new ArrayList<ReportPO>();
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "SELECT * FROM report WHERE tname = '" + taskName + "'";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		while (rSet.next()) {
			ReportPO po = new ReportPO(taskName, rSet.getString("uname"), rSet.getString("filename"),
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"), rSet.getInt("state"),
					rSet.getInt("origin"));
			list.add(po);
		}
		DBManager.stopAll(rSet, pStatement, connection);

		return list;
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月11日
	 * @param reportList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.MergeDataService#saveMergeReport(java.util.List)
	 *
	 */
	public int saveMergeReport(List<ReportPO> reportList, String taskName) throws SQLException, ClassNotFoundException {
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		ReportPO finalPO = reportList.get(0);
		reportList.remove(0);
		for (ReportPO po : reportList) {
			String sql = "UPDATE report SET state = ? WHERE tname = ? and uname= ? and filename=? and page=? and location=? and description=?  ";
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, 1);
			pStatement.setString(2, taskName);
			pStatement.setString(3, po.getUserName());
			pStatement.setString(4, po.getFileName());
			pStatement.setInt(5, po.getPage());
			pStatement.setInt(6, po.getLocation());
			pStatement.setString(7, po.getDescription());
			pStatement.executeUpdate();
		}

		String sql2 = "UPDATE report SET state = ? WHERE tname = ? and uname= ? and filename=? and page=? and location=? and description=?  ";
		pStatement = connection.prepareStatement(sql2);
		pStatement.setInt(1, 0);
		pStatement.setString(2, taskName);
		pStatement.setString(3, finalPO.getUserName());
		pStatement.setString(4, finalPO.getFileName());
		pStatement.setInt(5, finalPO.getPage());
		pStatement.setInt(6, finalPO.getLocation());
		pStatement.setString(7, finalPO.getDescription());
		pStatement.executeUpdate();

		for (ReportPO po : reportList) {
			String sql1 = "INSERT INTO merge (final_id, included_id) VALUES (?,  ?)";
			pStatement = connection.prepareStatement(sql1);
			pStatement.setInt(1, getID(finalPO));
			pStatement.setInt(2, getID(po));
			pStatement.executeUpdate();
		}
		updateMerge();
		DBManager.stopAll(null, pStatement, connection);
		return flag;
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

	/**
	 * get fault by id
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private ReportPO getFault(int id) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT * FROM report WHERE id = " + id;
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery(sql);

		rSet.next();
		ReportPO po = new ReportPO(rSet.getString("tname"), rSet.getString("uname"), rSet.getString("filename"),
				rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"), rSet.getInt("state"),
				rSet.getInt("origin"));

		DBManager.stopAll(rSet, statement, connection);
		return po;
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月11日
	 * @param reportList
	 * @param taskName
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.MergeDataService#saveAddedMergeReport(java.util.List,
	 *      java.lang.String)
	 *
	 */
	public int saveAddedMergeReport(List<ReportPO> reportList, String taskName)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		ReportPO finalPO = reportList.get(0);
		reportList.remove(0);

		String sql2 = "INSERT INTO report (tname,uname,filename,page,location,description,state,origin) VALUES (?,?,?,?,?,?,?,?)";
		pStatement = connection.prepareStatement(sql2);
		pStatement.setString(1, finalPO.getTaskName());
		pStatement.setString(2, finalPO.getUserName());
		pStatement.setString(3, finalPO.getFileName());
		pStatement.setInt(4, finalPO.getPage());
		pStatement.setInt(5, finalPO.getLocation());
		pStatement.setString(6, finalPO.getDescription());
		pStatement.setInt(7, 0);
		pStatement.setInt(8, 1);
		pStatement.executeUpdate();

		for (ReportPO po : reportList) {
			String sql = "UPDATE report SET state = ? WHERE tname = ? and uname= ? and filename=? and page=? and location=? and description=?  ";
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, 1);
			pStatement.setString(2, taskName);
			pStatement.setString(3, po.getUserName());
			pStatement.setString(4, po.getFileName());
			pStatement.setInt(5, po.getPage());
			pStatement.setInt(6, po.getLocation());
			pStatement.setString(7, po.getDescription());
			pStatement.executeUpdate();
		}

		for (ReportPO po : reportList) {
			String sql1 = "INSERT INTO merge (final_id, included_id) VALUES (?,  ?)";
			pStatement = connection.prepareStatement(sql1);
			pStatement.setInt(1, getID(finalPO));
			pStatement.setInt(2, getID(po));
			pStatement.executeUpdate();
		}
		updateMerge();
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月14日
	 * @param userName
	 * @param taskName
	 * @param fault
	 * @param assessfalut
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.MergeDataService#saveHistory(java.lang.String,
	 *      java.lang.String, int, int)
	 *
	 */
	@Override
	public int saveHistory(String userName, String taskName, int fault, int assessfalut)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO history (tname, uname, fault,assessfault) VALUES (?, ?, ?,?)";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, taskName);
		pStatement.setString(2, userName);
		pStatement.setInt(3, fault);
		pStatement.setInt(4, assessfalut);
		pStatement.executeUpdate();

		String sql1 = "UPDATE review SET state = ? WHERE tname = ? and uname= ? ";
		PreparedStatement pStatement1 = connection.prepareStatement(sql1);
		pStatement1.setString(1, String.valueOf(State.merged));
		pStatement1.setString(2, taskName);
		pStatement1.setString(3, userName);
		pStatement.executeQuery();
		DBManager.closeConnection();

		return flag;

	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月14日
	 * @param po
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.MergeDataService#deleteMergeRecord(po.ReportPO)
	 *
	 */
	@Override
	public int deleteMergeRecord(ReportPO po) throws SQLException, ClassNotFoundException {
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "UPDATE report SET state = ? WHERE tname = ? and uname= ? and filename=? and page=? and location=? and description=?  ";
		pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, 1);
		pStatement.setString(2, po.getTaskName());
		pStatement.setString(3, po.getUserName());
		pStatement.setString(4, po.getFileName());
		pStatement.setInt(5, po.getPage());
		pStatement.setInt(6, po.getLocation());
		pStatement.setString(7, po.getDescription());
		pStatement.executeUpdate();

		String sql1 = "DELETE * FROM merge  WHERE final_id = '" + getID(po) + "'";
		pStatement = connection.prepareStatement(sql1);
		pStatement.executeUpdate();
		updateMerge();
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	private void updateMerge() throws SQLException, ClassNotFoundException {
		ResultSet rSet = null;
		String sql = "SELECT * FROM merge";
		rSet = DBManager.getResultSet(sql);
		List<Integer> ids = new ArrayList<>();
		List<Integer> finalIds = new ArrayList<>();
		while (rSet.next()) {
			finalIds.add(rSet.getInt("1"));
			ids.add(rSet.getInt("2"));
		}
		DBManager.closeConnection();

		for (int i = 0; i < ids.size(); i++) {
			for (int j = 0; j < finalIds.size(); j++) {
				if (ids.get(i) == finalIds.get(j)) {
					finalIds.set(j, finalIds.get(i));
				}
			}

		}

		Connection connection = DBManager.connect();

		PreparedStatement pStatement = null;

		String sql2 = "UPDATE merge set	final_id=? where included_id=? '";
		for (int i = 0; i < ids.size(); i++) {
			pStatement = connection.prepareStatement(sql2);
			pStatement.setInt(1, finalIds.get(i));
			pStatement.setInt(2, ids.get(i));
			pStatement.executeUpdate();
		}

		DBManager.closeConnection();

		return;
	}

}
