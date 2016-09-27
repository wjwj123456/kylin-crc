package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;

import blservice.FileBlService;
import data.FileDataImpl;
import dataservice.FileDataService;
import tools.MyURLEncoder;
import vo.FileVO;
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
    @Deprecated
    public List<String> get(String taskName) {
        String[] stub = new String[]{"src", "main", "java"};

        return Arrays.asList(stub);
    }

    @Override
    public List<FileVO> getFileList(String path) {
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
        File file = new File(ROOT_PATH + "/" + path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                list.add(MyURLEncoder.encode(tempString, "utf-8"));
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
     *
     * @param path 文件夹路径
     * @return path对应文件夹下的文件(夹)名称, 不包含路径
     */
    private List<FileVO> listFile(String path) {
        List<FileVO> result = new ArrayList<>();

        File file = new File(path);
        File[] fileList = file.listFiles();

        assert fileList != null;
        for (File aFileList : fileList) {
            if (aFileList.isDirectory()) {
                result.add(new FileVO("d" + aFileList.getName(), transferSize(getFileSize(aFileList))));
            } else if (aFileList.isFile()) {
                result.add(new FileVO("f" + aFileList.getName(), transferSize(getFileSize(aFileList))));
            }
        }

        return result;
    }

    /**
     * 获取文件(夹)大小
     *
     * @param file 文件(夹)
     * @return 单位转换后的大小, 如3.6K
     */
    private long getFileSize(File file) {
        if (file.isFile()) {
            return file.length();
        }

        long size = 0;
        File fileList[] = file.listFiles();
        assert fileList != null;
        for (File aFileList : fileList) {
            if (aFileList.isDirectory()) {
                size = size + getFileSize(aFileList);
            } else {
                size = size + aFileList.length();
            }
        }

        return size;
    }

    /**
     * 将文件大小转换单位
     * @param size 大小
     * @return 转换后的大小
     */
    private String transferSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String result;

        if (size < 1024) {
            result = size + "B";
        } else if (size < 1048576) {
            result = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            result = df.format((double) size / 1048576) + "M";
        } else {
            result = df.format((double) size / 1073741824) + "G";
        }

        return result;
    }

    private String getFileType(File file) {
        if (file.isDirectory()) {
            return "dir";
        }

        return "";
    }
}
