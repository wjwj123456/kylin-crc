package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Servlet implementation class FileServlet
 * <p>
 * handle upload and download of file
 */
public class FileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        ServletContext context = request.getServletContext();
        String filePath = context.getInitParameter("file-upload");
        // 验证上传内容了类型
        String contentType = request.getContentType();

        if ((contentType.contains("multipart/form-data"))) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置内存中存储文件的最大值
            factory.setSizeThreshold(maxMemSize);
            // 创建一个新的文件上传处理程序
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置最大上传的文件大小
            upload.setSizeMax(maxFileSize);
            try {
                // 解析获取的文件
                List<FileItem> fileItems = upload.parseRequest(request);
                System.out.println(fileItems);
                // 处理上传的文件
                for (FileItem fileItem : fileItems) {
                    System.out.println(fileItem);
                    if (!fileItem.isFormField()) {
                        System.out.println(3);
                        // 获取上传文件的参数
                        // String fieldName = fi.getFieldName();
                        String fileName = fileItem.getName();
                        // boolean isInMemory = fi.isInMemory();
                        // long sizeInBytes = fi.getSize();
                        // 写入文件
                        System.out.println(filePath);
                        System.out.println(fileName);
                        File file = new File(filePath, fileName);
                        fileItem.write(file);
                    }
                }
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

        String file_download = "/home/song/program/crc/data/1.png";
        String file_display = "1.png";
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

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
