package dataservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import po.TaskPO;

public interface ReviewDataService {
	/**
	 * @param po
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return int 0 - �ɹ� 1 - �������ظ�  
	 * @author lpt14
	 */
	public int saveReviewInfo(TaskPO po) throws ClassNotFoundException, SQLException;
	
	public List<TaskPO> getTaskList(String userName) throws SQLException, ClassNotFoundException;

}
