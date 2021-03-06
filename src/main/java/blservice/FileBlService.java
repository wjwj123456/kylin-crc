package blservice;

import java.util.List;

import vo.FileVO;
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
	@Deprecated
	List<String> get(String taskName);

	/**
	 * 获取项目某一文件夹下的文件(夹)列表
	 * @param path 目标文件夹相对项目目录的相对路径
	 * @return 文件对象列表,名称不包含路径
	 */
	List<FileVO> getFileList(String path);

	Message delete(String taskName, List<String> paths);

	Message rename(String taskName, List<String> fromPaths, List<String> toPaths);


}
