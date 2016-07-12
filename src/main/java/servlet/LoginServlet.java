package servlet;

import bl.LoginBLImpl;
import blservice.LoginBlService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by song on 16-7-8.
 * <p>
 * 登录
 * 负责登陆信息的检查
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operationType = request.getParameter("type");

        if (operationType.equals("login")) {
            handleLogin(request, response);
        } else if (operationType.equals("logout")) {
            handleLogout(request, response);
        }
    }

    /**
     * handle login operation
     *
     * @param request  request from jsp page, include username and password
     * @param response response to jsp page,
     *                 send back an integer which means the result of login verify
     * @see LoginBlService
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        LoginBLImpl login = new LoginBLImpl();
        int result = 0;

        try {
            result = login.verifyAccount(userName, password);

            if (result == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("username", userName);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(result);
    }

    /**
     * handle logout operation
     *
     * @param request request from jsp page
     * @param response response to jsp page, send back 'success'
     * @throws IOException
     */
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.setAttribute("username", null);

        PrintWriter out = response.getWriter();
        out.print("success");
    }
}
