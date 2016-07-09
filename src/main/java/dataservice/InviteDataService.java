package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.TaskPO;


public interface InviteDataService {
	/**
	 *    ldk
	 * @param reviewerName
	 * @return TaskPOs
	 */
	public List<TaskPO> getInvitationInfo(String reviewerName)throws SQLException, ClassNotFoundException;
	
	
	/**
	 *    ldk
	 * @param vo
	 * @return 0:É¾³ý³É¹¦   1£ºÉ¾³ýÊ§°Ü
	 */
	public int deleteInvitationInfo(String userName,String taskName)throws SQLException, ClassNotFoundException; 
}
