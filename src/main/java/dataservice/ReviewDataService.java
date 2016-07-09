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
			
	
	public  int saveReviewer(String userName);

	public List<UserPO> searchUserByKeyword(String keyword) throws SQLException, ClassNotFoundException;


}
