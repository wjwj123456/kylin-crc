/**
* @author       lpt14
* @version      V1.0
*/
package dataservice;

import java.sql.SQLException;

/**
 * TODO: ����������
 *
 * @author lpt14
 * @since 2016��7��26��
 * @see
 */
public interface LockDataService {
	public boolean canWrite(String taskName) throws SQLException, ClassNotFoundException;

	public int setLock(String taskName, boolean flag) throws SQLException;
}
