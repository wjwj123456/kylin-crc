package bl;

import java.sql.SQLException;

import blservice.UserInfoBlService;
import data.UserInfoDataImpl;
import dataservice.UserInfoDataService;
import po.UserInfoPO;
import vo.UserInfoVO;

public class UserInfoBlImpl implements UserInfoBlService {

	private UserInfoDataService userInfo;

	public UserInfoBlImpl() {
		this.userInfo = new UserInfoDataImpl();
	}

	@Override
	public boolean update(UserInfoVO vo) {
		UserInfoPO po = new UserInfoPO(vo);
		try {
			return userInfo.update(po);
		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {

		}
		return false;
	}

	@Override
	public boolean add(String username) {
		try {
			return userInfo.add(username);
		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {

		}
		return false;
	}

	@Override
	public UserInfoVO get(String username) {
		UserInfoPO po = null;
		try {
			po = userInfo.get(username);
		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {

		}
		UserInfoVO vo = new UserInfoVO(po);
		return vo;
	}

}
