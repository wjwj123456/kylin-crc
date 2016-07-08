package blservice;


public interface LoginBlService {
	/**
	 * �����˻�
	 * @param accountName
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 */
	public boolean createAccount(String accountName,String email,String password);
	/**
	 * ��֤�˻�
	 * @param accountName
	 * @param password
	 * @return  boolean        
	 * @author      lpt
	 */
	public boolean verifyAccount(String accountName,String password);
	/**
	 * �����˻�����
	 * @param accountName
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 */
	public boolean changePassword(String accountName,String password);
}
