package bl;

import java.sql.SQLException;

import blservice.UserInfoBlService;
import data.UserDataImpl;
import dataservice.UserInfoDataService;
import po.UserInfoPO;
import vo.UserInfoVO;

public class UserInfoBlImpl implements UserInfoBlService{

	private UserInfoDataService userInfo;
	
	public UserInfoBlImpl() {
		this.userInfo = (UserInfoDataService) new UserDataImpl();
	}
	
	@Override
	public boolean update(UserInfoVO vo) throws ClassNotFoundException, SQLException {
		UserInfoPO po = new UserInfoPO(vo);
		return userInfo.update(po);
	}

	@Override
	public boolean add(String username) throws ClassNotFoundException, SQLException {
		return userInfo.add(username);
	}

	@Override
	public UserInfoVO get(String username) throws ClassNotFoundException, SQLException {
		UserInfoPO po = userInfo.get(username);
		UserInfoVO vo = new UserInfoVO(po);
		return vo;
	}

}
