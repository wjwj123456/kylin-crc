/**
* @author       lpt14
* @version      V1.0
*/
package data;

/**
 * TODO: ����������
 *
 * @author lpt14
 * @since 2016��7��26��
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
