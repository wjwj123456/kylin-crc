package bl;

import blservice.FileBlService;
import data.FileDataImpl;
import dataservice.FileDataService;
import tools.FileUtil;
import vo.FileVO;
import vo.Message;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
    @Deprecated
    public List<String> get(String taskName) {
        String[] stub = new String[]{"src", "main", "java"};

        return Arrays.asList(stub);
    }

    @Override
    public List<FileVO> getFileList(String path) {
        return FileUtil.listFile(path);
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
}
