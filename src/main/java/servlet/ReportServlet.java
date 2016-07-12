package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bl.ReportBlImpl;
import vo.ReportVO;

/**
 * Servlet implementation class ReportServlet
 * 
 * commit a report at the end of reviewing
 */
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String taskName = request.getParameter("taskName");
		String userName = (String) request.getSession().getAttribute("username");
		String data = request.getParameter("data");

		List<ReportVO> reportList = new ArrayList<ReportVO>();
		JSONArray jsonArray = new JSONArray(data);

		System.out.println(data);
		System.out.println(jsonArray);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			reportList.add(new ReportVO(taskName, userName, jsonObject.getString("fileName"), jsonObject.getInt("page"),
					Integer.parseInt(jsonObject.getString("location")), jsonObject.getString("description"),
					jsonObject.getInt("state"), jsonObject.getInt("origin")));
		}

		ReportBlImpl report = new ReportBlImpl();
		int result = report.createReport(reportList);

		PrintWriter out = response.getWriter();
		out.print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
