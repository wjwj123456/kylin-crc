/**
* @author       lpt14
* @version      V1.0
*/
package blservice;

/**
 * TODO: ����������
 *
 * @author lpt14
 * @since 2016��7��26��
 * @see
 */
public interface LockBlService {

	public String getCurrentUser(String taskName);

	public int setCurrentUser(String taskName, String userName);
}
