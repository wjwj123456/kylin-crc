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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		UserInfoVO vo = new UserInfoVO(po);
		return vo;
	}

	@Override
	public int setPicture(String userName, String path) {
		int result = -1;
		try {
			result = userInfo.setPicture(userName, path);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getPicture(String userName) {
		String path = null;
		try {
			path = userInfo.getPicture(userName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
