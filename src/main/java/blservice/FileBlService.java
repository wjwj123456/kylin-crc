package blservice;

import java.util.List;

import vo.Message;

public interface FileBlService {

	/**
	 * 
	 * @param taskName
	 * @param paths
	 * @return	
	 */
	public Message add(String taskName, List<String> paths);

	/**
	 * 
	 * @param taskName
	 * @return
	 */
	public List<String> get(String taskName);
	
	public Message delete(String taskName, List<String> paths);
	
	public Message rename(String taskName, List<String> fromPaths, List<String> toPaths);
}
