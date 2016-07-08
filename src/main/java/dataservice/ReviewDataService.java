package dataservice;

import java.sql.SQLException;

import po.ReviewPO;

public interface ReviewDataService {
	/**
	 * @param po
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return int 0 - 成功 1 - 任务名重复  
	 * @author lpt14
	 */
	public int saveReviewInfo(ReviewPO po) throws ClassNotFoundException, SQLException;

}
