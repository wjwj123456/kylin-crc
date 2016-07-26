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
	 * @see blservice.LockBlService#canWrite(java.lang.String)
	 *
	 */
	@Override
	public boolean canWrite(String taskName) {
		// TODO Auto-generated method stub

		try {
			return lockDataService.canWrite(taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * TODO:������������
	 *
	 * @author lpt14
	 * @since 2016��7��26��
	 * @param taskName
	 * @param flag
	 * @return
	 * @see blservice.LockBlService#setLock(java.lang.String, boolean)
	 *
	 */
	@Override
	public int setLock(String taskName, boolean flag) {
		// TODO Auto-generated method stub
		try {
			return lockDataService.setLock(taskName, flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
