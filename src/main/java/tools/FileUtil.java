package tools;

import org.apache.commons.fileupload.FileItem;
import vo.FileVO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by song on 16-9-28.
 * <p>
 * 文件操作相关工具类
 */
public class FileUtil {

    private FileUtil() {
    }

    /**
     * 从文件路径中获取文件名
     *
     * @param path 文件路径
     * @return 不包含文件路径的文件名
     */
    public static String getFileName(String path) {
        String temp[] = path.split("/");

        return temp[temp.length - 1];
    }

    /**
     * 向磁盘写入文件
     *
     * @param fileItem 文件项，包含文件内容
     * @param filePath 文件路径，不包含文件名
     * @param fileName 文件名，不包含路径
     */
    public static void writeFile(FileItem fileItem, String filePath, String fileName) throws Exception {
        File file = new File(filePath, fileName);

        fileItem.write(file);
    }

    /**
     * 从文件路径中获取文件名及各级父目录
     *
     * @param filePath 文件相对项目目录的路径
     * @return List<String> (0 ~ n - 2) :各级父目录名称
     * n - 1       :不包含路径的文件名
     * 示例：filePath --- taskName/src/main/java/test.java
     * 返回["taskName", "src", "main", "java", "test.java"]
     */
    public static List<String> getSeparatedPath(String filePath) {
        List<String> result = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(filePath, "/");

        while (tokenizer.hasMoreElements()) {
            result.add(tokenizer.nextToken());
        }

        return result;
    }


    /**
     * 获取文件夹下的文件(夹)名称
     *
     * @param path 文件夹路径
     * @return path对应文件夹下的文件(夹)名称, 不包含路径
     */
    public static List<FileVO> listFile(String path) {
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
     * 读取文本文件内容
     *
     * @param path 路径,包含文件名
     * @return 文本文件的内容, 每行占一项
     */
    public static List<String> readFile(String path) {
        List<String> list = new ArrayList<>();
        File file = new File(path);
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
     * 获取文件(夹)大小
     *
     * @param file 文件(夹)
     * @return 单位转换后的大小, 如3.6K
     */
    private static long getFileSize(File file) {
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
     *
     * @param size 大小
     * @return 转换后的大小
     */
    private static String transferSize(long size) {
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
}
