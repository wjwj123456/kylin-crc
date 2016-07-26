/**
* @author       lpt14
* @version      V1.0
*/
package blservice;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月26日
 * @see
 */
public interface LockBlService {
	public boolean canWrite(String taskName);

	public int setLock(String taskName, boolean flag);
}
