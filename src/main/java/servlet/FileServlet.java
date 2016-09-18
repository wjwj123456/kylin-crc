package servlet;

import bl.FileBlImpl;
import blservice.FileBlService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Servlet implementation class FileServlet
 * <p>
 * handle upload and download of file
 */
public class FileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * 文件最大
     */
    private final int maxFileSize = 5000 * 1024;

    /**
     * 内存最大
     */
    private final int maxMemSize = 5000 * 1024;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        switch (type) {
            case "upload":
                handleUpload(request);
                break;
            case "uploadFolder":
                handleUploadFolder(request);
                break;
            case "download":
                handleDownload(request, response);
                break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * upload files
     */
    private void handleUpload(HttpServletRequest request) throws IOException, ServletException {
        String taskName = request.getParameter("taskName");
        ServletContext context = request.getServletContext();
        String filePath = context.getRealPath("/data/" + taskName);
        filePath = "/home/song/opt/data/" + taskName;
        // 验证上传内容的类型
        String contentType = request.getContentType();

        if ((contentType.contains("multipart/form-data")) && new File(filePath).mkdir()) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置内存中存储文件的最大值
            factory.setSizeThreshold(maxMemSize);
            // 创建一个新的文件上传处理程序
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置最大上传的文件大小
            upload.setSizeMax(maxFileSize);

            FileBlService fileBl = new FileBlImpl();
            List<String> fileList = new ArrayList<>();

            try {
                // 解析获取的文件
                List<FileItem> fileItems = upload.parseRequest(request);
                // 处理上传的文件
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        String fileName = fileItem.getName();

                        // 写入文件
                        writeFile(fileItem, filePath, fileName);

                        fileList.add(fileName);
                    }
                }

//                fileBl.add(taskName, fileList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * upload folder
     */
    private void handleUploadFolder(HttpServletRequest request) {
        String taskName = request.getParameter("taskName");
        ServletContext context = request.getServletContext();
        // TODO 部署时更改路径
        // 项目根目录
        String rootPath = context.getRealPath("/data/" + taskName);
        rootPath = "/home/song/opt/data/" + taskName;
        // 验证上传内容的类型
        String contentType = request.getContentType();

        if ((contentType.contains("multipart/form-data")) && new File(rootPath).mkdir()) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置内存中存储文件的最大值
            factory.setSizeThreshold(maxMemSize);
            // 创建一个新的文件上传处理程序
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置最大上传的文件大小
            upload.setSizeMax(maxFileSize);

            FileBlService fileBl = new FileBlImpl();
            List<String> fileList = new ArrayList<>();

            try {
                // 解析获取的文件
                List<FileItem> fileItems = upload.parseRequest(request);
                // 处理上传的文件
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        String fileName = fileItem.getName();

                        List<String> separatedPath = getSeparatedPath(fileName);
                        System.out.println(separatedPath);
                        // 扫描文件目录结构
                        String temp = rootPath;
                        for (int i = 0; i < separatedPath.size() - 2; i++) {
                            temp += "/" + separatedPath.get(i);
                            // 若父级目录目录不存在，创建之
                            if (!new File(temp).exists()) {
                                new File(temp).mkdir();
                            }
                        }
                        System.out.println(fileName);
                        // 写入文件
//                        writeFile(fileItem, rootPath, fileName);
                    }
                }

//                fileBl.add(taskName, fileList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向磁盘写入文件
     *
     * @param fileItem 文件项，包含文件内容
     * @param filePath 文件路径，不包含文件名
     * @param fileName 文件名，不包含路径
     */
    private void writeFile(FileItem fileItem, String filePath, String fileName) throws Exception {
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
    private List<String> getSeparatedPath(String filePath) {
        List<String> result = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(filePath, "/");

        while (tokenizer.hasMoreElements()) {
            result.add(tokenizer.nextToken());
        }

        return result;
    }

    /**
     * download files of task from server
     */
    private void handleDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/x-download");

        String taskName = request.getParameter("taskName");
        String fileName = request.getParameter("fileName");

        String file_download = request.getServletContext().getRealPath("/data") + "/" + taskName + "/" + fileName;
        String file_display = fileName;
        file_display = URLEncoder.encode(file_display, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + file_display);

        OutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(new File(file_download));

            byte[] b = new byte[1024];
            int i;

            while ((i = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, i);
            }
            outputStream.flush();
            // 要加以下两句话，否则会报错
            // java.lang.IllegalStateException: getOutputStream() has already
            // been called for //this response
            // out.clear();
            // out = pageContext.pushBody();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
