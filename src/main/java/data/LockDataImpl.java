/**
* @author       lpt14
* @version      V1.0
*/
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataservice.LockDataService;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月26日
 * @see
 */
public class LockDataImpl implements LockDataService {

	/**
	 * @param @param
	 *            i
	 * @param @return
	 * @return boolean
	 * @throws @author
	 *             lpt14
	 */

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月26日
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.LockDataService#getCurrentUser(java.lang.String)
	 *
	 */
	@Override
	public String getCurrentUser(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "SELECT uname FROM lock where tname = '" + taskName + "'";
		Connection connection = DBManager.connect();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		String i = null;
		if (rs.next()) {
			i = rs.getString(1);
		}
		DBManager.stopAll(rs, ps, connection);
		return i;
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月26日
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @see dataservice.LockDataService#setCurrentUser(java.lang.String)
	 *
	 */
	@Override
	public int setCurrentUser(String taskName, String userName) throws SQLException {
		// TODO Auto-generated method stub
		String sql1 = "UPDATE lock SET uname = ? WHERE tname = ?";
		PreparedStatement pStatement1 = DBManager.getPreparedStatement(sql1);

		pStatement1.setString(1, userName);
		pStatement1.setString(2, taskName);
		int i = pStatement1.executeUpdate();
		DBManager.closeConnection();
		return i;
	}

}
