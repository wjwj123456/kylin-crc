  /**
 * @author       lpt14
 * @version      V1.0
 */
package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.ReviewBlService;
import data.ReviewDataImpl;
import dataservice.ReviewDataService;
import po.TaskPO;
import vo.TaskVO;

/**
* TODO: ����������
*
* @author lpt14
* @since 2016��7��8��
* @see
*/
public class ReviewBlImpl implements ReviewBlService {
	
	ReviewDataService reviewDataService=new ReviewDataImpl();
	

	/**
	* TODO:������������
	*
	* @author lpt14
	* @since 2016��7��8��
	* @param vo
	* @see blservice.ReviewBlService#saveReviewInfo(vo.TaskVO)
	*
	*/
	public void saveReviewInfo(TaskVO vo) {
		// TODO Auto-generated method stub
		TaskPO po=new TaskPO(vo.getUserName(), vo.getTaskName(), vo.getType(), vo.getProject(), vo.getDescribe(), vo.getDeadline(), vo.getState());
		try {
			reviewDataService.saveReviewInfo(po);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	* TODO:������������
	*
	* @author lpt14
	* @since 2016��7��8��
	* @param userName
	* @return
	* @see blservice.ReviewBlService#geTaskList(java.lang.String)
	*
	*/
	public List<TaskVO> geTaskList(String userName) {
		// TODO Auto-generated method stub
		List<TaskVO> result=new ArrayList<TaskVO>();
		List<TaskPO> list = null;
		try {
			list = reviewDataService.getTaskList(userName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(TaskPO po:list){
			TaskVO vo=new TaskVO(po.getUserName(), po.getTaskName(), po.getType(), po.getProject(), po.getDescribe(), po.getDeadline(), po.getState());
		result.add(vo);
		}
		return result;
	}

}
