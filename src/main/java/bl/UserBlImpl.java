package bl;

import java.sql.SQLException;

import blservice.UserBlService;
import data.UserDataImpl;
import dataservice.UserDataService;
import po.UserPO;
import vo.UserVO;

public class UserBlImpl implements UserBlService {

	private UserDataService userDataService = new UserDataImpl();

	public UserVO getUserVOByName(String userName) {
		String name = "";
		String mail = "";
		String password = "";
		try {
			UserPO po = userDataService.getUserPOByName(userName);
			name = po.getName();
			mail = po.getEmail();
			password = po.getPassword();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return new UserVO(name, mail, password);
	}

}
