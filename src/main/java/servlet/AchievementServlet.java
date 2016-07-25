package servlet;

import bl.AchievementBlImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by song on 16-7-25.
 */
@WebServlet(name = "AchievementServlet", urlPatterns = {"/AchievementServlet"})
public class AchievementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AchievementBlImpl achievementBl = new AchievementBlImpl();

    }
}
