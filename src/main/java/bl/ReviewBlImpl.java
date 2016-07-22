/**
* @author       lpt14
* @version      V1.0
*/
package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blservice.ReviewBlService;
import data.ReviewDataImpl;
import dataservice.ReviewDataService;
import po.TaskPO;
import po.UserPO;
import vo.Language;
import vo.State;
import vo.TaskVO;
import vo.Type;
import vo.UserVO;

/**
 *
 * 
 * @author lpt14
 */
public class ReviewBlImpl implements ReviewBlService {

	ReviewDataService reviewDataService = new ReviewDataImpl();

	/**
	 *
	 * @author lpt14
	 * 
	 * @param vo
	 * @see blservice.ReviewBlService#saveReviewInfo(vo.TaskVO)
	 *
	 */
	public int saveReviewInfo(TaskVO vo) {
		// TODO Auto-generated method stub
		TaskPO po = new TaskPO(vo.getUserName(), vo.getTaskName(), vo.getType(), vo.getProject(), vo.getDescribe(),
				vo.getDeadline(), vo.getState(), vo.getLanguage());
		int flag = 0;
		try {
			flag = reviewDataService.saveReviewInfo(po);
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
	 *
	 * 
	 * @author lpt14
	 * 
	 * @param userName
	 * @return
	 * @see blservice.ReviewBlService#geTaskList(java.lang.String)
	 *
	 */
	public List<TaskVO> getDoingTaskList(String userName) {
		// TODO Auto-generated method stub
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = reviewDataService.getDoingTaskList(userName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TaskPO po : list) {
			TaskVO vo = new TaskVO(po);
			result.add(vo);
		}
		return result;
	}

	/**
	 *
	 * 
	 * @author ldk14
	 * 
	 * @param keyword
	 * @return
	 * @throws ClassNotFoundException
	 * @see blservice.ReviewBlService#searchUserByKeyword(java.lang.String)
	 *
	 */
	public List<UserVO> searchUserByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<UserVO> result = new ArrayList<UserVO>();
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
		for (UserPO po : list) {
			UserVO vo = new UserVO(po.getName(), po.getEmail(), po.getPassword());
			result.add(vo);
		}
		return result;
	}

	@Override
	public List<TaskVO> searchTasksByKeyword(String keyword, Language language) {
		// TODO Auto-generated method stub
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = reviewDataService.searchTasksByKeyword(keyword, language);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TaskPO po : list) {
			result.add(new TaskVO(po));
		}
		return result;
	}

	public List<TaskVO> getEndTaskList(String userName) {
		// TODO Auto-generated method stub
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = reviewDataService.getEndTaskList(userName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TaskPO po : list) {
			TaskVO vo = new TaskVO(po);
			result.add(vo);
		}
		return result;
	}

	/**
	 *
	 *
	 * @author lpt14
	 * @param userName
	 * @return
	 * @see blservice.ReviewBlService#saveInvitation(java.lang.String[])
	 *
	 */
	public int saveInvitation(String[] userName, String taskName) {
		int flag = 0;
		try {
			flag = reviewDataService.saveInvitation(userName, taskName);

			TaskVO task = getTaskVOByTaskName(taskName);
			UserBlImpl userBl = new UserBlImpl();
			UserVO user = userBl.getUserVOByName(task.getUserName());
			String url = "localhost:8080/crc/tasks.jsp?taskName=" + taskName;
			String[] userEmails = new String[userName.length];

			for (int i = 0; i < userName.length; i++) {
				userEmails[i] = userBl.getUserVOByName(userName[i]).getEmail();
			}
			SendMail.sendMail(user.getEmail(), userEmails, user.getName(), taskName, url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 *
	 * 
	 * @author lpt14
	 * @param userName
	 * @return
	 * @see blservice.ReviewBlService#saveAcceptReviewer(java.lang.String)
	 *
	 */
	public int saveAcceptReviewer(String userName, String taskName) {
		// TODO Auto-generated method stub
		int flag = 0;
		try {
			flag = reviewDataService.saveAcceptReviewer(userName, taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FriendBlImpl friendBlImpl = new FriendBlImpl();
		String uname = getTaskVOByTaskName(taskName).getUserName();

		flag = friendBlImpl.addFriend(userName, uname);
		flag = friendBlImpl.addFriend(uname, userName);

		return flag;
	}

	/**
	 *
	 * 
	 * @author lpt14
	 * 
	 * @return
	 * @see blservice.ReviewBlService#getTaskList()
	 *
	 */
	public List<TaskVO> getTaskList() {
		// TODO Auto-generated method stub
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = reviewDataService.getTaskList();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TaskVO> voList = new ArrayList<TaskVO>();
		for (TaskPO po : list) {
			voList.add(new TaskVO(po.getUserName(), po.getTaskName()));

		}

		return voList;

	}

	public TaskVO getTaskVOByTaskName(String taskName) {
		// TODO Auto-generated method stub

		String userName = "";
		String tName = "";
		vo.Type type = Type.code;
		String project = "";
		String describe = "";
		Date deadline = new Date();
		int state = -1;
		Language language = Language.c;

		try {
			TaskPO po = reviewDataService.getTaskPOByTaskName(taskName);
			userName = po.getUserName();
			tName = po.getTaskName();
			type = po.getType();
			project = po.getProject();
			describe = po.getDescribe();
			deadline = po.getDeadline();
			state = po.getState();
			language = po.getLanguage();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TaskVO vo = new TaskVO(userName, tName, type, project, describe, deadline, state, language);
		return vo;
	}

	@Override
	public List<TaskVO> getJoinedDoingTasksByUserName(String userName) {
		// TODO Auto-generated method stub
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = reviewDataService.getJoinedDoingTasksByUserName(userName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TaskPO po : list) {
			result.add(new TaskVO(po));
		}
		return result;
	}

	@Override
	public List<TaskVO> getJoinedEndTasksByUserName(String userName) {
		// TODO Auto-generated method stub
		List<TaskVO> result = new ArrayList<TaskVO>();
		List<TaskPO> list = new ArrayList<TaskPO>();
		try {
			list = reviewDataService.getJoinedEndTasksByUserName(userName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TaskPO po : list) {
			result.add(new TaskVO(po));
		}
		return result;
	}

	/**
	 * TODO:锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 *
	 * @author lpt14
	 * @since 2016锟斤拷7锟斤拷14锟斤拷
	 * @param userName
	 * @param takName
	 * @return
	 * @see blservice.ReviewBlService#getUserState(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public State getUserState(String userName, String taskName) {
		// TODO Auto-generated method stub
		State state = null;
		try {
			state = reviewDataService.getUserState(userName, taskName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}

	/**
	 * TODO:锛堟柟娉曟弿杩帮級
	 *
	 * @author lpt14
	 * @since 2016骞�7鏈�20鏃�
	 * @param taskName
	 * @return
	 * @see blservice.ReviewBlService#getTaskState(java.lang.String)
	 *
	 */
	@Override
	public State getTaskState(String taskName) {
		// TODO Auto-generated method stub
		State state = null;
		try {
			state = reviewDataService.getTaskState(taskName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月22日
	 * @param userName
	 * @param taskName
	 * @return
	 * @see blservice.ReviewBlService#isOwner(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public boolean isOwner(String userName, String taskName) {
		// TODO Auto-generated method stub
		try {
			return reviewDataService.isOwner(userName, taskName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
