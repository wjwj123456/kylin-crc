/**
* @author       lpt14
* @version      V1.0
*/
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	 * TODO:（方法描述）
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
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("describe"));
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
	 * @see dataservice.MergeDataService#saveMergeReport(java.util.List)
	 *
	 */
	public void saveMergeReport(List<ReportPO> reportList) {
		// TODO Auto-generated method stub

	}

}
