package bl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import blservice.LoginBlService;
import data.DBManager;
import data.LoginDataImpl;
import dataservice.LoginDataService;

public class LoginBLImpl implements LoginBlService{

	private LoginDataService loginDataService;
	
	public LoginBLImpl() {
		this.loginDataService = new LoginDataImpl();
	}
	public boolean createAccount(String userName, String email, String password) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT * FROM user WHERE uname = '" + userName + "'";
		ResultSet rs = DBManager.getQueryResultSet(sql, connection);
		System.out.println(rs.next());
		if(rs.next()){
			System.out.println("’Àªß“—¥Ê‘⁄");
			return false;
		}
		else
			return loginDataService.createAccount(userName, email, password);
	}

	public int verifyAccount(String userName, String password) throws ClassNotFoundException, SQLException {
		return loginDataService.verifyAccount(userName, password);
	}

	public boolean changePassword(String userName, String password) throws ClassNotFoundException, SQLException {
		return loginDataService.changePassword(userName, password);
	}

}
