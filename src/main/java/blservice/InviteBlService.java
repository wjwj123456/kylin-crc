package blservice;

import java.sql.SQLException;
import java.util.List;

import vo.TaskVO;

public interface InviteBlService {
	/**
	 *    ldk
	 * @param reviewerName
	 * @return TaskVOs
	 */
	public List<TaskVO> getInvitationInfo(String reviewerName)throws SQLException, ClassNotFoundException;
	
	
	/**
	 *    ldk
	 * @param vo
	 * @return 0:É¾³ý³É¹¦   1£ºÉ¾³ýÊ§°Ü
	 */
	public int deleteInvitationInfo(String userName,String taskName); 
}
