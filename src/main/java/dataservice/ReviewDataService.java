package dataservice;

import java.sql.SQLException;

import po.ReviewPO;

public interface ReviewDataService {
	/**
	 * @param   po
	 * @throws ClassNotFoundException
	 * @throws SQLException      
	 * @return boolean   �Ƿ�ɹ�����
	 * @author      lpt14
	 */
 public boolean saveReviewInfo(ReviewPO po) throws ClassNotFoundException, SQLException;
}
