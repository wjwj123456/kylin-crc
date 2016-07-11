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
		// TODO Auto-generated method stub
		String name = "";
		String mail = "";
		String password = "";
		try {
			UserPO po = userDataService.getUserPOByName(userName);
			name = po.getName();
			mail = po.getEmail();
			password = po.getPassword();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new UserVO(name, mail, password);
	}

}
