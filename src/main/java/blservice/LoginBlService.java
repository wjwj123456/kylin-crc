package blservice;


public interface LoginBlService {
	/**
	 * �����˻�
	 * @param userName
	 * @param email
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 */
	public boolean createAccount(String userName,String email,String password);
	/**
	 * ��֤�˻�
	 * @param userName
	 * @param password
	 * @return  boolean        
	 * @author      lpt
	 */
	public boolean verifyAccount(String userName,String password);
	/**
	 * �����˻�����
	 * @param userName
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 */
	public boolean changePassword(String userName,String password);
}
