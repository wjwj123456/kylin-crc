package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	// 172.17.243.193
	// root
	// 1129009
//	private static final String IP = "115.159.195.149";
	private static final String IP = "172.17.208.122";

	private static final String userName = "root";

	private static final String password = "1129009";

	public static Connection connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://" + IP + ":3306/crc";

		Connection connection = DriverManager.getConnection(url + "?serverTimezone=UTC&characterEncoding=UTF-8", userName,
				password);
		return connection;
	}

	public static void stopAll(ResultSet rSet, Statement statement, Connection connection) throws SQLException {
		if (rSet != null)
			rSet.close();
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet result = null;

	public static PreparedStatement getPreparedStatement(String sql) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + IP + ":3306/crc";
			conn = DriverManager.getConnection(url + "?serverTimezone=UTC&characterEncoding=UTF-8", userName, password);
			pstmt = conn.prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pstmt;
	}

	public static ResultSet getResultSet(String sql) {

		try {
			pstmt = getPreparedStatement(sql);
			result = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void closeConnection() {
		try {
			if (result != null) {
				result.close();
			}

			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
