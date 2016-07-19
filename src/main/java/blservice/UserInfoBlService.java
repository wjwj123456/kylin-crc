package blservice;

import java.sql.SQLException;

import vo.UserInfoVO;

public interface UserInfoBlService {

	/**
	 * 
	 * @param vo
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean update(UserInfoVO vo) throws ClassNotFoundException, SQLException;
	
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
	 * @param vo
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UserInfoVO get(String vo) throws ClassNotFoundException, SQLException;
}
