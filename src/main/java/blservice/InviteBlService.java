package blservice;

import java.util.List;

import vo.TaskVO;

public interface InviteBlService {
	/**
	 *    ldk
	 * @param reviewerName
	 * @return TaskVOs
	 */
	public List<TaskVO> getInvitationInfo(String reviewerName);
	
	
	/**
	 *    ldk
	 * @param vo
	 * @return 0:É¾³ý³É¹¦   1£ºÉ¾³ýÊ§°Ü
	 */
	public int deleteInvitationInfo(String userName,String taskName); 
}
