package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	public static Connection connect() throws SQLException, ClassNotFoundException {		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://115.28.33.208:3306/crc";
		String username = "ykk2";
		String password = "123qwe";
		
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
	
	public static void stopAll(ResultSet rSet, Statement statement, Connection connection) throws SQLException {
		if(rSet != null) 
			rSet.close();
		if(statement != null)
			statement.close();
		if(connection!=null) 
			connection.close();
	}
	
}

