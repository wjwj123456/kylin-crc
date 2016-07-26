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
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月26日
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.LockDataService#canWrite()
	 *
	 */
	@Override
	public boolean canWrite(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String sql = "SELECT lock FROM lock where tname = '" + taskName + "'";
		Connection connection = DBManager.connect();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int i = 0;
		if (rs.next()) {
			i = rs.getInt(1);
		}

		DBManager.stopAll(rs, ps, connection);
		return getBool(i);
	}

	/**
	 * @param @param
	 *            i
	 * @param @return
	 * @return boolean
	 * @throws @author
	 *             lpt14
	 */
	private boolean getBool(int i) {
		// TODO Auto-generated method stub
		if (i == 1)
			return true;
		return false;
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月26日
	 * @param flag
	 * @return
	 * @throws SQLException
	 * @see dataservice.LockDataService#setLock(boolean)
	 *
	 */
	@Override
	public int setLock(String taskName, boolean flag) throws SQLException {
		// TODO Auto-generated method stub
		String sql1 = "UPDATE lock SET lock = ? WHERE tname = ?";
		PreparedStatement pStatement1 = DBManager.getPreparedStatement(sql1);

		pStatement1.setInt(1, getLock(flag));
		pStatement1.setString(2, taskName);
		int i = pStatement1.executeUpdate();
		DBManager.closeConnection();
		return i;
	}

	public int getLock(boolean flag) {
		if (flag)
			return 1;
		return 0;
	}

}
