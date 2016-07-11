package dataservice;

import java.sql.SQLException;

import po.UserPO;

public interface UserDataService {

	public UserPO getUserPOByName(String userName) throws SQLException, ClassNotFoundException;
}
