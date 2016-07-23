package blservice;

import java.util.List;

public interface FileBlService {

	/**
	 * 
	 * @param taskName
	 * @param paths
	 * @return
	 */
	public int add(String taskName, List<String> paths);

	/**
	 * 
	 * @param taskName
	 * @return
	 */
	public List<String> get(String taskName);
}
