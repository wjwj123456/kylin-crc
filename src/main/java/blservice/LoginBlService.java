package blservice;

import java.sql.SQLException;

public interface LoginBlService {
	/**
	 * �����˻�
	 * @param userName
	 * @param email
	 * @param password
	 * @return  int     0��ʾ�ɹ���1��ʾ�û������ڣ�2��ʾδ֪����-1��ʾ���ݿ����ʧЧ
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int createAccount(String userName,String email,String password) throws ClassNotFoundException, SQLException;
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
	 * @return  int        0��ʾ�޸ĳɹ���1��ʾ�û��������ڣ�2��ʾ����û�иı䣬3��ʾδ֪����-1��ʾ���ݿ����ʧЧ
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int changePassword(String userName,String password) throws ClassNotFoundException, SQLException;
}
