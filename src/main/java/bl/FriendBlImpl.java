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
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月21日
 * @see
 */
public class FriendBlImpl implements FriendBlService {
	FriendDataService friendDataService = new FriendDataImpl();

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月21日
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

}
