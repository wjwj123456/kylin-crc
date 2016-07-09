package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataservice.InviteDataService;
import po.TaskPO;
import vo.Type;

public class InviteDataImpl implements InviteDataService{


	



	
	
	public List<TaskPO> getInvitationInfo(String reviewerName) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<TaskPO> poList = new ArrayList<TaskPO>();
		
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		String sql = "SELECT * FROM task WHERE uname = '" + reviewerName + "' and isAgree = '0'" ;
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

	public int deleteInvitationInfo(String userName, String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		int flag = -1;
		String sql = "DELETE   FROM task WHERE uname = '" + userName + "' and tname = '" +taskName+"'";
		pStatement = connection.prepareStatement(sql);
		int i = pStatement.executeUpdate();
		
		if(i==1)
			flag = 0;
		else
			flag = 1;

		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

}
