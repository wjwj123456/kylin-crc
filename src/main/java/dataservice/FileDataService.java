package dataservice;

import java.sql.SQLException;
import java.util.List;

public interface FileDataService {

	/**
	 * 
	 * @param taskName
	 * @param paths
	 * @return	0 means success, 1 means failure
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int add(String taskName, List<String> paths) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param taskName
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<String> get(String taskName) throws ClassNotFoundException, SQLException;
}
