package bl;

import java.sql.SQLException;

import blservice.LoginBlService;
import data.LoginDataImpl;

public class LoginBLImpl implements LoginBlService{

	private LoginDataImpl loginDataImpl;
	
	public LoginBLImpl() {
		this.loginDataImpl = new LoginDataImpl();
	}
	public boolean createAccount(String userName, String email, String password) throws ClassNotFoundException, SQLException {
		return loginDataImpl.createAccount(userName, email, password);
	}

	public int verifyAccount(String userName, String password) throws ClassNotFoundException, SQLException {
		return loginDataImpl.verifyAccount(userName, password);
	}

	public boolean changePassword(String userName, String password) throws ClassNotFoundException, SQLException {
		return loginDataImpl.changePassword(userName, password);
	}

}
