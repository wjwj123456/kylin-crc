package blservice;


public interface LoginBlService {
	/**
	 * 创建账户
	 * @param accountName
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 */
	public boolean createAccount(String accountName,String email,String password);
	/**
	 * 验证账户
	 * @param accountName
	 * @param password
	 * @return  boolean        
	 * @author      lpt
	 */
	public boolean verifyAccount(String accountName,String password);
	/**
	 * 更改账户密码
	 * @param accountName
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 */
	public boolean changePassword(String accountName,String password);
}
