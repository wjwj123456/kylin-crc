package blservice;

import java.sql.SQLException;

public interface LoginBlService {
	/**
	 * �����˻�
	 * @param userName
	 * @param email
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean createAccount(String userName,String email,String password) throws ClassNotFoundException, SQLException;
	/**
	 * ��֤�˻�
	 * @param userName
	 * @param password
	 * @return  int  0��ʾ��֤�ɹ���1��ʾ�û��������ڣ�2��ʾ������� ,-1��ʾ���ݿ����ʧЧ
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int verifyAccount(String userName,String password) throws ClassNotFoundException, SQLException;
	/**
	 * �����˻�����
	 * @param userName
	 * @param password
	 * @return  boolean       
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean changePassword(String userName,String password) throws ClassNotFoundException, SQLException;
}
