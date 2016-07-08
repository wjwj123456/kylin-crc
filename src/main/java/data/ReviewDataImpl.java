  /**
 * @author       lpt14
 * @version      V1.0
 */
package data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.cj.fabric.xmlrpc.base.Data;

import dataservice.ReviewDataService;
import po.ReviewPO;
import vo.Type;

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
	public int saveReviewInfo(ReviewPO po) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO review (rname, type, project,discribe,deadline) VALUES (?, ?, ?, ?, ?)";
		Timestamp tt=new Timestamp(po.getDeadline().getTime());
		pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, po.getName());
		pStatement.setString(2, String.valueOf(po.getType()));
		pStatement.setString(3, po.getProject());
		pStatement.setString(4, po.getDescribe());
		pStatement.setTimestamp(5, tt);
		int i = pStatement.executeUpdate();
		if(i==0) {
			flag = 1;
		}
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}


	
	
	public static void main(String[] args) {
		ReviewDataImpl reviewDataImpl=new ReviewDataImpl();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Date deadline = null;
		try {
			deadline = dateFormat.parse("2016-7-10 23:59:59");
		} catch (ParseException e) {
//			 TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ReviewPO po=new ReviewPO("task1", Type.code,"project1", "this is a code review", deadline);
		try {
			reviewDataImpl.saveReviewInfo(po);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
