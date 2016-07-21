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

import dataservice.FriendDataService;
import po.UserInfoPO;

/**
 * @author lpt14
 * @see
 */
public class FriendDataImpl implements FriendDataService {
	UserInfoDataImpl userInfoDataImpl = new UserInfoDataImpl();

	@Override
	public List<UserInfoPO> getFriends(String userName) throws ClassNotFoundException, SQLException {
		List<UserInfoPO> result = new ArrayList<>();
		String sql = "SELECT friendUserName FROM friend where userName = ' " + userName + "'";
		Connection connection = DBManager.connect();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result.add(userInfoDataImpl.get(rs.getString(1)));
		}
		DBManager.stopAll(rs, ps, connection);
		return result;
	}
}
