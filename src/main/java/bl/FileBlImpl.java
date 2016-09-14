package bl;

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

}
