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
		String sql = "SELECT friendUserName FROM friend where userName = '" + userName + "'";
		Connection connection = DBManager.connect();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result.add(userInfoDataImpl.get(rs.getString(1)));
		}
		DBManager.stopAll(rs, ps, connection);
		return result;
	}

	@Override
	public int addFriend(String userName, String friendName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int flag = -1;
		String sql = "INSERT INTO friend (userName,friendUserName) VALUES(?,?)";
		PreparedStatement pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setString(1, userName);
		pStatement.setString(2, friendName);
		try {
			int j = pStatement.executeUpdate();
			if (j == 1) {
				flag = 0;
			} else {
				flag = 2;
			}
		} catch (Exception SQLIntegrityConstraintViolationException) {
			flag = 1;
		}
		DBManager.closeConnection();
		return flag;
	}

	@Override
	public List<UserInfoPO> getFriendsByKeywords(String keyword) throws ClassNotFoundException, SQLException {
		List<UserInfoPO> result = new ArrayList<>();
		String sql = "SELECT friendUserName FROM friend where userName like '%" + keyword + "%'";
		Connection connection = DBManager.connect();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result.add(userInfoDataImpl.get(rs.getString(1)));
		}
		DBManager.stopAll(rs, ps, connection);
		return result;
	}

	@Override
	public int deleteFriend(String userName, String friendName) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "DELETE FROM friend WHERE userName = ? AND friendUserName = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, userName);
		pStatement.setString(2, friendName);
		int i = pStatement.executeUpdate();
		if (i != 1) return 1;
		pStatement.setString(1, friendName);
		pStatement.setString(2, userName);
		i = pStatement.executeUpdate();
		if (i != 1) return 2;
		return 0;
	}
}
