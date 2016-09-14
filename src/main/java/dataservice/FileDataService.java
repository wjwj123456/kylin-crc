package dataservice;

import java.sql.SQLException;
import java.util.List;

import vo.Message;


public interface FileDataService {

	/**
	 * 
	 * @param taskName
	 * @param paths
	 * @return	
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Message add(String taskName, List<String> paths) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param taskName
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<String> get(String taskName) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param taskName
	 * @param paths
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Message delete(String taskName, List<String> paths) throws ClassNotFoundException, SQLException;
}
