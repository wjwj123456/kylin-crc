  /**
 * @author       lpt14
 * @version      V1.0
 */
package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
* TODO: ����������
*
* @author lpt14
* @since 2016��7��9��
* @see
*/
public class Test {
	public static void executeFixedRate() {  
	    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);  
	    executor.scheduleAtFixedRate(  
	            new CheckDeadline(),  
	            0,  
	            1000,  
	            TimeUnit.MILLISECONDS);  
	}  
	public static void main(String[] args) {
		executeFixedRate();
	}
}
