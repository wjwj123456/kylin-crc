package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataservice.ReportDataService;
import po.ReportPO;
import vo.State;
import vo.Type;

public class ReportDataImpl implements ReportDataService {

	private PreparedStatement pStatement = null;
	private ResultSet rSet = null;

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

	@Override
	public int deleteReport(ReportPO po) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = -1;

		String sql = "DELETE * FROM report  WHERE tname = '" + po.getTaskName() + "' and uname = '" + po.getUserName()
				+ "' and filename = '" + po.getFileName() + "' and page = '" + po.getPage() + "' and location = '"
				+ po.getLocation() + "' and state = 0";
		pStatement = DBManager.getPreparedStatement(sql);
		int i = pStatement.executeUpdate();
		if (i == 1)
			flag = 0;
		else
			flag = 1;
		DBManager.closeConnection();
		return flag;
	}

	public int setCompleteTime(String taskName, String reviewerName, double time)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int flag = -1;

		String sql = "UPDATE review SET  time = ? and state = ? WHERE tname = '" + taskName + "' and uname = '"
				+ reviewerName + "'";
		pStatement = DBManager.getPreparedStatement(sql);
		pStatement.setDouble(1, time);
		pStatement.setString(2, String.valueOf(State.commit));
		int i = pStatement.executeUpdate();
		if (i == 1)
			flag = 0;
		else
			flag = 1;
		DBManager.closeConnection();
		return flag;

	}

	@Override
	public List<ReportPO> getAllReportsByTaskName(String taskName) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT type FROM task where tname = '" + taskName + "'";
		rSet = DBManager.getResultSet(sql);
		Type type = Type.code;
		if (rSet.next())
			type = Type.valueOf(rSet.getString(1));

		DBManager.closeConnection();
		if (type == Type.code) {
			sql = "SELECT * FROM report WHERE tname = '" + taskName
					+ "' and state = 1 GROUP BY filename ORDER BY location";
		} else {
			sql = "SELECT * FROM report WHERE tname = '" + taskName + "' and state = 0 ORDER BY page, location";
		}

		List<ReportPO> result = new ArrayList<ReportPO>();

		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			ReportPO po = new ReportPO(rSet.getString("tname"), rSet.getString("uname"), rSet.getString("filename"),
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"), rSet.getInt("state"),
					rSet.getInt("origin"));
			result.add(po);
		}
		DBManager.closeConnection();
		return result;
	}

	@Override
	public List<ReportPO> getMergeReport(String taskname) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql1 = "SELECT * FROM report r WHERE r.tname = ? AND r.state = 0 AND r.uname IN (SELECT v.uname FROM review v"
				+ " WHERE v.tname = r.tname AND v.state = ?)";
		PreparedStatement pStatement = connection.prepareStatement(sql1);
		pStatement.setString(1, taskname);
		pStatement.setString(2, "merged");
		ResultSet rSet = pStatement.executeQuery();
		ArrayList<ReportPO> reportPOs = new ArrayList<>();
		while (rSet.next()) {
			ReportPO po = new ReportPO(taskname, rSet.getString("uname"), rSet.getString("filename"),
					rSet.getInt("page"), rSet.getInt("location"), rSet.getString("description"), rSet.getInt("State"),
					rSet.getInt("origin"));
			reportPOs.add(po);
		}
		DBManager.stopAll(rSet, pStatement, connection);
		return reportPOs;
	}

	@Override
	public boolean abandonReport(ReportPO po) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "UPDATE report SET state = 2 WHERE tname = '" + po.getTaskName() + "' and uname = '"
				+ po.getUserName() + "' and filename = '" + po.getFileName() + "' and page = '" + po.getPage()
				+ "' and location = '" + po.getLocation() + "'";
		Statement statement = connection.createStatement();
		int i = statement.executeUpdate(sql);
		if (i == 1)
			return true;
		return false;
	}

	@Override
	public boolean recoverReport(ReportPO po) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "UPDATE report SET state = 0 WHERE tname = '" + po.getTaskName() + "' and uname = '"
				+ po.getUserName() + "' and filename = '" + po.getFileName() + "' and page = '" + po.getPage()
				+ "' and location = '" + po.getLocation() + "'";
		Statement statement = connection.createStatement();
		int i = statement.executeUpdate(sql);
		if (i == 1)
			return true;
		return false;
	}

}
