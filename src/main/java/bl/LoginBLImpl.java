package bl;

import java.sql.SQLException;

import blservice.LoginBlService;
import blservice.UserInfoBlService;
import data.LoginDataImpl;
import dataservice.LoginDataService;

public class LoginBLImpl implements LoginBlService{

	private LoginDataService loginDataService;
	
	public LoginBLImpl() {
		this.loginDataService = new LoginDataImpl();
	}
	public int createAccount(String userName, String email, String password) throws ClassNotFoundException, SQLException {
		int i = loginDataService.createAccount(userName, email, password);
		if(i == 0) {
			UserInfoBlService userInfoBlService = new UserInfoBlImpl();
			userInfoBlService.add(userName);
		}
		return i;
	}

	public int verifyAccount(String userName, String password) throws ClassNotFoundException, SQLException {
		return loginDataService.verifyAccount(userName, password);
	}

	public int changePassword(String userName, String password) throws ClassNotFoundException, SQLException {
		return loginDataService.changePassword(userName, password);
	}

}
