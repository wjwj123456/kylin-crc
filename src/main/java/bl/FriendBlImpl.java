/**
* @author       lpt14
* @version      V1.0
*/
package bl;

import blservice.FriendBlService;
import data.FriendDataImpl;
import dataservice.FriendDataService;
import po.UserInfoPO;
import vo.UserInfoVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 閿涘牏琚幓蹇氬牚閿涳拷
 *
 * @author lpt14
 * @since 2016楠烇拷7閺堬拷21閺冿拷
 */
public class FriendBlImpl implements FriendBlService {
	FriendDataService friendDataService = new FriendDataImpl();

	/**
	 * TODO:閿涘牊鏌熷▔鏇熷伎鏉╁府绱�
	 *
	 * @author lpt14
	 * @since 2016楠烇拷7閺堬拷21閺冿拷
	 * @param userName
	 * @return
	 * @see blservice.FriendBlService#getFriends(java.lang.String)
	 *
	 */
	@Override
	public List<UserInfoVO> getFriends(String userName) {
		List<UserInfoPO> list = new ArrayList<>();
		List<UserInfoVO> list2 = new ArrayList<>();
		try {
			list = friendDataService.getFriends(userName);
		} catch (ClassNotFoundException | SQLException e) {
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int delete(String userName, String friendUserName) {
		int i = -1;
		try {
			i = friendDataService.deleteFriend(userName, friendUserName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<UserInfoVO> getFriendByKeyword(String userName, String keyword) {
		List<UserInfoVO> result = new ArrayList<>();
		List<UserInfoPO> pos = new ArrayList<>();
		try {
			pos = friendDataService.getFriendsByKeywords(userName, keyword);
			for(UserInfoPO po: pos) {
				UserInfoVO vo = new UserInfoVO(po);
				result.add(vo);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<UserInfoVO> getStrangerByKeyword(String userName, String keyword) {
		List<UserInfoVO> result = new ArrayList<>();
		List<UserInfoPO> pos = new ArrayList<>();
		try {
			pos = friendDataService.getStrangerByKeywords(userName, keyword);
			for(UserInfoPO po: pos) {
				UserInfoVO vo = new UserInfoVO(po);
				result.add(vo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isFriend(String userName, String friendUserName) {
		boolean result = false;
		try {
			result = friendDataService.isFriend(userName, friendUserName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
