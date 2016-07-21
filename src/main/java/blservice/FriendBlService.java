/**
* @author       lpt14
* @version      V1.0
*/
package blservice;

import java.util.List;

import vo.UserInfoVO;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月21日
 * @see
 */
public interface FriendBlService {
	public List<UserInfoVO> getFriends(String userName);
}
