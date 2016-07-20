package blservice;

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
	 * @param username
	 * @return
	 */
	public UserInfoVO get(String username);
}
