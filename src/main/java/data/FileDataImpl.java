package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataservice.FileDataService;
import vo.Message;

public class FileDataImpl implements FileDataService {

	@Override
	public Message add(String taskName, List<String> paths) throws ClassNotFoundException, SQLException {
		Message message = new Message();
		Connection connection = DBManager.connect();
		String sql = "INSERT INTO file (tname, path) VALUES (?, ?)";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		for (String path: paths) {
			pStatement.setString(1, taskName);
			pStatement.setString(2, path);
			int i = pStatement.executeUpdate();
			if(i == 0) {
				message.setFail();
				message.setInform(path + "dbfail");
				break;
			}
		}
		DBManager.stopAll(null, pStatement, connection);
		return message;
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

	@Override
	public Message delete(String taskName, List<String> paths) throws ClassNotFoundException, SQLException {
		Message message = new Message();
		Connection connection = DBManager.connect();
		String sql = "DELETE FROM file (tname, path) WHERE tname = ? and path = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		for (String path: paths) {
			pStatement.setString(1, taskName);
			pStatement.setString(2, path);
			int i = pStatement.executeUpdate();
			if (i == 0) {
				message.setFail();
				message.setInform(path + "dbfail");
				break;
			}
			if (i > 1) {
				message.setFail();
				message.setInform(path + "repeat");
				break;
			}
		}
		DBManager.stopAll(null, pStatement, connection);
		return message;
	}

	@Override
	public Message rename(String taskName, List<String> fromPaths, List<String> toPaths) throws ClassNotFoundException, SQLException {
		Message message = new Message();
		Connection connection = DBManager.connect();
		String sql = "UPDATE file SET path = ? WHERE tname = ? AND path = ?";
		PreparedStatement pStatement = connection.prepareStatement(sql);
		int length1 = fromPaths.size();
		int length2 = toPaths.size();
		if(length1 != length2) {
			message.setFail();
			message.setInform("length_differ");
		}
		else {
			for (int i = 0; i < length1; i++) {
				String path1 = toPaths.get(i);
				String path2 = fromPaths.get(i);
				pStatement.setString(1, path1);
				pStatement.setString(2, taskName);
				pStatement.setString(3, path2);
				int changedData = pStatement.executeUpdate();
				if(changedData == 0) {
					message.setFail();
					message.setInform(path2 + "dbfail");
					break;
				}
				if(changedData > 1) {
					message.setFail();
					message.setInform(path2 + "repeat_changed_to" + path1);
					break;
				}
			}
		}
		DBManager.stopAll(null, pStatement, connection);
		return message;
	}

}
