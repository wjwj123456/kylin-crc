/**
* @author       lpt14
* @version      V1.0
*/
package dataservice;

import java.sql.SQLException;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月26日
 * @see
 */
public interface LockDataService {
	public boolean canWrite(String taskName) throws SQLException, ClassNotFoundException;

	public int setLock(String taskName, boolean flag) throws SQLException;
}
