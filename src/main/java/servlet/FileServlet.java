package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileServlet
 * 
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
	 *      response)
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
	 *      response)
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
	private void handleUpload(HttpServletRequest request, HttpServletResponse response) {
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		ServletContext context = request.getServletContext();
		String filePath = context.getInitParameter("file-upload");
		// 验证上传内容了类型
		String contentType = request.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {
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
				// 处理上传的文件
				Iterator<FileItem> i = fileItems.iterator();
				while (i.hasNext()) {
					FileItem fileItem = (FileItem) i.next();
					if (!fileItem.isFormField()) {
						// 获取上传文件的参数
						// String fieldName = fi.getFieldName();
						String fileName = fileItem.getName();
						// boolean isInMemory = fi.isInMemory();
						// long sizeInBytes = fi.getSize();
						// 写入文件
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
	 * @param requset
	 * @param response
	 * @throws IOException
	 */
	private void handleDownload(HttpServletRequest requset, HttpServletResponse response) throws IOException {
		response.reset();
		response.setContentType("application/x-download");

		String file_download = "/song/home/program/crc/data/1.png";
		String file_display = "1.png";
		file_display = URLEncoder.encode(file_display, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + file_display);

		OutputStream outputStream = null;
		FileInputStream inputStream = null;
		try {
			outputStream = response.getOutputStream();
			inputStream = new FileInputStream(file_download);

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
			System.out.println("Error!");
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
