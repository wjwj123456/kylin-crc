package bl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.InviteBlService;
import data.InviteDataImpl;
import dataservice.InviteDataService;
import po.TaskPO;
import vo.TaskVO;

public class InviteBlImpl implements InviteBlService{

	private  InviteDataService inviteDataService = new InviteDataImpl() ;
	
	public List<TaskVO> getInvitationInfo(String reviewerName) {
		// TODO Auto-generated method stub
		List<TaskVO> result=new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = inviteDataService.getInvitationInfo(reviewerName);
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

	public int deleteInvitationInfo(String userName, String taskName) {
		// TODO Auto-generated method stub
		int flag =-1;
		try {
			flag = inviteDataService.deleteInvitationInfo(userName, taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
