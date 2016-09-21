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
	Message add(String taskName, List<String> paths);

	/**
	 * 
	 * @param taskName
	 * @return
	 */
	List<String> get(String taskName);

	Message delete(String taskName, List<String> paths);

	Message rename(String taskName, List<String> fromPaths, List<String> toPaths);
}
