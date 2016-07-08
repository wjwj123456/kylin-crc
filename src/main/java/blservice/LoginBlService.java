package blservice;


public interface LoginBlService {
	/**
	 * 创建账户
	 * @param userName
	 * @param email
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 */
	public boolean createAccount(String userName,String email,String password);
	/**
	 * 验证账户
	 * @param userName
	 * @param password
	 * @return  boolean        
	 * @author      lpt
	 */
	public boolean verifyAccount(String userName,String password);
	/**
	 * 更改账户密码
	 * @param userName
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 */
	public boolean changePassword(String userName,String password);
}
