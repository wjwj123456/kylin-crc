package dataservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import po.TaskPO;
import po.UserPO;

public interface ReviewDataService {
	/**
	 * @param po
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return int 0 - 成功 1 - 任务名重复  
	 * @author lpt14
	 */
	public int saveReviewInfo(TaskPO po) throws ClassNotFoundException, SQLException;
	
	public List<TaskPO> getTaskList(String userName) throws SQLException, ClassNotFoundException;
			
	

	public List<UserPO> searchUserByKeyword(String keyword) throws SQLException, ClassNotFoundException;


	/**
	 * save all user who has been invited
	 * @param userName  
	 * @return int   0 -- success     1-- fail   
	 * @author      lpt14
	 */
	public int saveInvitation(String[] userName,String taskName) throws SQLException, ClassNotFoundException;     
	/**
	 * save user who agree the invitation
	 * @param  userName 
	 * @return int 0 -- success     1-- fail       
	 * @author      lpt14
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int saveAcceptReviewer(String userName,String taskName) throws SQLException, ClassNotFoundException;
	
}
