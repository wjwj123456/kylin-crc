package blservice;

import java.util.List;

import vo.TaskVO;
import vo.UserVO;

public interface ReviewBlService {
	public int saveReviewInfo(TaskVO vo);
	public List<TaskVO> geTaskList(String userName);
	public List<UserVO> searchUserByKeyword(String keyword) throws ClassNotFoundException;
}
                 