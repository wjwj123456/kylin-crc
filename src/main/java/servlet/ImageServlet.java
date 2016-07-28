package servlet;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.UserInfoBlImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class ImageServlet
 * <p>
 * store portrait of user
 */
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int maxFileSize = 5000 * 1024;
    private int maxMemSize = 5000 * 1024;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("type").equals("store")) {
            storePortrait(request, response);
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
     * 存储头像
     *
     * @param request
     * @param response
     */
    private void storePortrait(HttpServletRequest request, HttpServletResponse response) {
        String userName = (String) request.getSession().getAttribute("username");
        // 头像存储路径
        String filePath = request.getServletContext().getRealPath("/portrait");

        try {
            String temp = request.getParameter("data");
            // 不知为什么，所有‘+’都被替换为了‘ ’，只能换回来
            temp = temp.replaceAll(" ", "+");

            // Base64解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(temp);
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(new File(filePath + File.separator+ userName + ".jpeg"));
            out.write(bytes);
            out.flush();
            out.close();

            UserInfoBlImpl userInfoBl = new UserInfoBlImpl();
            userInfoBl.setPicture(userName, userName + ".jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}