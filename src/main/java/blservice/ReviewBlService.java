package blservice;

import java.util.List;

import vo.TaskVO;

public interface ReviewBlService {
	public void saveReviewInfo(TaskVO vo);
	public List<TaskVO> geTaskList(String userName);
}
                 