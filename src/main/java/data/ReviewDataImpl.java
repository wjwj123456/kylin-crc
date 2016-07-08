  /**
 * @author       lpt14
 * @version      V1.0
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import dataservice.ReviewDataService;
import po.ReviewPO;

/**
* review data impl
*
* @author lpt14
* @since 2016年7月8日
* @see
*/
public class ReviewDataImpl implements ReviewDataService {

	/**
	* save review infomation to database
	*
	* @author lpt14
	* @since 2016年7月8日
	* @param po
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	* @see dataservice.ReviewDataService#saveReviewInfo(po.ReviewPO)
	*
	*/
	public boolean saveReviewInfo(ReviewPO po) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		boolean flag = true;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO review (rname, type, project,discribe,deadline) VALUES (?, ?, ?, ?, ?, )";
		pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, po.getName());
		pStatement.setString(2, String.valueOf(po.getType()));
		pStatement.setString(3, po.getProject());
		pStatement.setString(4, po.getDescribe());
		pStatement.setString(5, String.valueOf(po.getDeadline()));
		int i = pStatement.executeUpdate();
		if(i==0) {
			flag = false;
		}
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

}
