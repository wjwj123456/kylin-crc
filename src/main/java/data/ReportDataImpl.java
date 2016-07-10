package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dataservice.ReportDataService;
import po.ReportPO;

public class ReportDataImpl implements ReportDataService{

	public int createReport(List<ReportPO> pos)throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = 0;
		Connection connection = DBManager.connect();
		PreparedStatement pStatement = null;
		
		for(int i =0;i<pos.size();i++){
			String sql = "INSERT INTO report (tname, uname,filename, page,location,description,state,origin) VALUES (?, ?, ?, ?, ?, ?,?,? )";

			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, pos.get(i).getTaskName());
			pStatement.setString(2, pos.get(i).getUserName());
			pStatement.setString(3, pos.get(i).getFileName());
			pStatement.setInt(4, pos.get(i).getPage());
			pStatement.setInt(5, pos.get(i).getLocation());
			pStatement.setString(6, pos.get(i).getDescription());
			pStatement.setInt(7, 1);
			pStatement.setInt(8, 1);
			int j = pStatement.executeUpdate();
			if (j == 0) {
				flag = 1;
			}
		}

		DBManager.stopAll(null, pStatement, connection);
		return flag;
	}

	public int setCompleteTime(double time) throws ClassNotFoundException, SQLException{
		return 0;
		// TODO Auto-generated method stub
//		int flag = 0;
//		Connection connection = DBManager.connect();
//		PreparedStatement pStatement = null;
//		
//		String sql = "INSERT INTO review (tname, uname,filename, page,location,description,state,origin) VALUES (?, ?, ?, ?, ?, ?,?,? )";
//		pStatement = connection.prepareStatement(sql);

	}

}
