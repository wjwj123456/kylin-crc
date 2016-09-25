package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.InviteBlService;
import data.InviteDataImpl;
import dataservice.InviteDataService;
import po.TaskPO;
import vo.TaskVO;

public class InviteBlImpl implements InviteBlService {

	private InviteDataService inviteDataService = new InviteDataImpl();

	/**
	 * ldk ����������Ϣ
	 */

	public List<TaskVO> getInvitationInfo(String reviewerName) {
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = inviteDataService.getInvitationInfo(reviewerName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		for (TaskPO po : list) {
			result.add(new TaskVO(po));
		}
		return result;
	}

	/**
	 * ldk 0: delete success 1:delete fail
	 */
	public int deleteInvitationInfo(String userName, String taskName) {
		int flag = -1;
		try {
			flag = inviteDataService.deleteInvitationInfo(userName, taskName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public List<TaskVO> getAllDoingTask(String createrName) {
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = inviteDataService.getAllDoingTask(createrName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		for (TaskPO po : list) {
			result.add(new TaskVO(po));
		}
		return result;
	}

	public List<TaskVO> getAllCompleteTask(String createrName) {
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = inviteDataService.getAllCompleteTask(createrName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		for (TaskPO po : list) {
			result.add(new TaskVO(po));
		}
		return result;
	}

	public List<String> getAgreeUser(String taskName) {
		List<String> nameList = new ArrayList<String>();
		try {
			nameList = inviteDataService.getAgreeUser(taskName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return nameList;
	}

	public List<String> getDisagreeUser(String taskName) {
		List<String> nameList = new ArrayList<String>();
		try {
			nameList = inviteDataService.getDisagreeUser(taskName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return nameList;
	}

}
