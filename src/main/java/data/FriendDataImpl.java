/**
* @author       lpt14
* @version      V1.0
*/
package data;

import java.util.ArrayList;
import java.util.List;

import dataservice.FriendDataService;
import po.UserInfoPO;

/**
 * @author lpt14
 * @see
 */
public class FriendDataImpl implements FriendDataService {

	@Override
	public List<UserInfoPO> getFriends(String userName) {
		List<UserInfoPO> result = new ArrayList<>();

		return result;
	}
}
