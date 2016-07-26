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

/**
 * Servlet implementation class FileServlet
 * <p>
 * handle upload and download of file
 */
public class FileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final int maxFileSize = 5000 * 1024;
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

        if (type.equals("upload")) {
            handleUpload(request, response);
        } else if (type.equals("download")) {
            handleDownload(request, response);
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
     *
     * @param request
     * @param response
     */
    private void handleUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String taskName = request.getParameter("taskName");
        ServletContext context = request.getServletContext();
        String filePath = context.getInitParameter("file-upload");
        // 验证上传内容的类型
        String contentType = request.getContentType();

        if ((contentType.contains("multipart/form-data")) && new File(filePath + "/" + taskName).mkdir()) {
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
                        File file = new File(filePath + "/" + taskName, fileName);
                        fileItem.write(file);
                        fileList.add(fileName);
                    }
                }

                fileBl.add(taskName, fileList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * download files of task from server
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void handleDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/x-download");

        String taskName = request.getParameter("taskName");
        String fileName = request.getParameter("fileName");

        String file_download = request.getServletContext().getInitParameter("file-upload") + "/" + taskName + "/" + fileName;
        String file_display = fileName;
        file_display = URLEncoder.encode(file_display, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + file_display);

        OutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();
            inputStream = new FileInputStream(new File(file_download));

            byte[] b = new byte[1024];
            int i = 0;

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
