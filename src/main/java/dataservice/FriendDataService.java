/**
* @author       lpt14
* @version      V1.0
*/
package dataservice;

import java.util.List;

import po.UserInfoPO;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月19日
 * @see
 */
public interface FriendDataService {
	public List<UserInfoPO> getFriends(String userName);
}
