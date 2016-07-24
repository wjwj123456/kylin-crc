package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataservice.FileDataService;

public class FileDataImpl implements FileDataService {

	@Override
	public int add(String taskName, List<String> paths) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "INSERT INTO file (tname, path) VALUES (?, ?)";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		for (String path: paths) {
			pStatement.setString(1, taskName);
			pStatement.setString(2, path);
			int i = pStatement.executeUpdate();
			if(i != 1) {
				DBManager.stopAll(null, pStatement, connection);
				return 1;
			}
		}
		DBManager.stopAll(null, pStatement, connection);
		return 0;
	}

	@Override
	public List<String> get(String taskName) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.connect();
		String sql = "SELECT path FROM file WHERE tname = '" + taskName + "'";
		Statement statement = connection.createStatement();
		ResultSet rSet = statement.executeQuery(sql);
		ArrayList<String> result = new ArrayList<>();
		while(rSet.next()) {
			String path = rSet.getString("path");
			result.add(path);
		}
		return result;
	}

}
