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
import po.UserPO;
import vo.TaskVO;
import vo.UserVO;

/**
* TODO: （类描述）
*
* @author lpt14
* @since 2016年7月8日
* @see
*/
public class ReviewBlImpl implements ReviewBlService {
	
	ReviewDataService reviewDataService=new ReviewDataImpl();
	

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月8日
	* @param vo
	* @see blservice.ReviewBlService#saveReviewInfo(vo.TaskVO)
	*
	*/
	public int saveReviewInfo(TaskVO vo) {
		// TODO Auto-generated method stub
		TaskPO po=new TaskPO(vo.getUserName(), vo.getTaskName(), vo.getType(), vo.getProject(), vo.getDescribe(), vo.getDeadline(), vo.getState());
		int flag=0;
		try {
			flag=reviewDataService.saveReviewInfo(po);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	
	}

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月8日
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

	/**
	* TODO:（方法描述）
	*
	* @author ldk14
	* @since 2016年7月9日
	* @param keyword
	* @return
	 * @throws ClassNotFoundException 
	* @see blservice.ReviewBlService#searchUserByKeyword(java.lang.String)
	*
	*/
	public List<UserVO> searchUserByKeyword(String keyword)  {
		// TODO Auto-generated method stub
		List<UserVO> result=new ArrayList<UserVO>();
		List<UserPO> list = new ArrayList<UserPO>();
		try {
			list = reviewDataService.searchUserByKeyword(keyword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(UserPO po:list){
			UserVO vo=new UserVO(po.getName(), po.getEmail(), po.getPassword());
		result.add(vo);
		}
		return result;
	}

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月9日
	* @param userName
	* @return
	* @see blservice.ReviewBlService#saveReviewer(java.lang.String[])
	*
	*/
	public int saveReviewer(String[] userName) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月9日
	* @param userName
	* @return
	* @see blservice.ReviewBlService#saveInvitation(java.lang.String[])
	*
	*/
	public int saveInvitation(String[] userName) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月9日
	* @param userName
	* @return
	* @see blservice.ReviewBlService#saveAcceptReviewer(java.lang.String)
	*
	*/
	public int saveAcceptReviewer(String userName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
