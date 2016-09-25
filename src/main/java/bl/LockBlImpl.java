/**
* @author       lpt14
* @version      V1.0
*/
package bl;

import java.sql.SQLException;

import blservice.LockBlService;
import data.LockDataImpl;
import dataservice.LockDataService;

/**
 * TODO: ����������
 *
 * @author lpt14
 * @since 2016��7��26��
 * @see
 */
public class LockBlImpl implements LockBlService {
	LockDataService lockDataService = new LockDataImpl();

	/**
	 * TODO:������������
	 *
	 * @author lpt14
	 * @since 2016��7��26��
	 * @param taskName
	 * @return
	 * @see blservice.LockBlService#getCurrentUser(java.lang.String)
	 *
	 */
	@Override
	public String getCurrentUser(String taskName) {
		String name = null;
		try {
			name = lockDataService.getCurrentUser(taskName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * TODO:������������
	 *
	 * @author lpt14
	 * @since 2016��7��26��
	 * @param taskName
	 * @param userName
	 * @return
	 * @see blservice.LockBlService#setCurrentUser(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public int setCurrentUser(String taskName, String userName) {
		int i = 0;
		try {
			i = lockDataService.setCurrentUser(taskName, userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
