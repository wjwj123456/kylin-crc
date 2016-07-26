package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bl.ReviewBlImpl;
import tools.Encode;
import vo.Language;
import vo.Power;
import vo.TaskVO;
import vo.Type;

/**
 * Servlet implementation class CreateTaskServlet
 * <p>
 * create new tasks
 */
public class CreateTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTaskServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type.equals("createNewTask")) {
            createNewTask(request, response);
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

    private void createNewTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = (String) request.getSession().getAttribute("username");

        JSONObject jsonObject = new JSONObject(request.getParameter("data"));
        String date = jsonObject.getString("deadline");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date deadline = null;
        try {
            deadline = dateFormat.parse(date + ":00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ReviewBlImpl review = new ReviewBlImpl();
        int result = review.saveReviewInfo(new TaskVO(userName, jsonObject.getString("taskName"),
                Type.valueOf(jsonObject.getString("type")), "", jsonObject.getString("describe"),
                deadline, 0, Language.valueOf(jsonObject.getString("language")),
                Power.valueOf(jsonObject.getString("power"))));

        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
