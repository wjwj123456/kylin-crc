package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bl.FileBlImpl;
import bl.ReportBlImpl;
import blservice.FileBlService;
import blservice.ReportBlService;
import vo.ReportVO;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = (String) request.getParameter("type");
		switch (type) {
		case "temp":
			handleTemp(request,response);
			break;
		case "read":
			handleRead(request,response);
			break;
		default:
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void handleTemp(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username = (String) request.getSession().getAttribute("username");
		String taskName = request.getParameter("taskName");
		ReportBlService reportBl = new ReportBlImpl();
		List<ReportVO> tempVOs = reportBl.getTempReport(taskName,username);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("records", tempVOs);
		JSONArray jsonArray = new JSONArray();
		jsonArray =  jsonObject.getJSONArray("records");
		response.getWriter().write(jsonArray.toString());
	}
	private void handleRead(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String path = request.getParameter("path");
		List<String> fileLines = FileBlImpl.readFile(path);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("records", fileLines);
		JSONArray jsonArray = new JSONArray();
		jsonArray =  jsonObject.getJSONArray("records");
		response.getWriter().write(jsonArray.toString());
	}

}