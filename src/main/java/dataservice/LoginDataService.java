package dataservice;

import java.sql.SQLException;

/**
 * Created by lpt on 16-7-8.
 */
public interface LoginDataService {
	/**
	 * ����һ���û�
	 * @param userName
	 * @param email
	 * @param password
	 * @return  int    0��ʾ�ɹ���1��ʾ�û������ڣ�2��ʾδ֪����-1��ʾ���ݿ����ʧЧ
	 * @author     lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int createAccount(String userName,String email,String password) throws ClassNotFoundException, SQLException;
	/**
	 * ��֤һ���û����˻����������Ƿ�ƥ��
	 * @param userName
	 * @param password
	 * @return int		0��ʾ��֤�ɹ���1��ʾ�û��������ڣ�2��ʾ������� ,-1��ʾ���ݿ����ʧЧ
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int verifyAccount(String userName,String password) throws ClassNotFoundException, SQLException;
	/**
	 * �û��޸��Լ�������
	 * @param userName
	 * @param password
	 * @return  int		0��ʾ�޸ĳɹ���1��ʾ�û��������ڣ�2��ʾ����û�иı䣬3��ʾδ֪����-1��ʾ���ݿ����ʧЧ     
	 * @author      lpt
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int changePassword(String userName,String password) throws ClassNotFoundException, SQLException;
}