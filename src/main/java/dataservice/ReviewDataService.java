package dataservice;

import java.sql.SQLException;

import po.ReviewPO;

public interface ReviewDataService {
	/**
	 * @param po
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return int 0 - �ɹ� 1 - �������ظ�  
	 * @author lpt14
	 */
	public int saveReviewInfo(ReviewPO po) throws ClassNotFoundException, SQLException;

}
