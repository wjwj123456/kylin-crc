  /**
 * @author       lpt14
 * @version      V1.0
 */
package thread;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import bl.ReviewBlImpl;
import blservice.ReviewBlService;
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
		
		Date date=new Date();
		System.out.println(date.toString());
		List<TaskVO> voList=reviewBlService.getTaskList();
		DateFormat   df   =   new   SimpleDateFormat( "yyyy-MM-dd   HH:mm:ss ");
		for(TaskVO vo:voList){
			if(vo.getDeadline().compareTo(date)==0){
				System.out.println("vo>date");		
			}else{
				System.out.println("gg");
			}
		}
		  
	}
	

}
