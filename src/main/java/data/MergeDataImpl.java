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
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("describe"), rSet.getInt("state"),
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
	public void saveMergeReport(List<ReportPO> reportList, String taskName)
			throws SQLException, ClassNotFoundException {

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

		for (ReportPO po : reportList) {
			String sql1 = "INSERT INTO merge (final_id, included_id) VALUES (?,  ?)";
			pStatement = connection.prepareStatement(sql1);
			pStatement.setInt(1, getID(finalPO));
			pStatement.setInt(2, getID(po));
			pStatement.executeUpdate();
		}
		DBManager.stopAll(null, pStatement, connection);
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
	public void saveAddedMergeReport(List<ReportPO> reportList, String taskName)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
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
		DBManager.stopAll(null, pStatement, connection);
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
	public void saveHistory(String userName, String taskName, int fault, int assessfalut)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO history (tname, uname, fault,assessfault) VALUES (?, ?, ?,?)";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, taskName);
		pStatement.setString(2, userName);
		pStatement.setInt(3, fault);
		pStatement.setInt(4, assessfalut);
		pStatement.executeUpdate();
		DBManager.closeConnection();
		return;
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
	public void deleteMergeRecord(ReportPO po) throws SQLException, ClassNotFoundException {
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

		DBManager.stopAll(null, pStatement, connection);

	}

}
