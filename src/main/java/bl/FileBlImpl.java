package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.FileBlService;
import data.FileDataImpl;
import dataservice.FileDataService;
import vo.Message;

public class FileBlImpl implements FileBlService {

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
		List<String> result = new ArrayList<>();
		try {
			result = fileData.get(taskName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Message delete(String taskName, List<String> paths) {
		Message message = null;
		try {
			message = fileData.delete(taskName, paths);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return message;
	}

	@Override
	public Message rename(String taskName, List<String> fromPaths, List<String> toPaths) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<String> readFile(String path) {
		List<String> list = new ArrayList<String>();
		File file = new File(path);
		System.out.println(file.getPath());
		BufferedReader reader = null;
		try {
			// System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			String tempString = "";
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// // ��ʾ�к�
				// System.out.println("line " + line + ": " + tempString);
				// line++;
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
				}
			}
		}
		return list;
	}

}
