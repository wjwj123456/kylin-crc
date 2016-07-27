package servlet;

import bl.FriendBlImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by song on 16-7-27.
 * <p>
 * 管理好友
 */
@WebServlet(name = "FriendsServlet", urlPatterns = {"/FriendsServlet"})
public class FriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type.equals("add")) {
            handleAdd(request, response);
        } else if (type.equals("delete")) {
            handleDelete(request, response);
        }
    }

    /**
     * 添加好友
     *
     * @param request
     * @param response
     */
    private void handleAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = (String) request.getSession().getAttribute("username");
        String friendName = request.getParameter("friendName");

        FriendBlImpl friendBl = new FriendBlImpl();

        int result = friendBl.addFriend(userName, friendName);

        PrintWriter out = response.getWriter();
        out.print(result);
    }

    /**
     * 删除好友
     *
     * @param request
     * @param response
     */
    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = (String) request.getSession().getAttribute("username");
        String friendName = request.getParameter("friendName");

        FriendBlImpl friendBl = new FriendBlImpl();

        int result = friendBl.delete(userName, friendName);

        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
