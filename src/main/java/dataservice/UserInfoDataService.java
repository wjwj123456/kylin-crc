package dataservice;

import java.sql.SQLException;

import po.UserInfoPO;

public interface UserInfoDataService {

	/**
	 * 
	 * @param po
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean update(UserInfoPO po) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param username
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean add(String username) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param username
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UserInfoPO get(String username) throws ClassNotFoundException, SQLException;
}
