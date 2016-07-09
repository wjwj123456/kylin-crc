package blservice;

import java.util.List;

import vo.TaskVO;
import vo.UserVO;

public interface ReviewBlService {
	public int saveReviewInfo(TaskVO vo);
	public List<TaskVO> geTaskList(String userName);
	public List<UserVO> searchUserByKeyword(String keyword) throws ClassNotFoundException;
	/**
	 * save all user who has been invited
	 * @param userName  
	 * @return int   0 -- success     1-- fail   
	 * @author      lpt14
	 */
	public int saveInvitation(String[] userName,String taskName);     
	/**
	 * save user who agree the invitation
	 * @param  userName 
	 * @return int 0 -- success     1-- fail       
	 * @author      lpt14
	 */
	public int saveAcceptReviewer(String userName,String taskName);
	
	
	
	
	
	
}
                 