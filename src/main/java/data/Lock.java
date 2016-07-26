/**
* @author       lpt14
* @version      V1.0
*/
package data;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月26日
 * @see
 */
public class Lock {
	private static boolean lock;

	public void lock() {
		this.lock = false;
	}

	public void unlock() {
		this.lock = true;
	}

	public boolean getLock() {
		return lock;
	}
}
