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
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月26日
 * @see
 */
public class LockBlImpl implements LockBlService {
	LockDataService lockDataService = new LockDataImpl();

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月26日
	 * @param taskName
	 * @return
	 * @see blservice.LockBlService#getCurrentUser(java.lang.String)
	 *
	 */
	@Override
	public String getCurrentUser(String taskName) {
		// TODO Auto-generated method stub
		String name = null;
		try {
			name = lockDataService.getCurrentUser(taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月26日
	 * @param taskName
	 * @param userName
	 * @return
	 * @see blservice.LockBlService#setCurrentUser(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public int setCurrentUser(String taskName, String userName) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			i = lockDataService.setCurrentUser(taskName, userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
