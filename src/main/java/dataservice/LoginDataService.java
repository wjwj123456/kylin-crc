package dataservice;

import java.sql.SQLException;

/**
 * Created by lpt on 16-7-8.
 */
public interface LoginDataService {
	/**
	 * 创建一个用户
	 * @param userName
	 * @param email
	 * @param password
	 * @return  int    0表示成功，1表示用户名存在，2表示未知错误，-1表示数据库操作失效
	 * @author     lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int createAccount(String userName,String email,String password) throws ClassNotFoundException, SQLException;
	/**
	 * 验证一个用户的账户名和密码是否匹配
	 * @param userName
	 * @param password
	 * @return int		0表示验证成功，1表示用户名不存在，2表示密码错误 ,-1表示数据库操作失效
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int verifyAccount(String userName,String password) throws ClassNotFoundException, SQLException;
	/**
	 * 用户修改自己的密码
	 * @param userName
	 * @param password
	 * @return  int		0表示修改成功，1表示用户名不存在，2表示密码没有改变，3表示未知错误，-1表示数据库操作失效     
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int changePassword(String userName,String password) throws ClassNotFoundException, SQLException;
}
