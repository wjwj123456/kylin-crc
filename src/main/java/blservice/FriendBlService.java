/**
* @author       lpt14
* @version      V1.0
*/
package blservice;

import java.util.List;

import vo.UserInfoVO;

/**
 * TODO: 锛堢被鎻忚堪锛�
 *
 * @author lpt14
 * @since 2016骞�7鏈�21鏃�
 * @see
 */
public interface FriendBlService {
	public List<UserInfoVO> getFriends(String userName);
	
	public int delete(String userName, String friendUserName);
	
	public List<UserInfoVO> getFriendByKeyword(String userName, String keyword);
	
	public List<UserInfoVO> getStrangerByKeyword(String userName, String keyword);
}
