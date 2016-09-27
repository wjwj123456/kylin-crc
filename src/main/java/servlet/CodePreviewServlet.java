package servlet;

import bl.ReportBlImpl;
import blservice.ReportBlService;
import org.json.JSONArray;
import org.json.JSONObject;
import tools.FileUtil;
import vo.ReportVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CodePreviewServlet
 */
public class CodePreviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodePreviewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        switch (type) {
            case "temp":
                handleTemp(request, response);
                break;
            case "read":
                handleRead(request, response);
                break;
            default:
                break;
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void handleTemp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = (String) request.getSession().getAttribute("username");
        String taskName = request.getParameter("taskName");
        ReportBlService reportBl = new ReportBlImpl();
        List<ReportVO> tempVOs = reportBl.getTempReport(taskName, username);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("records", tempVOs);
        JSONArray jsonArray = jsonObject.getJSONArray("records");
        response.getWriter().write(jsonArray.toString());
    }

    private void handleRead(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getParameter("path");
        List<String> fileLines = FileUtil.readFile(path);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("records", fileLines);
        JSONArray jsonArray = jsonObject.getJSONArray("records");
        response.getWriter().write(jsonArray.toString());
    }

}
