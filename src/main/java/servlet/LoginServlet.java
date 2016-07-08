package servlet;

import blservice.LoginBlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by song on 16-7-8.
 *
 * 登录
 * 负责登陆信息的检查
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        session.setAttribute("username", userName);

        PrintWriter out = response.getWriter();
        out.print("success");
    }
}
