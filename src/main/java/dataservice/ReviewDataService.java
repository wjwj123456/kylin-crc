package dataservice;

import java.sql.SQLException;

import po.ReviewPO;

public interface ReviewDataService {
	/**
	 * @param   po
	 * @throws ClassNotFoundException
	 * @throws SQLException      
	 * @return boolean   是否成功保存
	 * @author      lpt14
	 */
 public boolean saveReviewInfo(ReviewPO po) throws ClassNotFoundException, SQLException;
}
