package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.FileBlService;
import data.FileDataImpl;
import dataservice.FileDataService;

public class FileBlImpl implements FileBlService {

	private FileDataService fileData;
	
	public FileBlImpl() {
		this.fileData = new FileDataImpl();
	}
	
	@Override
	public int add(String taskName, List<String> paths) {
		int i = 1;
		try {
			i = fileData.add(taskName, paths);
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		return i;
	}

	@Override
	public List<String> get(String taskName) {
		List<String> result = new ArrayList<>();
		try {
			result = fileData.get(taskName);
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		return result;
	}

}
