  /**
 * @author       lpt14
 * @version      V1.0
 */
package thread;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import bl.ReviewBlImpl;
import blservice.ReviewBlService;
import vo.State;
import vo.TaskVO;

/**
* TODO: ����������
*
* @author lpt14
* @since 2016��7��9��
* @see
*/
public class CheckDeadline implements Runnable{

	ReviewBlService reviewBlService=new ReviewBlImpl();
	
	
	
	/**
	* TODO:������������
	*
	* @author lpt14
	* @since 2016��7��9��
	* @see java.lang.Runnable#run()
	*
	*/
	public void run() {
		
		Date thisTime = new Date();
		System.out.println(thisTime.toString());
		List<TaskVO> voList=reviewBlService.getAllDoingTaskList();
		System.out.println(voList.size());
		for(TaskVO vo:voList){
			Date deadline = vo.getDeadline();
			if(deadline.before(thisTime)) {
				reviewBlService.setState(State.timefinish, vo.getTaskName());
			}
		}
		  
	}
	

}
