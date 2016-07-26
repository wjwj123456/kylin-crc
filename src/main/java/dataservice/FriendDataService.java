/**
* @author       lpt14
* @version      V1.0
*/
package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.UserInfoPO;

/**
 * TODO: 锛堢被鎻忚堪锛�
 *
 * @author lpt14
 * @since 2016骞�7鏈�19鏃�
 * @see
 */
public interface FriendDataService {
	public List<UserInfoPO> getFriends(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * 
	 * @param userName
	 * @param friendName
	 * @return0 o:success 1:conflict 2:fails
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int addFriend(String userName, String friendName) throws SQLException, ClassNotFoundException;

	public int deleteFriend(String userName, String friendName) throws ClassNotFoundException, SQLException;
	
	public List<UserInfoPO> getFriendsByKeywords(String keyword) throws ClassNotFoundException, SQLException;
}
