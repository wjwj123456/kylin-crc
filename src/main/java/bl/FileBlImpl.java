package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import blservice.FileBlService;
import data.FileDataImpl;
import dataservice.FileDataService;
import vo.Message;

public class FileBlImpl implements FileBlService {

	/**
	 * 文件存储根目录
	 */
	private static final String ROOT_PATH = "/home/song/opt/data";

	private FileDataService fileData;

	public FileBlImpl() {
		this.fileData = new FileDataImpl();
	}

	@Override
	public Message add(String taskName, List<String> paths) {
		Message message = null;
		try {
			message = fileData.add(taskName, paths);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<String> get(String taskName) {
        // TODO 修改实现
        String[] stub = new String[]{"src", "main", "java"};

		return Arrays.asList(stub);
//        return Arrays.asList(getFileList(taskName));
	}

	@Override
	public List<String> getFileList(String path) {
		return listFile(ROOT_PATH + "/" + path);
	}

	@Override
	public Message delete(String taskName, List<String> paths) {
		Message message = null;
		try {
			message = fileData.delete(taskName, paths);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return message;
	}

	@Override
	public Message rename(String taskName, List<String> fromPaths, List<String> toPaths) {
		return null;
	}

	public static List<String> readFile(String path) {
		List<String> list = new ArrayList<>();
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 获取文件夹下的文件(夹)名称
	 * @param path 文件夹路径
	 * @return path对应文件夹下的文件(夹)名称,不包含路径
	 */
	private List<String> listFile(String path) {
		List<String> result = new ArrayList<>();

		File file = new File(path);
		File[] fileList = file.listFiles();

		assert fileList != null;
		for (File aFileList : fileList) {
			if (aFileList.isDirectory()) {
				result.add("d" + aFileList.getName());
			} else if (aFileList.isFile()) {
				result.add("f" + aFileList.getName());
			}
		}

		return result;
	}
}
