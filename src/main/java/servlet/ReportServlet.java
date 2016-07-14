
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
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
		String type = request.getParameter("type");

		if (type.equals("store")) {
			handleStore(request, response);
		} else if (type.equals("delete")) {
			handleDelete(request, response);
		} else if (type.equals("commit")) {
			handleCommit(request, response);
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
	 * 向数据库中写入记录
	 * 
	 * @param request
	 * @param reponse
	 * @throws IOException
	 * @throws JSONException
	 */
	private void handleStore(HttpServletRequest request, HttpServletResponse reponse)
			throws JSONException, IOException {
		List<ReportVO> reportList = new ArrayList<ReportVO>();
		JSONObject jsonObject = new JSONObject(request.getParameter("data"));

		reportList.add(
				new ReportVO(jsonObject.getString("taskName"), (String) request.getSession().getAttribute("username"),
						jsonObject.getString("fileName"), jsonObject.getInt("page"), jsonObject.getInt("location"),
						jsonObject.getString("description"), jsonObject.getInt("state"), jsonObject.getInt("origin")));

		ReportBlImpl report = new ReportBlImpl();

		report.createReport(reportList);
	}

	/**
	 * 从数据库中删除记录
	 * 
	 * @param request
	 * @param reponse
	 * @throws IOException
	 */
	private void handleDelete(HttpServletRequest request, HttpServletResponse reponse) throws IOException {
		JSONObject jsonObject = new JSONObject(request.getParameter("data"));

		ReportBlImpl report = new ReportBlImpl();
		int result = report.deleteReport(
				new ReportVO(jsonObject.getString("taskName"), (String) request.getSession().getAttribute("username"),
						jsonObject.getString("fileName"), jsonObject.getInt("page"), jsonObject.getInt("location"),
						jsonObject.getString("description"), jsonObject.getInt("state"), jsonObject.getInt("origin")));

		PrintWriter out = reponse.getWriter();
		out.print(result);
	}

	/**
	 * 提交报告
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void handleCommit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String taskName = request.getParameter("taskName");
		String userName = (String) request.getSession().getAttribute("username");
		double time = Double.parseDouble(request.getParameter("time"));
		ReportBlImpl report = new ReportBlImpl();
		int result = report.setCompleteTime(taskName, userName, time);

		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
