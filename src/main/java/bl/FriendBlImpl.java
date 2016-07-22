/**
* @author       lpt14
* @version      V1.0
*/
package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.FriendBlService;
import data.FriendDataImpl;
import dataservice.FriendDataService;
import po.UserInfoPO;
import vo.UserInfoVO;

/**
 * TODO: 锛堢被鎻忚堪锛�
 *
 * @author lpt14
 * @since 2016骞�7鏈�21鏃�
 * @see
 */
public class FriendBlImpl implements FriendBlService {
	FriendDataService friendDataService = new FriendDataImpl();

	/**
	 * TODO:锛堟柟娉曟弿杩帮級
	 *
	 * @author lpt14
	 * @since 2016骞�7鏈�21鏃�
	 * @param userName
	 * @return
	 * @see blservice.FriendBlService#getFriends(java.lang.String)
	 *
	 */
	@Override
	public List<UserInfoVO> getFriends(String userName) {
		// TODO Auto-generated method stub
		List<UserInfoPO> list = new ArrayList<>();
		List<UserInfoVO> list2 = new ArrayList<>();
		try {
			list = friendDataService.getFriends(userName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (UserInfoPO po : list) {
			list2.add(new UserInfoVO(po));
		}
		return list2;
	}

	/**
	 * 
	 * @param userName
	 * @param friendName
	 * @return0 o:success 1:conflict 2:fail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public int addFriend(String userName, String friendName) {
		int flag = -1;
		try {
			flag = friendDataService.addFriend(userName, friendName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
