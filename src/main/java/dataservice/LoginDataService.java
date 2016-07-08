package dataservice;



/**
 * Created by lpt on 16-7-8.
 */
public interface LoginDataService {
	/**
	 * ����һ���û�
	 * @param accountName
	 * @param password
	 * @return  boolean    
	 * @author     lpt
	 */
	public boolean createAccount(String userName,String email,String password);
	/**
	 * ��֤һ���û����˻����������Ƿ�ƥ��
	 * @param accountName
	 * @param password
	 * @return  boolean        
	 * @author      lpt
	 */
	public boolean verifyAccount(String userName,String password);
	/**
	 * �û��޸��Լ�������
	 * @param accountName
	 * @param password
	 * @return  boolean         
	 * @author      lpt
	 */
	public boolean changePassword(String userName,String password);
}
