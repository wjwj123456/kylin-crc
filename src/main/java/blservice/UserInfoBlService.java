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
	
	
	/**
	 * 
	 * @param userName
	 * @param path
	 * @return
	 */
	public int setPicture(String userName, String path);
	
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public String getPicture(String userName);
}
