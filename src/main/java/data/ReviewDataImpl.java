package data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataservice.ReviewDataService;
import po.TaskPO;
import po.UserPO;
import tools.Tools;
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
	 * @see dataservice.ReviewDataService#saveReviewInfo(po.TaskPO)
	 *
	 */
	public int saveReviewInfo(TaskPO po) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO task (uname, tname,type, project,discribe,deadline,state) VALUES (?, ?, ?, ?, ?, ?,? )";
		Timestamp tt = new Timestamp(po.getDeadline().getTime());
		pStatement = connection.prepareStatement(sql);
		pStatement.setString(1, po.getUserName());
		pStatement.setString(2, po.getTaskName());
		pStatement.setString(3, String.valueOf(po.getType()));
		pStatement.setString(4, po.getProject());
		pStatement.setString(5, po.getDescribe());
		pStatement.setTimestamp(6, tt);
		pStatement.setInt(7, po.getState());
		int i = pStatement.executeUpdate();
		if (i == 0) {
			flag = 1;
		}
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	public static void main(String[] args) {
		ReviewDataImpl reviewDataImpl = new ReviewDataImpl();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Date deadline1 = null;
		Date deadline2 = null;
		Date deadline3 = null;
		try {
			deadline1 = dateFormat.parse("2016-7-10 23:59:59");
			deadline2 = dateFormat.parse("2016-7-12 23:59:59");
			deadline3 = dateFormat.parse("2016-7-14 23:59:59");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TaskPO po1 = new TaskPO("crc", "task1", Type.code, "project1", "this is a code review", deadline1, 0);
		TaskPO po2 = new TaskPO("crc", "task2", Type.code, "project1", "this is a code review", deadline2, 0);
		TaskPO po3 = new TaskPO("crc", "task3", Type.code, "project1", "this is a code review", deadline3, 0);
		// try {
		// reviewDataImpl.saveReviewInfo(po1);
		// reviewDataImpl.saveReviewInfo(po2);
		// reviewDataImpl.saveReviewInfo(po3);
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		try {
			List<TaskPO> list = reviewDataImpl.getTaskList("crc");
			for (TaskPO po : list) {
				// System.out.println(po.getDeadline());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月8日
	 * @param userName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#getTaskList(java.lang.String)
	 *
	 */
	public List<TaskPO> getTaskList(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();
		int flag = -1;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "SELECT * FROM task WHERE uname = '" + userName + "'" + "order by " + "'deadline'" + "DESC";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		while (rSet.next()) {
			TaskPO po = new TaskPO(rSet.getString(1), rSet.getString(2), Type.valueOf(rSet.getString(3)),
					rSet.getString(4), rSet.getString(5), rSet.getDate(6), rSet.getInt(7));
			poList.add(po);

		}

		DBManager.stopAll(rSet, pStatement, connection);
		return poList;

	}

	/**
	 * TODO:（方法描述）
	 *
	 * - * @author lpt14 + * @author ldk14
	 * 
	 * @since 2016年7月9日
	 * @param keyword
	 * @return + * @throws SQLException + * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see dataservice.ReviewDataService#searchUserByKeyword(java.lang.String)
	 *
	 */
	public List<UserPO> searchUserByKeyword(String keyword) throws ClassNotFoundException, SQLException {

		// TODO Auto-generated method stub
		List<UserPO> poList = new ArrayList<UserPO>();
		int flag = -1;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "SELECT * FROM user WHERE uname like '%" + keyword + "%'";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		while (rSet.next()) {
			UserPO po = new UserPO(rSet.getString(1), rSet.getString(2), rSet.getString(3));
			poList.add(po);

		}

		DBManager.stopAll(rSet, pStatement, connection);
		return poList;
	}


	/**
	 * TODO:（方法描述）
	 *
	 * @author lpt14
	 * @since 2016年7月9日
	 * @param userName
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see dataservice.ReviewDataService#saveAcceptReviewer(java.lang.String)
	 *
	 */
	public int saveAcceptReviewer(String userName,String taskName) throws SQLException, ClassNotFoundException {
		
		// TODO Auto-generated method stub
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "UPDATE review SET isAgree = ? WHERE uname = ? and tname= ?";
		pStatement = connection.prepareStatement(sql);
		pStatement.setInt(1, 1);
		pStatement.setString(2, userName);
		pStatement.setString(3, taskName);
		int i = pStatement.executeUpdate();
		if(i==1)	flag = 0;
		else	flag = 3;
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月9日
	* @param userName
	* @return
	* @throws SQLException
	* @throws ClassNotFoundException
	* @see dataservice.ReviewDataService#saveInvitation(java.lang.String[])
	*
	*/
	public int saveInvitation(String[] userName,String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO review (tname, ,uname, isAgree) VALUES (?, ?, ?)";

		pStatement = connection.prepareStatement(sql);
		for (int i = 0; i < userName.length; i++) {
			pStatement.setString(1, taskName);
			pStatement.setString(2, userName[i]);
			pStatement.setInt(3, 0);
		}
		int i = pStatement.executeUpdate();
		if (i == 0) {
			flag = 1;
		}
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

}
