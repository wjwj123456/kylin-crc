package servlet;

import bl.FileBlImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import vo.FileVO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    private static final int maxFileSize = 5000 * 1024;

    /**
     * 内存最大
     */
    private static final int maxMemSize = 5000 * 1024;

    /**
     * 项目根目录
     */
    private String rootPath;

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
                handleUploadFile(request, response);
                break;
            case "uploadFolder":
                handleUploadFolder(request, response);
                break;
            case "download":
                handleDownload(request, response);
                break;
            case "getFileStruct":
                handleFileStruct(request, response);
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
    private void handleUploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (createRoot(request, response)) {
            try {
                // 解析获取的文件
                List<FileItem> fileItems = getFileItem(request);
                // 处理上传的文件
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        String fileName = fileItem.getName();

                        // 写入文件
                        writeFile(fileItem, rootPath, fileName);
                    }
                }
            } catch (FileUploadException e) {
                response.getWriter().print("文件过大,无法上传");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * upload folder
     */
    private void handleUploadFolder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (createRoot(request, response)) {
            try {
                // 解析获取的文件
                List<FileItem> fileItems = getFileItem(request);
                // 处理上传的文件
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        String fileName = fileItem.getName();
                        // 获取文件的各级目录
                        List<String> separatedPath = getSeparatedPath(fileName);
                        // 扫描文件目录结构
                        String temp = rootPath;
                        for (int i = 0; i < separatedPath.size() - 1; i++) {
                            temp += "/" + separatedPath.get(i);
                            // 若父级目录目录不存在，创建之
                            if (!new File(temp).exists()) {
                                new File(temp).mkdir();
                            }
                        }

                        // 写入文件
                        writeFile(fileItem, temp, separatedPath.get(separatedPath.size() - 1));
                    }
                }
            } catch (FileUploadException e) {
                response.getWriter().print("文件夹过大,无法上传");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建项目根目录
     *
     * @return 创建成功, 返回true;否则返回false
     */
    private boolean createRoot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String taskName = request.getParameter("taskName");
        if (taskName == null) {
            return false;
        }

        ServletContext context = request.getServletContext();
        // 项目根目录
//        rootPath = context.getRealPath("/data/" + taskName);
        rootPath = "/home/song/opt/data/" + taskName;
        // 验证上传内容的类型
        String contentType = request.getContentType();

        PrintWriter out = response.getWriter();

        if (!contentType.contains("multipart/form-data")) {
            out.print("上传内容类型错误");

            return false;
        }

        if (!new File(rootPath).mkdir()) {
            out.print("项目已存在");

            return false;
        }

        return true;
    }

    /**
     * 获取request对象中包含的文件列表
     *
     * @throws FileUploadException 文件（夹）大小超出限制抛出异常
     */
    private List<FileItem> getFileItem(HttpServletRequest request) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存中存储文件的最大值
        factory.setSizeThreshold(maxMemSize);
        // 创建一个新的文件上传处理程序
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大上传的文件大小
        upload.setSizeMax(maxFileSize);

        return upload.parseRequest(request);
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

    /**
     * 查询文件路径
     * @param request 包含项目名，文件夹路径
     * @param response 返回文件（夹）名称列表，不包含路径
     */
    private void handleFileStruct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getParameter("path");

        FileBlImpl fileBl = new FileBlImpl();

        List<FileVO> fileList = fileBl.getFileList(path);

        JSONArray array = new JSONArray(fileList);

        PrintWriter out = response.getWriter();
        out.print(array.toString());
    }
}
