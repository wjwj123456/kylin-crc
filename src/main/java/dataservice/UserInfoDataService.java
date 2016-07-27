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
	
	
	/**
	 * 
	 * @param userName
	 * @param path
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int setPicture(String userName, String path) throws ClassNotFoundException, SQLException;
	
	
	/**
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public String getPicture(String userName) throws ClassNotFoundException, SQLException;
}
