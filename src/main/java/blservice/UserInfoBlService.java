package blservice;

import java.sql.SQLException;

import vo.UserInfoVO;

public interface UserInfoBlService {

	/**
	 * 
	 * @param vo
	 * @return
	 */
	public boolean update(UserInfoVO vo);
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public boolean add(String username);
	
	/**
	 * 
	 * @param vo
	 * @return
	 */
	public UserInfoVO get(String vo);
}
