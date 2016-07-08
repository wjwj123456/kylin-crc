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

import com.mysql.cj.fabric.xmlrpc.base.Data;
import com.mysql.cj.mysqlx.protobuf.MysqlxCrud.Order;

import dataservice.ReviewDataService;
import po.TaskPO;
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
		Timestamp tt=new Timestamp(po.getDeadline().getTime());
		pStatement = connection.prepareStatement(sql);
		pStatement.setString(0, po.getUserName());
		pStatement.setString(1, po.getTaskName());
		pStatement.setString(2, String.valueOf(po.getType()));
		pStatement.setString(3, po.getProject());
		pStatement.setString(4, po.getDescribe());
		pStatement.setTimestamp(5, tt);
		pStatement.setInt(6,po.getState());
		int i = pStatement.executeUpdate();
		if(i==0) {
			flag = 1;
		}
		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}


	
	
	public static void main(String[] args) {
//		ReviewDataImpl reviewDataImpl=new ReviewDataImpl();
//		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		
//		Date deadline = null;
//		try {
//			deadline = dateFormat.parse("2016-7-10 23:59:59");
//		} catch (ParseException e) {
////			 TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		TaskPO po=new TaskPO("task1", Type.code,"project1", "this is a code review", deadline);
//		try {
//			reviewDataImpl.saveReviewInfo(po);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	
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
		List<TaskPO> poList=new ArrayList<TaskPO>();
		int flag = -1;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "SELECT * FROM task WHERE uname = '" + userName + "'" +"order by "+"'deadline'"+"DESC";
		pStatement = connection.prepareStatement(sql);
		ResultSet rSet = pStatement.executeQuery();
		if(rSet.next()) {
			TaskPO po=new TaskPO(rSet.getString(0), rSet.getString(1), Type.valueOf(rSet.getString(2)), rSet.getString(3), rSet.getString(4), rSet.getDate(5), rSet.getInt(6));
			poList.add(po);
		}
		
		DBManager.stopAll(rSet, pStatement, connection);
		return poList;
		
	}
}
