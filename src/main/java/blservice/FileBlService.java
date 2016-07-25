package blservice;

import java.util.List;

public interface FileBlService {

	/**
	 * 
	 * @param taskName
	 * @param paths
	 * @return	0 means success, 1 means failure
	 */
	public int add(String taskName, List<String> paths);

	/**
	 * 
	 * @param taskName
	 * @return
	 */
	public List<String> get(String taskName);
}
