package dataservice;



/**
 * Created by lpt on 16-7-8.
 */
public interface LoginDataService {
	/**
	 * 创建一个用户
	 * @param accountName
	 * @param password
	 * @return  boolean    
	 * @author     lpt
	 */
	public boolean createAccount(String userName,String email,String password);
	/**
	 * 验证一个用户的账户名和密码是否匹配
	 * @param accountName
	 * @param password
	 * @return  boolean        
	 * @author      lpt
	 */
	public boolean verifyAccount(String userName,String password);
	/**
	 * 用户修改自己的密码
	 * @param accountName
	 * @param password
	 * @return  boolean         
	 * @author      lpt
	 */
	public boolean changePassword(String userName,String password);
}
