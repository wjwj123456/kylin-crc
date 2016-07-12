package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dataservice.ReportDataService;
import po.ReportPO;

public class ReportDataImpl implements ReportDataService {

	private PreparedStatement pStatement = null;

	public int createReport(List<ReportPO> pos) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = -1;

		for (int i = 0; i < pos.size(); i++) {

			String sql = "INSERT INTO report (tname, uname,filename, page,location,description,state,origin) VALUES (?, ?, ?, ?, ?, ?,?,? )";

			pStatement = DBManager.getPreparedStatement(sql);
			pStatement.setString(1, pos.get(i).getTaskName());
			pStatement.setString(2, pos.get(i).getUserName());
			pStatement.setString(3, pos.get(i).getFileName());
			pStatement.setInt(4, pos.get(i).getPage());
			pStatement.setInt(5, pos.get(i).getLocation());
			pStatement.setString(6, pos.get(i).getDescription());
			pStatement.setInt(7, 0);
			pStatement.setInt(8, 0);

			try {
				int j = pStatement.executeUpdate();
				if (j == 1) {
					flag = 0;
				} else {
					flag = 2;
				}
			} catch (Exception SQLIntegrityConstraintViolationException) {
				flag = 1;
			}
			DBManager.closeConnection();
		}

		return flag;
	}

	public int setCompleteTime(String taskName, String reviewerName, double time)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = -1;

		String sql = "UPDATE review SET  time = ? WHERE tname = '" + taskName + "' and uname = '" + reviewerName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setDouble(1, time);
		int i = pStatement.executeUpdate();
		if (i == 1)
			flag = 0;
		else
			flag = 1;
		DBManager.closeConnection();
		return flag;

	}

}
